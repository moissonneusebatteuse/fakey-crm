package uk.co.tangentlabs.crm.actors.validator;

import org.apache.commons.validator.GenericValidator;

public  class URLValidator extends Validator{

	@Override
	public boolean isValid(String value) {
		// TODO Auto-generated method stub
		return GenericValidator.isUrl(value);
	}

	@Override
	public String message(String value) {
		// TODO Auto-generated method stub
		return "Not a valid URL";
	}
	
}
