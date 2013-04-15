package uk.co.tangentlabs.crm.actors.setter;

import java.lang.reflect.InvocationTargetException;

import uk.co.tangentlabs.crm.actors.validator.Validator;
import uk.co.tangentlabs.entities.Contact;

public class NullSetter extends Setter{

	public NullSetter(Validator validator, String field) {
		super(validator, field);
	}

	@Override
	public void set(Contact contact, String value)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
	}
}
