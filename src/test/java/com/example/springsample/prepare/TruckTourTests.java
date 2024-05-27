package com.example.springsample.prepare;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TruckTourTests {

    @Test
    public void test() {
        assertThat(truckTour(List.of(List.of(1, 5), List.of(10, 3), List.of(3, 4)))).isEqualTo(1);
    }
    public int truckTour(List<List<Integer>> petrolpumps) {
        // Write your code here
        for (int start = 0; start < petrolpumps.size(); start++) {
            if (truckTourFrom(petrolpumps, start)) return start;
        }
        return -1;
    }

    public boolean truckTourFrom(List<List<Integer>> pumps, int start) {
        int n = pumps.size();
        int end = start == 0 ? n - 1 : start - 1;
        int petrol = 0;
        for (int idx = start; idx != end; idx = (idx + 1) % n) {
            List<Integer> pump = pumps.get(idx);
            int fill = pump.get(0);
            int next = pump.get(1);
            petrol = petrol + fill - next;
            if (petrol < 0) return false;
        }
        return true;
    }
}
