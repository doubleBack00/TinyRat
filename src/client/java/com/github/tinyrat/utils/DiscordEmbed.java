package com.github.tinyrat.utils;

import java.util.ArrayList;
import java.util.List;

public class DiscordEmbed {
    private List<String> list;
    private String title;

    public DiscordEmbed(String title) {
        this.title = title;
        this.list = new ArrayList<>();
    }

    public void add(String text) {
        this.list.add(text + "\n");
    }

    public String getText() {
        return String.join("\n", this.list);
    }

    public String getTitle() {
        return title;
    }

    public String render() {
        return getText();
    }
}
