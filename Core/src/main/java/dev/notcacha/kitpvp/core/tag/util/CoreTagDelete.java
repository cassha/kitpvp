package dev.notcacha.kitpvp.core.tag.util;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.processor.ModelDeleteProcessor;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.delete.TagDelete;
import dev.notcacha.kitpvp.api.tag.DefaultTag;

import javax.inject.Inject;

public class CoreTagDelete implements TagDelete {

    @Inject private ModelDeleteProcessor<Tag> modelDeleteProcessor;
    @Inject private ObjectCache<Tag> tagObjectCache;

    @Override
    public boolean delete(String tagId) {
        if (!tagObjectCache.ifPresent(tagId)) {
            return false;
        }

        return modelDeleteProcessor.deleteSync(new DefaultTag(tagId, null, null, null));
    }
}
