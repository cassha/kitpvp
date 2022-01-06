package dev.notcacha.kitpvp.api.tag;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jetbrains.annotations.Nullable;

import java.beans.ConstructorProperties;

@JsonSerialize(as = Tag.class)
public class DefaultTag implements Tag {

    private final String id;
    private String prefix;
    private String suffix;
    private String listName;

    @ConstructorProperties({
            "_id", "prefix", "suffix", "list_name"
    })
    public DefaultTag(String id, String prefix, String suffix, String listName) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
        this.listName = listName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public String getListName() {
        return listName;
    }

    @Override
    public void setListName(@Nullable String listName) {
        this.listName = listName;
    }


}
