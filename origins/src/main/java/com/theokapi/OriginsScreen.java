package com.theokapi;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class OriginsScreen extends Screen {
    public OriginsScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        Button buttonWidget = Button.builder(Component.literal("Hello"), (_) -> {})
                .bounds(40, 40, 120, 20).build();


        this.addRenderableWidget(buttonWidget);
    }
}
