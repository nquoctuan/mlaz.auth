package com.mlaz.api.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "MLUserConnection")
public class MlazUserConnection {

    private String id;

    private final String userId;
    private final String providerId;
    private final String providerUserId;
    private final int rank;
    private final String displayName;
    private final String profileUrl;
    private final String imageUrl;
    private final String accessToken;
    private final String secret;
    private final String refreshToken;
    private final Long expireTime;

    public MlazUserConnection(String userId, String providerId, String providerUserId, int rank, String displayName, String profileUrl, String imageUrl, String accessToken, String secret, String refreshToken, Long expireTime) {
        this.userId = userId;
        this.providerId = providerId;
        this.providerUserId = providerUserId;
        this.rank = rank;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.secret = secret;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

    public String toString() {
        return
                "userId = " + userId +
                        ", providerId = " + providerId +
                        ", providerUserId = " + providerUserId +
                        ", rank = " + rank +
                        ", displayName = " + displayName +
                        ", profileUrl = " + profileUrl +
                        ", imageUrl = " + imageUrl +
                        ", accessToken = " + accessToken +
                        ", secret = " + secret +
                        ", refreshToken = " + refreshToken +
                        ", expireTime = " + expireTime;
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

    @DynamoDBAttribute
    public String getProviderId() {
        return providerId;
    }

    @DynamoDBAttribute
    public String getProviderUserId() {
        return providerUserId;
    }

    @DynamoDBAttribute
    public int getRank() {
        return rank;
    }

    @DynamoDBAttribute
    public String getDisplayName() {
        return displayName;
    }

    @DynamoDBAttribute
    public String getProfileUrl() {
        return profileUrl;
    }

    @DynamoDBAttribute
    public String getImageUrl() {
        return imageUrl;
    }

    @DynamoDBAttribute
    public String getAccessToken() {
        return accessToken;
    }

    @DynamoDBAttribute
    public String getSecret() {
        return secret;
    }

    @DynamoDBAttribute
    public String getRefreshToken() {
        return refreshToken;
    }

    @DynamoDBAttribute
    public Long getExpireTime() {
        return expireTime;
    }
}
