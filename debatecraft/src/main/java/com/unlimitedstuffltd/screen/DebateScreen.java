package com.unlimitedstuffltd.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DebateScreen extends Screen {
    public DebateScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        int x = (width - 244) / 2;
        Minecraft minecraft = Minecraft.getInstance();
        EditBox editBox = new EditBox(minecraft.font, x, 20, 204, 20, Component.literal("Talk to the world"));
        Button sendButton = Button.builder(Component.literal("Send"), btn -> {}).bounds(x + 209, 20, 35, 20).build();
        this.addRenderableWidget(editBox);
        this.addRenderableWidget(sendButton);
    }
}
