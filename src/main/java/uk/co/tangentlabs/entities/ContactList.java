package uk.co.tangentlabs.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@AccessType("field")
public class ContactList {
	@Id 
	@Generated(value = GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="list_id_gen") 
	@SequenceGenerator(initialValue=0, name="list_id_gen")
	private int id;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="contact_contactlist",joinColumns={@JoinColumn(name="contactlist_id")},inverseJoinColumns={@JoinColumn(name="contact_id")})
	List<Contact> contacts;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
