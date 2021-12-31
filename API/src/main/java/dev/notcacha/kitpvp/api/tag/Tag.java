package dev.notcacha.kitpvp.api.tag;

import dev.notcacha.kitpvp.api.model.Model;
import org.jetbrains.annotations.Nullable;

public interface Tag extends Model {

    /**
     * @return The prefix of this {@link Tag}
     */

    String getPrefix();

    /**
     * Change the prefix of tag.
     * @param prefix new prefix has been changed.
     */

    void setPrefix(String prefix);

    /**
     * @return The suffix of this {@link Tag}
     */

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

    @Nullable String getListName();

    /**
     * Change the list name of this tag.
     * @param listName new name has been set.
     */

    void setListName(@Nullable String listName);
}
