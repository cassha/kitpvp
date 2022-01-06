package dev.notcacha.kitpvp.api.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.notcacha.kitpvp.api.model.Model;
import org.jetbrains.annotations.Nullable;

@JsonDeserialize(as = DefaultTag.class)
public interface Tag extends Model {

    /**
     * @return The prefix of this {@link Tag}
     */

    @JsonProperty("prefix")
    String getPrefix();

    /**
     * Change the prefix of tag.
     * @param prefix new prefix has been changed.
     */

    void setPrefix(String prefix);

    /**
     * @return The suffix of this {@link Tag}
     */

    @JsonProperty("suffix")
    String getSuffix();

    /**
     * Change the suffix of tag.
     * @param suffix new suffix has been changed.
     */

    void setSuffix(String suffix);

    /**
     * The name has been view in tag gui list.
     * @return The list name of this {@link Tag}
     */

    @JsonProperty("list_name")
    String getListName();

    /**
     * Change the list name of this tag.
     * @param listName new name has been set.
     */

    void setListName(@Nullable String listName);
}
