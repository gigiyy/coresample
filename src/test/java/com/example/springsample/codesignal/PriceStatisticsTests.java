package com.example.springsample.codesignal;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class PriceStatisticsTests {
    @Test
    public void testPriceStatistics() {
        assertThat(solution(new String[]{"APPL,178.0", "APPL,125.00"}))
                .isEqualTo(new String[]{"APPL,178.0,178.0,125.00,125.00"});
    }

    static class Price {
        String name;
        String high;
        String open;
        String low;
        String close;
        private double dHigh;
        private double dLow;

        Price(String name, String price) {
            this.name = name;
            this.high = price;
            this.open = price;
            this.low = price;
            this.close = price;
            dHigh = dLow = Double.parseDouble(price);
        }

        void addSpot(String price) {
            double spot = Double.parseDouble(price);
            this.close = price;
            if (spot > dHigh) {
                this.high = price;
                dHigh = spot;
            }
            if (spot < dLow) {
                this.low = price;
                dLow = spot;
            }
        }
    }

    String[] solution(String[] csv) {
        Map<String, Price> map = new HashMap<>();
        for (String line : csv) {
            String[] spot = line.split(",");
            String name = spot[0];
            String price = spot[1];
            if (!map.containsKey(name)) {
                map.put(name, new Price(name, price));
            } else {
                map.get(name).addSpot(price);
            }
        }
        return map.values().stream()
                .map(v -> String.format("%s,%s,%s,%s,%s", v.name, v.high, v.open, v.low, v.close))
                .toArray(String[]::new);
    }


}
