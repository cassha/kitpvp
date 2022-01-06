package dev.notcacha.kitpvp.core.chat.formatter;

import dev.notcacha.kitpvp.api.chat.formatter.ChatFormatter;
import dev.notcacha.kitpvp.core.util.PlaceholderAPIUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashSet;
import java.util.Set;

public class DefaultChatFormatter implements ChatFormatter {

    private final Set<ChatFormatter> formatterSet = new LinkedHashSet<>();

    @Override
    public String format(Player holder, String format,  String text) {
        return setPlaceholders(holder, PlaceholderAPIUtil.detectAndApply(holder, format).replace("%message%", text));
    }

    @Override
    public String setPlaceholders(Player holder, String text) {

        for (ChatFormatter formatter : formatterSet) {
            text = formatter.setPlaceholders(holder, text);
        }

        return text.replace("%player_name%", holder.getName());
    }

    @Override
    public ChatFormatter merge(ChatFormatter formatter) {
        this.formatterSet.add(formatter);

        return this;
    }
}