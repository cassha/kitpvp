package dev.notcacha.kitpvp.core.translation.interceptor;

import dev.notcacha.kitpvp.api.util.Colorize;
import me.yushust.message.format.MessageInterceptor;
import org.jetbrains.annotations.NotNull;

public class ColorMessageInterceptor implements MessageInterceptor {
    @Override
    public @NotNull String intercept(String s) {
        return Colorize.colorize(s);
    }
}
