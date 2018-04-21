package com.sample.beans.validators;

import com.sample.beans.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean(name="emailValidator")
@RequestScoped
public class EmailValidator implements Validator {


    @ManagedProperty(value = "#{user}")
    private User user;

    private static final String EMAIL_PATTERN = "^[\\w]+[\\-]?[\\w]+[@][\\w]{1,7}\\.(com|ru|ua|net)";

    public EmailValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String email = (String)value;
        if (!(email.matches(EMAIL_PATTERN))) {
            FacesMessage msg = new FacesMessage("E-mail validation failed.",
                    "Некорректный email адрес!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }else if(!(user.getEmail().equals(email))){
            FacesMessage msg = new FacesMessage("E-mail validation failed.",
                    "Пользователь с таким логином не найден!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
