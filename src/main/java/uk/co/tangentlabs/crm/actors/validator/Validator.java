package uk.co.tangentlabs.crm.actors.validator;

public abstract class Validator{
	public abstract boolean isValid(String value);
	public abstract String message(String value);
}
