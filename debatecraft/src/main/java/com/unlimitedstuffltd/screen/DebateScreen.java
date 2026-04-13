package com.unlimitedstuffltd.screen;

import com.unlimitedstuffltd.dialog.WorldDialogConversation;
import com.unlimitedstuffltd.world.WorldEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DebateScreen extends Screen {
    public record DebateMessage(boolean sentByWorld, String message) {}

    private final WorldDialogConversation CONVERSATION;

    private final List<DebateMessage> MESSAGES = new ArrayList<>();

    private void sendWorldMessage(String message) {
        MESSAGES.addFirst(new DebateMessage(true, message));
    }

    private void sendPlayerMessage() {
        MESSAGES.addFirst(new DebateMessage(false, "Hello"));
        CONVERSATION.playerMessage("Hello");
    }

    public DebateScreen(Component title, WorldEvent worldEvent) {
        super(title);
        CONVERSATION = new WorldDialogConversation(worldEvent, this::sendWorldMessage);
    }

    @Override
    protected void init() {
        int x = (width - 244) / 2;
        Minecraft minecraft = Minecraft.getInstance();
        EditBox editBox = new EditBox(minecraft.font, x, 20, 204, 20, Component.literal("Talk to the world"));
        Button sendButton = Button.builder(Component.literal("Send"), _ -> sendPlayerMessage()).bounds(x + 209, 20, 35, 20).build();
        this.addRenderableWidget(editBox);
        this.addRenderableWidget(sendButton);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractRenderState(graphics, mouseX, mouseY, a);

        Font font = Minecraft.getInstance().font;

        int x = (width - 244) / 2;

        for (int i = 0; i < MESSAGES.size(); i++) {
            if (i * 20 + 50 > height) {
                break;
            }
            DebateMessage message = MESSAGES.get(i);
            if (message.sentByWorld) {
                graphics.text(font, message.message, x + 244 - font.width(message.message), i * 20 + 50, 0xFFFFFFFF);
            } else {
                graphics.text(font, message.message, x, i * 20 + 50, 0xFFFFFFFF);
            }
        }
    }
}
