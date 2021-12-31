package dev.notcacha.kitpvp.core.tag;

import dev.notcacha.kitpvp.api.tag.Tag;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DefaultTag implements Tag {

    private final String id;
    private String prefix;
    private String suffix;
    private String listName;

    public DefaultTag(String id, String prefix, String suffix, String listName) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
        this.listName = listName;
    }

    public DefaultTag(Map<String, Object> map) {
        this(
                (String) map.get("_id"),
                (String) map.get("prefix"),
                (String) map.get("suffix"),
                (String) map.get("list_name")
        );
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

    @Nullable
    @Override
    public String getListName() {
        return listName;
    }

    @Override
    public void setListName(@Nullable String listName) {
        this.listName = listName;
    }


    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("_id", id);
        map.put("prefix", prefix);
        map.put("suffix", suffix);
        map.put("list_name", listName);

        return map;
    }
}
