package uk.co.tangentlabs.crm.actors.validator;

import org.apache.commons.validator.GenericValidator;


public class EmailValidator extends Validator{
	@Override
	public boolean isValid(String value) {
		// TODO Auto-generated method stub
		return GenericValidator.isEmail(value);
	}

	@Override
	public String message(String value) {
		// TODO Auto-generated method stub
		return "Not a valid Email";
	}
	
}