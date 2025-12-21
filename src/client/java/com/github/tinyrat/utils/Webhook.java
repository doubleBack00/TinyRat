package com.github.tinyrat.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;

public class Webhook {
    private final String webhookUrl;
    private final HttpClient client;

    public Webhook(String webhookUrl) {
        this.webhookUrl = webhookUrl;
        this.client = HttpClient.newHttpClient();
    }

    private HttpRequest buildRequest(JsonObject json) throws Exception {
        return HttpRequest.newBuilder()
            .uri(new URI(webhookUrl))
            .header("Content-Type", "application/json; charset=UTF-8")
            .POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
            .build();
    }

    private void send(JsonObject json) {
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

    public void sendEmbed(Embed embed) {
        this.send(embed.toJson());
    }
}
