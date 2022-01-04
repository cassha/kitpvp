package dev.notcacha.kitpvp.api.tag.create;

public interface TagCreate {

    default boolean createTag(String tagId) {
        return createTag(tagId, "", "");
    }

    default boolean createTag(String tagId, String prefix) {
        return createTag(tagId, prefix, "");
    }

    boolean createTag(String tagId, String prefix, String suffix);
}
