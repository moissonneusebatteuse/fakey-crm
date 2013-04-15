package uk.co.tangentlabs.entities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import net.backtothefront.HstoreUserType;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@AccessType("field")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Contact {
	@Id 
	@Generated(value = GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contact_id_seq") 
	@SequenceGenerator(initialValue=0, name="contact_id_seq")
	private int id;
	
	private String salutation;
	
	private String firstname;
	private String lastname;
	
	private boolean doNotContact = false;
	private boolean doNotEmail = false;
	private boolean doNotMail = false;
	private boolean doNotText = false;
	private boolean active = true;
	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	@Index(name = "contact_addressid_index")
	private long addressId;
	private String city;
	private String county;
	private String postcode;
	@Index(name = "contact_email_index")
	private String email;
	@Index(name = "contact_mobile_index")
	private String mobile;
	
	private Date created;
	
	private Date updated;
	
	@Type(type = "hstore")
    @Column(columnDefinition = "hstore")
    public Map<String, String> fields = new HashMap<String, String>();
	
	@ManyToMany(mappedBy="contacts")
	List<ContactList> lists;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public boolean isDoNotContact() {
		return doNotContact;
	}

	public void setDoNotContact(boolean doNotContact) {
		this.doNotContact = doNotContact;
	}

	public boolean isDoNotEmail() {
		return doNotEmail;
	}

	public void setDoNotEmail(boolean doNotEmail) {
		this.doNotEmail = doNotEmail;
	}

	public boolean isDoNotMail() {
		return doNotMail;
	}

	public void setDoNotMail(boolean doNotMail) {
		this.doNotMail = doNotMail;
	}

	public boolean isDoNotText() {
		return doNotText;
	}

	public void setDoNotText(boolean doNotText) {
		this.doNotText = doNotText;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public List<ContactList> getLists() {
		return lists;
	}

	public void setLists(List<ContactList> lists) {
		this.lists = lists;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
}
