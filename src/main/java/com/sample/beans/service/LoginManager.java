package com.sample.beans.service;

import com.sample.beans.model.User;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginManager {


    @ManagedProperty(value = "#{user}")
    private User user;
    private String email;
    private String password;

    public LoginManager() {

    }


    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("loginManager", this);
        return "/resources/templates/chartPage.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }


    public  boolean isLogged(){
        return (user.getEmail().equals(email)
                && user.getPassword().equals(password));
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}
