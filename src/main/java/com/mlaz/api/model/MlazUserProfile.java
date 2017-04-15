package com.mlaz.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;

@DynamoDBTable(tableName = "MLUserProfile")
public class MlazUserProfile {

    private String id;
    private String userId;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public MlazUserProfile() {}

    public MlazUserProfile(String userId, String name, String firstName, String lastName, String email, String username) {
        this.userId = userId;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;

        fixName();
    }

    private void fixName() {
        if (name == null) {

            name = firstName;

            if (lastName != null) {
                if (name == null) {
                    name = lastName;
                } else {
                    name += " " + lastName;
                }
            }

            if (name == null) {
                name = email;
            }

            if (name == null) {
                name = username;
            }

            if (name == null) {
                name = "UNKNOWN";
            }
        }
    }

    public MlazUserProfile(String userId, MlazUserProfile up) {
        this.userId = userId;
        this.name = up.getName();
        this.firstName = up.getFirstName();
        this.lastName = up.getLastName();
        this.email = up.getEmail();
        this.username = up.getUsername();
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    public void setName(String val) {
        name = val;
    }

    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }

    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }

    public void setEmail(String val) {
        email = val;
    }
    @DynamoDBAttribute
    public String getPassword() {
        return password;
    }

    @DynamoDBAttribute
    public String getUsername() {
        return username;
    }

    public String toString() {
        return
                "name = " + name +
                        ", firstName = " + firstName +
                        ", lastName = " + lastName +
                        ", email = " + email +
                        ", username = " + username;
    }

    public static MlazUserProfile fromConnection(Connection<?> connection) {

        MlazUserProfile userProfile = new MlazUserProfile();

        if (connection != null) {
            UserProfile socialMediaProfile = connection.fetchUserProfile();
            userProfile.setEmail(socialMediaProfile.getEmail());
            userProfile.setName(socialMediaProfile.getFirstName() + " " + socialMediaProfile.getLastName());
        }

        return userProfile;
    }
}
