package com.sasd13.proadmin.android.model.user;

public class UserUpdate {

    private User user;
    private CredentialUpdate credentials;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CredentialUpdate getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialUpdate credentials) {
        this.credentials = credentials;
    }
}
