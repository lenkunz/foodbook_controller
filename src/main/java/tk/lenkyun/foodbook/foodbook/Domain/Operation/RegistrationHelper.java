package tk.lenkyun.foodbook.foodbook.Domain.Operation;

import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.UserAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.FoodbookType;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoContent;

/**
 * Created by lenkyun on 5/11/2558.
 */
public class RegistrationHelper implements FoodbookType {
    // User
    private UserAuthenticationInfo authenticationInfo;
    private String username;
    // Profile
    private String firstname, lastname;
    private PhotoContent profilePicture;
    private PhotoContent coverPicture;
    private String facebookToken;

    public UserAuthenticationInfo getAuthenticationInfo() {
        return authenticationInfo;
    }

    public RegistrationHelper setAuthenticationInfo(UserAuthenticationInfo authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegistrationHelper setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public RegistrationHelper setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public RegistrationHelper setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public PhotoContent getProfilePicture() {
        return profilePicture;
    }

    public RegistrationHelper setProfilePicture(PhotoContent profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public PhotoContent getCoverPicture() {
        return coverPicture;
    }

    public RegistrationHelper setCoverPicture(PhotoContent coverPicture) {
        this.coverPicture = coverPicture;
        return this;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }
}
