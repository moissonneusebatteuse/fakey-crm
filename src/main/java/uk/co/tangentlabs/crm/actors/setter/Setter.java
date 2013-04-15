package uk.co.tangentlabs.crm.actors.setter;

import java.lang.reflect.InvocationTargetException;

import uk.co.tangentlabs.crm.actors.validator.ValidationException;
import uk.co.tangentlabs.crm.actors.validator.Validator;
import uk.co.tangentlabs.entities.Contact;

public abstract class Setter{
	private final Validator validator;
	protected final String field;
	
	public Setter(Validator validator, String field){
		this.validator = validator;
		this.field = field;
	}
	public void set(Contact contact, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ValidationException{
		if (!validate(value)){
			throw new ValidationException(validator.message(value));
		}
	}
	public boolean validate(String value){
		return validator.isValid(value);
	}
	public Validator getValidator() {
		return validator;
	}
	public String getField() {
		return field;
	}
	
	
}
