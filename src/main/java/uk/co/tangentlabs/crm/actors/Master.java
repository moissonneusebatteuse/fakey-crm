package uk.co.tangentlabs.crm.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import uk.co.tangentlabs.crm.actors.messages.Calculate;
import uk.co.tangentlabs.crm.actors.messages.MapMessage;
import uk.co.tangentlabs.crm.actors.messages.MapperResult;
import uk.co.tangentlabs.crm.actors.messages.TaskResult;
import uk.co.tangentlabs.crm.actors.messages.UnificationMessage;
import uk.co.tangentlabs.crm.actors.messages.UnifyResult;
import uk.co.tangentlabs.crm.actors.setter.Setter;
import uk.co.tangentlabs.crm.actors.strategy.unification.NaiveEmailUnificationStrategy;
import uk.co.tangentlabs.crm.actors.strategy.unification.UnificationStrategy;
import uk.co.tangentlabs.entities.Contact;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;
import akka.util.Duration;

public class Master extends UntypedActor {
	private final int nrOfMessages;
	private final int nrOfWorkers;
	private int nrOfResults;
	private int unificationTasks = 0;
	private final long start = System.currentTimeMillis();
	final List<Setter> setters;
	final List<String[]> contactLines;
	List<Contact> result;

	private final ActorRef listener;
	private final ActorRef workerRouter;
	private final ConcurrentHashMap<String, List<Contact>> contactMap;
	private UnificationStrategy unificationStrategy;

	public Master(final List<String[]> contactLines,
			final List<Setter> setters, final int nrOfWorkers,
			ActorRef listener) {
		nrOfResults = 0;
		this.setters = setters;
		this.contactLines = contactLines;
		this.contactMap = new ConcurrentHashMap<String, List<Contact>>();
		this.nrOfMessages = contactLines.size();
		this.nrOfWorkers = nrOfWorkers;
		this.listener = listener;
		this.unificationStrategy = new NaiveEmailUnificationStrategy();
		result = new ArrayList<Contact>();
		workerRouter = this.getContext().actorOf(
				new Props(Mapper.class).withRouter(new RoundRobinRouter(
						this.nrOfWorkers)), "workerRouter");
	}

	public void onReceive(Object message) {
		if (message instanceof Calculate) {
			for (String[] line : contactLines) {
				workerRouter.tell(new MapMessage(line, setters), getSelf());
			}
		} else if (message instanceof MapperResult) {
			MapperResult msg = (MapperResult) message;

			nrOfResults += 1;

			if (msg.getException().size() == 0) {
				String partitionKey = unificationStrategy
						.getPartitionKey(msg.getContact());
				if (!contactMap.containsKey(partitionKey)) {
					contactMap.put(partitionKey, new ArrayList<Contact>());
				}
				contactMap.get(partitionKey).add(msg.getContact());
			}
			if (nrOfResults == nrOfMessages) {
				System.out.println("Part 1 complete");
				contactLines.clear();
				setters.clear();
				Set<String> keys = contactMap.keySet();
				unificationTasks = contactMap.size();

				for (String key : keys) {
					// System.out.println("Unifying: "+key);
					workerRouter.tell(new UnificationMessage(
							unificationStrategy, contactMap.get(key)),
							getSelf());
				}

			}
			// Check if the lot has finished, if so, start the deduplication
			// job
		} else if (message instanceof UnifyResult) {

			UnifyResult msg = (UnifyResult) message;
			// System.out.println("Unified: "+msg.getContact().getEmail());
			result.add(msg.getContact());
			if (result.size() == unificationTasks) {
				contactMap.clear();
				System.out.println("Part 2 complete");
				Duration duration = Duration.create(
						System.currentTimeMillis() - start,
						TimeUnit.MILLISECONDS);
				listener.tell(new TaskResult(result, duration), getSelf());
			}
		} else {
			unhandled(message);
		}
	}
}