package dev.notcacha.kitpvp.api.chat.formatter;

import org.bukkit.entity.Player;

public interface ChatFormatter {

    /**
     * Set format from text
     *
     * @param holder from get options from set
     * @param format the format of chat.
     * @param text   has been set format
     * @return {@link String} with the established format
     */

    String format(Player holder, String format, String text);

    /**
     * Set placeholders from text
     *
     * @param text   has been set placeholders
     * @param holder from get values
     * @return text from format
     */

    String setPlaceholders(Player holder, String text);

    /**
     * Merge other format from this format
     *
     * @param formatter has been merging.
     */

    ChatFormatter merge(ChatFormatter formatter);

}
