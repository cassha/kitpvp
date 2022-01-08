package dev.notcacha.kitpvp.core.message;

import javax.inject.Inject;
import javax.inject.Named;
import dev.notcacha.kitpvp.api.message.MessageHandler;
import dev.notcacha.kitpvp.core.file.YamlFile;
import dev.notcacha.kitpvp.api.util.Colorize;

import java.util.List;

public class CoreMessageHandler implements MessageHandler {

    @Inject @Named("message") private YamlFile messageFile;

    @Override
    public String getMessage(String path) {
        return Colorize.colorize(messageFile.getString(path, "&cMessage not exists!"));
    }

    @Override
    public List<String> getMessages(String path) {
        return Colorize.colorize(messageFile.getStringList(path));
    }

    @Override
    public boolean getBoolean(String path) {
        return messageFile.getBoolean(path);
    }
}
