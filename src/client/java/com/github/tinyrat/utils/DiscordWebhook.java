package com.github.tinyrat.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DiscordWebhook {
    private final String webhookUrl;
    private final HttpClient client;
    private final String userAgent;

    public DiscordWebhook(String webhookUrl) {
        this.webhookUrl = webhookUrl;
        this.userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
        this.client = HttpClient.newHttpClient();
    }

    private HttpRequest buildRequest(JsonObject json) throws Exception {
        return HttpRequest.newBuilder()
            .uri(new URI(webhookUrl))
            .header("Content-Type", "application/json; charset=UTF-8")
            .header("User-Agent", userAgent)
            .POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
            .build();
    }

    public void send(JsonObject json) {
        try {
            HttpRequest request = buildRequest(json);
            client.sendAsync(request, HttpResponse.BodyHandlers.discarding())
                  .exceptionally(e -> {
                      e.printStackTrace();
                      return null;
                  });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmbed(DiscordEmbed embed) {
        JsonObject payload = new JsonObject();

        JsonArray embeds = new JsonArray();
        embeds.add(embed.toJson());

        payload.add("embeds", embeds);
        this.send(payload);
    }
}
