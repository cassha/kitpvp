package dev.notcacha.kitpvp.core.chat;

import dev.notcacha.kitpvp.api.chat.ChatManager;

public class DefaultChatManager implements ChatManager {

    private boolean muted = false;

    @Override
    public boolean isMuted() {
        return muted;
    }

    @Override
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
