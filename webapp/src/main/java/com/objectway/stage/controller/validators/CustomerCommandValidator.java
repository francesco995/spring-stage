package com.objectway.stage.controller.validators;

import com.objectway.stage.controller.CustomerCommand;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomerCommandValidator implements Validator, MessageSourceAware {

    private MessageSourceAccessor message;

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {



        CustomerCommand customerCommand = (CustomerCommand) o;

        ValidationUtils.rejectIfEmpty(errors, "customer.name", "errors.customers.required",
                new Object[]{message.getMessage("customers.details.name")});
        ValidationUtils.rejectIfEmpty(errors, "customer.lastName", "errors.customers.required",
                new Object[]{message.getMessage("customers.details.lastName")});
        ValidationUtils.rejectIfEmpty(errors, "customer.birthDate", "errors.customers.required",
                new Object[]{message.getMessage("customers.details.birthdate")});

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        message = new MessageSourceAccessor(messageSource);
    }
}
