package dev.notcacha.kitpvp.core.message;

import javax.inject.Inject;
import javax.inject.Named;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.file.YamlFile;
import org.bukkit.ChatColor;

import java.util.List;

public class CoreMessageHandler implements MessageHandler {

    @Inject @Named("message") private YamlFile messageFile;

    @Override
    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', messageFile.getString(path, "Message not exists!"));
    }

    @Override
    public List<String> getMessages(String path) {
        List<String> list = messageFile.getStringList(path);

        list.replaceAll(message -> message.replace(
                message,
                ChatColor.translateAlternateColorCodes('&', message)
        ));

        return list;
    }

    @Override
    public boolean getBoolean(String path) {
        return messageFile.getBoolean(path);
    }
}
