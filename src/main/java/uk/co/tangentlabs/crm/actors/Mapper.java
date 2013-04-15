package uk.co.tangentlabs.crm.actors;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import uk.co.tangentlabs.crm.actors.messages.MapMessage;
import uk.co.tangentlabs.crm.actors.messages.MapperResult;
import uk.co.tangentlabs.crm.actors.messages.UnificationMessage;
import uk.co.tangentlabs.crm.actors.messages.UnifyResult;
import uk.co.tangentlabs.crm.actors.setter.Setter;
import uk.co.tangentlabs.crm.actors.validator.ValidationException;
import uk.co.tangentlabs.entities.Contact;
import akka.actor.UntypedActor;

/**
 * Maps a row of CSV data to a Contact. All mapped columns are retained,
 * with extra columns added to the map element
 * 
 * Will also handle a UnificationMessage to generate a unified view of a 
 * record, based on it's Strategy
 * 
 * @author rnoble
 * 
 */
public class Mapper extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof MapMessage) {
			MapMessage toMap = (MapMessage) message;
			mapRowToContact(toMap);
		} else if (message instanceof UnificationMessage) {
			UnificationMessage unify = (UnificationMessage) message;
			unifyRecords(unify);
		} else {
			unhandled(message);
		}

	}

	public void unifyRecords(UnificationMessage unify) {
		Contact contact = unify.getUnificationStrategy().merge(
				unify.getDuplicates());
		this.getSender().tell(
				new UnifyResult(contact, new ArrayList<Exception>()));
	}

	public void mapRowToContact(MapMessage toMap) {
		List<Exception> ex = new ArrayList<Exception>();
		Contact contact = new Contact();

		for (int i = 0; i < toMap.getMapper().size(); i++) {
			Setter setter = toMap.getMapper().get(i);

			String value = toMap.getRow()[i];

			try {
				setter.set(contact, value);
			} catch (IllegalAccessException e) {
				// e.printStackTrace();
				ex.add(e);
			} catch (IllegalArgumentException e) {
				// e.printStackTrace();
				ex.add(e);
			} catch (InvocationTargetException e) {
				// e.printStackTrace();
				ex.add(e);
			} catch (ValidationException e) {
				// e.printStackTrace();
				ex.add(e);
			}

		}

		this.getSender().tell(new MapperResult(contact, ex));
	}

}
