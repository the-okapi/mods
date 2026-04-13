package com.theokapi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class OriginsScreen extends Screen {
    public OriginsScreen(Component title) {
        super(title);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

        Identifier bookTexture = Identifier.fromNamespaceAndPath(Origins.MOD_ID, "textures/gui/book.png");

        graphics.blit(RenderPipelines.GUI_TEXTURED, bookTexture, (width - 146)/2, 10, 0, 0, 146, 180, 146, 180);

        Font font = Minecraft.getInstance().font;

        graphics.text(font, "Enderian", (width - 146)/2 + 20, 25, 0xFF000000, false);

        Button buttonWidget = Button.builder(Component.literal("Done"),
                        (_) -> Minecraft.getInstance().setScreen(null))
                .bounds((width - 200)/2, 200, 200, 20).build();

        this.addRenderableWidget(buttonWidget);
    }
}
