package com.sample.beans.validators;


import com.sample.beans.model.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class PasswordValidator implements Validator{

    @ManagedProperty(value = "#{user}")
    private User user;

    private static final String PASSWORD_PATTERN = "^[\\w]+[\\-]?[\\w]+";

    public PasswordValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String)value;
        if (!(password.matches(PASSWORD_PATTERN))) {
            FacesMessage msg = new FacesMessage("Password validation failed.",
                    "В пароле недопустимые символы!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }else if(!(user.getPassword().equals(password))){
            FacesMessage msg = new FacesMessage("Password validation failed.",
                    "Неверный пароль!");
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
