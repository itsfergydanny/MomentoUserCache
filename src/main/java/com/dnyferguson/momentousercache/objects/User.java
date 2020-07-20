package com.dnyferguson.momentousercache.objects;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String ign;
    private String headTexture;

    public User(UUID uuid, String ign, String headTexture) {
        this.uuid = uuid;
        this.ign = ign;
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
}
