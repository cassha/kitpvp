package dev.notcacha.kitpvp.api.message;

import java.util.List;

public interface MessageHandler {

    /**
     * Get the message of message file.
     * @param path identifier of get message.
     * @return The message in {@link String} format
     */

    String getMessage(String path);

    /**
     * Get the messages of message file.
     * @param path identifier of get message.
     * @return The message in {@link List} format
     */

    List<String> getMessages(String path);

    /**
     * Get the boolean value of path.
     * @param path path identifier of get message.
     * @return The boolean value.
     */

    boolean getBoolean(String path);

}
