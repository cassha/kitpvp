package dev.notcacha.kitpvp.api.chat;

public interface ChatManager {

    /**
     * Detect the chat is mute.
     * @return The muted of chat state in {@link Boolean}
     */

    boolean isMuted();

    /**
     * Change the state of muted chat.
     * @param muted new state of muted chat.
     */

    void setMuted(boolean muted);

}
