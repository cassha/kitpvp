package dev.notcacha.kitpvp.core.scoreboard.module;

import dev.notcacha.kitpvp.api.scoreboard.ScoreboardSetup;
import dev.notcacha.kitpvp.api.scoreboard.properties.ScoreboardProperties;
import dev.notcacha.kitpvp.core.scoreboard.DefaultScoreboardSetup;
import dev.notcacha.kitpvp.core.scoreboard.properties.DefaultScoreboardProperties;
import dev.notcacha.scoreboard.api.ScoreboardManager;
import dev.notcacha.scoreboard.core.SimpleScoreboardManager;
import me.yushust.inject.AbstractModule;
import me.yushust.inject.Provides;

import javax.inject.Singleton;

public class ScoreboardModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ScoreboardProperties.class).to(DefaultScoreboardProperties.class).singleton();
        bind(ScoreboardSetup.class).to(DefaultScoreboardSetup.class).singleton();
    }

    @Provides
    @Singleton
    public ScoreboardManager provideScoreboardManager() {
        return new SimpleScoreboardManager();
    }
}
