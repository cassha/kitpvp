package dev.notcacha.kitpvp.core.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public class CountdownTimerUtil implements Runnable {

    private final Plugin plugin;
    private final int seconds;
    private final Consumer<CountdownTimerUtil> everySecond;
    private Integer assignedTaskId;
    private int secondsLeft;
    private boolean beforeTimerExecuted = false;
    private Runnable beforeTimer;
    private final Runnable afterTimer;

    public CountdownTimerUtil(
            Plugin plugin,
            int seconds,
            Runnable beforeTimer,
            Consumer<CountdownTimerUtil> everySecond,
            Runnable afterTimer
    ) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;
        this.beforeTimer = beforeTimer;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }

    public CountdownTimerUtil(
            Plugin plugin,
            int seconds,
            Consumer<CountdownTimerUtil> everySecond,
            Runnable afterTimer
    ) {
        this.plugin = plugin;
        this.seconds = seconds;
        this.secondsLeft = seconds;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }

    @Override
    public void run() {
        if (secondsLeft < 1) {
            if (afterTimer != null) {
                afterTimer.run();
            }
            if (assignedTaskId != null) {
                cancelCountdown();
            }
            return;
        }

        if (!beforeTimerExecuted && secondsLeft == seconds && beforeTimer != null) {
            beforeTimer.run();
            beforeTimerExecuted = true;
        }

        everySecond.accept(this);

        secondsLeft--;
    }

    public int getTotalSeconds() {
        return seconds;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public Integer getAssignedTaskId() {
        return assignedTaskId;
    }

    public void cancelCountdown() {
        Bukkit.getScheduler().cancelTask(assignedTaskId);
    }

    public void scheduleTimer() {
        this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }
}