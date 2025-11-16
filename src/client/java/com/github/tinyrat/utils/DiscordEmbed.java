package com.github.tinyrat.utils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.github.tinyrat.Entry;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DiscordEmbed {
    private List<String> list;
    private String title;

    public DiscordEmbed(String title) {
        this.title = title;
        this.list = new ArrayList<>();
    }

    public void addln(String text) {
        this.list.add(text);
    }

    private String getTitle() {
        return title;
    }

    private String getDescription() {
        return String.join("\n", this.list);
    }

    private JsonObject getFooter() {
        JsonObject footer = new JsonObject();
        footer.addProperty("text", Entry.MOD_ID);
        return footer;
    }

    public JsonObject toJson() {
        JsonObject embedJson = new JsonObject();

        embedJson.add("footer", this.getFooter());
        embedJson.addProperty("title", this.getTitle());
        embedJson.addProperty("timestamp", Instant.now().toString());
        embedJson.addProperty("description", this.getDescription());

        return embedJson;
    }
}
