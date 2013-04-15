package uk.co.tangentlabs.crm.actors.setter;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;


import uk.co.tangentlabs.crm.actors.validator.Validator;
import uk.co.tangentlabs.entities.Contact;

public class SetterFactory{
	public static Setter getSetter(Validator validator, String field){
		if (field == null){
			return new NullSetter(null, null);
		}
		
		try {
			(new PropertyDescriptor(field, Contact.class)).getWriteMethod();
			return new PropertySetter(validator, field);
		} catch (IntrospectionException ie){
			return new MetaSetter(validator, field);
		}
	}

	
}
