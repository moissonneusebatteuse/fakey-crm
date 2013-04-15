package uk.co.tangentlabs.crm.actors.messages;

import java.util.List;

import uk.co.tangentlabs.crm.actors.strategy.unification.UnificationStrategy;
import uk.co.tangentlabs.entities.Contact;

public class UnificationMessage {
	private final UnificationStrategy unificationStrategy;
	private final List<Contact> duplicates;

	public UnificationMessage(UnificationStrategy unificationStrategy,
			List<Contact> duplicates) {
		this.unificationStrategy = unificationStrategy;
		this.duplicates = duplicates;
	}

	public UnificationStrategy getUnificationStrategy() {
		return unificationStrategy;
	}

	public List<Contact> getDuplicates() {
		return duplicates;
	}
}
