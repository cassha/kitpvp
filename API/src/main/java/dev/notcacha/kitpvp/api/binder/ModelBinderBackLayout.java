package dev.notcacha.kitpvp.api.binder;

import dev.notcacha.kitpvp.api.model.Model;

public interface ModelBinderBackLayout<T extends Model> {

    /**
     * @return The back instance class {@link ModelBinder} from bind other properties.
     */

    ModelBinder<T> back();

}
