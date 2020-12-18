package com.dnyferguson.momentousercache.objects;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String ign;
    private String lowercaseIgn;
    private String headTexture;

    public User(UUID uuid, String ign, String lowercaseIgn, String headTexture) {
        this.uuid = uuid;
        this.ign = ign;
        this.lowercaseIgn = lowercaseIgn;
        this.headTexture = headTexture;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getIgn() {
        return ign;
    }

    public void setIgn(String ign) {
        this.ign = ign;
    }

    public String getHeadTexture() {
        return headTexture;
    }

    public void setHeadTexture(String headTexture) {
        this.headTexture = headTexture;
    }

    public String getLowercaseIgn() {
        return lowercaseIgn;
    }

    public void setLowercaseIgn(String lowercaseIgn) {
        this.lowercaseIgn = lowercaseIgn;
    }
}
