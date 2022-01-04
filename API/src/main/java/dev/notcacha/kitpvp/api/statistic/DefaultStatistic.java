package dev.notcacha.kitpvp.api.statistic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.beans.ConstructorProperties;

@JsonSerialize(as = Statistic.class)
public class DefaultStatistic implements Statistic {

    private int amount;

    public DefaultStatistic() {
        this.amount = 0;
    }

    @ConstructorProperties({
            "amount"
    })
    public DefaultStatistic(int amount) {
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
