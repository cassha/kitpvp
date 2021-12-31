package dev.notcacha.kitpvp.core.statistic;

import dev.notcacha.kitpvp.api.statistic.Statistic;

public class CoreStatistic implements Statistic {

    private int amount;

    public CoreStatistic() {
        this.amount = 0;
    }

    public CoreStatistic(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void add(int amount) {
        this.amount =+ amount;
    }

    @Override
    public void remove(int amount) {
        this.amount =- amount;
    }
}
