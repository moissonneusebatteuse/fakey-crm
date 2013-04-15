package uk.co.tangentlabs.crm.actors.strategy.unification;

import java.util.List;

import uk.co.tangentlabs.entities.Contact;

public class NaiveEmailUnificationStrategy extends UnificationStrategy{

	@Override
	public String getPartitionKey(Contact contact) {
		// TODO Auto-generated method stub
		return contact.getEmail();
	}

	@Override
	public Contact merge(List<Contact> contacts) {
		// TODO Auto-generated method stub
		return contacts.get(0);
	}
	
}
