package uk.co.tangentlabs.crm.actors.validator;

import org.apache.commons.validator.GenericValidator;


public  class IsNotEmptyValidator extends Validator{
	@Override
	public boolean isValid(String value) {
		// TODO Auto-generated method stub
		return !GenericValidator.isBlankOrNull(value);//.isEmail((valueString);
	}
	@Override
	public String message(String value) {
		// TODO Auto-generated method stub
		return "Should not be empty";
	}
}