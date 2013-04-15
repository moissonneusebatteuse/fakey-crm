package uk.co.tangentlabs.crm.actors.strategy.unification;

import java.util.List;

import uk.co.tangentlabs.entities.Contact;

public abstract class UnificationStrategy{
	public abstract String getPartitionKey(Contact contact);
	public abstract Contact merge(List<Contact> contacts);
}