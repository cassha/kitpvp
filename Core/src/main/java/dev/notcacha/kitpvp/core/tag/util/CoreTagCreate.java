package dev.notcacha.kitpvp.core.tag.util;

import dev.notcacha.kitpvp.api.cache.ObjectCache;
import dev.notcacha.kitpvp.api.model.processor.ModelFindProcessor;
import dev.notcacha.kitpvp.api.tag.Tag;
import dev.notcacha.kitpvp.api.tag.TagCreate;
import dev.notcacha.kitpvp.core.tag.DefaultTag;

import javax.inject.Inject;
import java.util.Optional;

public class CoreTagCreate implements TagCreate {

    @Inject private ModelFindProcessor<Tag> tagModelFindProcessor;
    @Inject private ObjectCache<Tag> tagObjectCache;

    @Override
    public boolean createTag(String tagId, String prefix, String suffix) {

        Optional<Tag> optionalTag = tagModelFindProcessor.findOneSync(tagId);

        if (!optionalTag.isPresent()) {
            tagObjectCache.addObject(
                    new DefaultTag(
                            tagId,
                            prefix,
                            suffix,
                            null
                    )
            );

            return true;
        }

        return false;
    }

}
