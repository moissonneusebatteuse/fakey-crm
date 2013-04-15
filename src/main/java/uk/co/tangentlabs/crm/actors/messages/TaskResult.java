package uk.co.tangentlabs.crm.actors.messages;

import java.util.List;

import uk.co.tangentlabs.entities.Contact;
import akka.util.Duration;

public class TaskResult {
	final List<Contact> contacts;
	final Duration duration;

	public TaskResult(List<Contact> contacts, Duration duration) {
		this.contacts = contacts;
		this.duration = duration;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public Duration getDuration() {
		return duration;
	}

}
