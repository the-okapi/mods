package com.unlimitedstuffltd.dialog;

import com.unlimitedstuffltd.world.WorldEvent;

import java.util.function.Consumer;

public class WorldDialogConversation {
    private final WorldEvent WORLD_EVENT;
    private final Consumer<String> SEND_MESSAGE;

    public WorldDialogConversation(WorldEvent worldEvent, Consumer<String> sendMessage) {
        WORLD_EVENT = worldEvent;
        SEND_MESSAGE = sendMessage;
    }

    public void playerMessage(String message) {
        SEND_MESSAGE.accept("Huh? I was sleeping. Could you say that again?");
    }
}
