package uk.co.tangentlabs.crm.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import uk.co.tangentlabs.crm.actors.messages.Calculate;
import uk.co.tangentlabs.crm.actors.messages.TaskResult;
import uk.co.tangentlabs.crm.actors.setter.Setter;
import uk.co.tangentlabs.crm.actors.setter.SetterFactory;
import uk.co.tangentlabs.crm.actors.validator.EmailValidator;
import uk.co.tangentlabs.crm.actors.validator.IsNotEmptyValidator;
import uk.co.tangentlabs.crm.actors.validator.URLValidator;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import akka.util.Duration;

public class Importer {
	static SessionFactory sessionFactory;

	public Importer(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void run(SessionFactory session, int count) {

		List<String[]> list = new ArrayList<String[]>();

		RandomString s = new RandomString(4);
		RandomString y = new RandomString(2);

		// setup list
		for (int i = 0; i < count; i++) {
			list.add(new String[] { s.nextString(), s.nextString(),
					s.nextString() + "@gmail.com", y.nextString(),
					"http://" + s.nextString() + ".org" });
		}

		List<Setter> setters = new ArrayList<Setter>();
		setters.add(SetterFactory.getSetter(new IsNotEmptyValidator(),
				"firstname"));
		setters.add(SetterFactory.getSetter(new IsNotEmptyValidator(),
				"lastname"));
		setters.add(SetterFactory.getSetter(new EmailValidator(), "email"));
		setters.add(SetterFactory.getSetter(null, null));
		setters.add(SetterFactory.getSetter(new URLValidator(), "website"));

		Importer importJob = new Importer(session);
		importJob.calculate(list, setters, 5);
	} 

	

	

	

	

	

	public static class Listener extends UntypedActor {
		public void onReceive(Object message) {
			if (message instanceof TaskResult) {
				TaskResult result = (TaskResult) message;
				System.out.println(String.format("\n"
						+ "\n\tCalculation time: \t%s", result.getDuration()
						.toString()));
				System.out.println(result.getContacts().size());

				long start = System.currentTimeMillis();
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				for (int i = 0; i < result.getContacts().size(); i++) {

					session.save(result.getContacts().get(i));
					if (i % 50 == 0) { // 20, same as the JDBC batch size
						// flush a batch of inserts and release memory:
						session.flush();
						session.clear();
					}
				}

				tx.commit();
				session.close();
				Duration duration = Duration.create(System.currentTimeMillis()
						- start, TimeUnit.MILLISECONDS);
				System.out.println(String.format("\n"
						+ "\n\tCalculation time: \t%s", duration.toString()));

				getContext().system().shutdown();
			} else {
				unhandled(message);
			}
		}
	}

	public void calculate(final List<String[]> contactLines,
			final List<Setter> setters, final int nrOfWorkers) {

		ActorSystem system = ActorSystem.create("Import");

		final ActorRef listener = system.actorOf(new Props(Listener.class),
				"listener");

		ActorRef master = system.actorOf(new Props(new UntypedActorFactory() {
			public UntypedActor create() {
				return new Master(contactLines, setters, nrOfWorkers, listener);
			}
		}), "master");

		master.tell(new Calculate());
	}
}
