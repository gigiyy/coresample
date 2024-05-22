package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxCostTests {

    @Test
    public void test() {
        List<Integer> cost = List.of(0, 5, 4, 3, 2);
        List<String> labels = List.of("legal", "illegal", "legal", "illegal", "legal");
        assertThat(maxCost(cost, labels, 2)).isEqualTo(9);
    }

    public int maxCost(List<Integer> cost, List<String> labels, int dailyCount) {
        System.out.println(dailyCount);
        int maxCost = 0;
        int count = 0;
        int dailyCost = 0;
        for (int idx = 0; idx < cost.size(); idx++) {
            String label = labels.get(idx);
            int cst = cost.get(idx);
            System.out.println("label: " + label + " cost: " + cst + " accCost:" + dailyCost);
            if (count < dailyCount) {
                dailyCost += cst;
                if (label.equals("legal")) {
                    count++;
                }
                if (count == dailyCount) {
                    if (dailyCost > maxCost) {
                        maxCost = dailyCost;
                    }
                    count = 0;
                    dailyCost = 0;
                }
            }
        }
        return maxCost;
    }
}
