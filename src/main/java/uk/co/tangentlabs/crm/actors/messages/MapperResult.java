package uk.co.tangentlabs.crm.actors.messages;

import java.util.List;

import uk.co.tangentlabs.entities.Contact;

public class MapperResult {
	final Contact contact;
	final List<Exception> exception;

	public MapperResult(Contact contact, List<Exception> exception) {
		this.contact = contact;
		this.exception = exception;
	}

	public Contact getContact() {
		return contact;
	}

	public List<Exception> getException() {
		return exception;
	}
}
