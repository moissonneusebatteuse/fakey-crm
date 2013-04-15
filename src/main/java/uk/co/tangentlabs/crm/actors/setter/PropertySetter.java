package uk.co.tangentlabs.crm.actors.setter;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import uk.co.tangentlabs.crm.actors.validator.ValidationException;
import uk.co.tangentlabs.crm.actors.validator.Validator;
import uk.co.tangentlabs.entities.Contact;

public class PropertySetter extends Setter {
	final Method setterMethod;
	final Class propertyType;
	public PropertySetter(Validator validator, String field) throws IntrospectionException {
		super(validator, field);
		setterMethod = (new PropertyDescriptor(field, Contact.class)).getWriteMethod();
		propertyType = setterMethod.getParameterTypes()[0];
	}

	@Override
	public void set(Contact contact, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ValidationException {
		super.set(contact, value);
		//We only really have strings for this, so
		setterMethod.invoke(contact, new Object[]{value});
	}
	
}
