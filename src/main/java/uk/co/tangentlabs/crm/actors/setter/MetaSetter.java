package uk.co.tangentlabs.crm.actors.setter;

import java.lang.reflect.InvocationTargetException;

import uk.co.tangentlabs.crm.actors.validator.ValidationException;
import uk.co.tangentlabs.crm.actors.validator.Validator;
import uk.co.tangentlabs.entities.Contact;

public class MetaSetter extends Setter {

	public MetaSetter(Validator validator, String field) {
		super(validator, field);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void set(Contact contact, String value)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, ValidationException {
		super.set(contact, value);
		contact.getFields().put(field, value);
	}
	
}
