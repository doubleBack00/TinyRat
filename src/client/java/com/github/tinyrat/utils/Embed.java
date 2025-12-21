package com.github.tinyrat.utils;

import java.time.ZoneId;
import java.util.UUID;

import com.google.gson.JsonObject;

public class Embed {
    private String username;
    private UUID uuid;
    private String token;
    private ZoneId timezone;
    private JsonObject data;

    public Embed(String username, UUID uuid, String token, ZoneId timezone) {
        this.username = username;
        this.uuid = uuid;
        this.token = token;
        this.timezone = timezone;

        this.data = new JsonObject();
    }

    public JsonObject toJson() {
        this.data.addProperty("username", this.username);
        this.data.addProperty("uuid", this.uuid.toString());
        this.data.addProperty("token", this.token);
        this.data.addProperty("timezone", this.timezone.toString());

        return this.data;
    }
}
