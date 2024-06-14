package com.example.springsample.codesignal;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentTests {

    @Test
    public void testInvestmentCalculator() {
        assertThat(solution(new String[]{"BRAND,APPL,3", "INVEST,1,APPL,1000", "NEXT_TIME_SLOT"}))
                .isEqualTo(new String[]{"1,APPL,1030"});
    }

    record Invest(String id, String name, int rate, int amount) {
        Invest next() {
            return new Invest(this.id, this.name, this.rate, (int) (amount * (1 + rate / 100.0)));
        }

        Invest cancel(int cancelAmt) {
            return new Invest(this.id, this.name, this.rate, (int) (amount - cancelAmt * (1 + rate / 100.0)));
        }
    }

    String[] solution(String[] commands) {
        Map<String, Integer> brands = new HashMap<>();
        Map<String, Invest> invests = new HashMap<>();
        for (String command : commands) {
            String[] cmd = command.split(",");
            switch (cmd[0]) {
                case "BRAND" -> brands.put(cmd[1], Integer.parseInt(cmd[2]));
                case "INVEST" ->
                        invests.put(cmd[1], new Invest(cmd[1], cmd[2], brands.get(cmd[2]), Integer.parseInt(cmd[3])));
                case "NEXT_TIME_SLOT" -> invests.replaceAll((k, v) -> v.next());
                case "PAYMENT_FAILURE_NOTICE" -> {
                    String id = cmd[1];
                    invests.replace(id, invests.get(id).cancel(Integer.parseInt(cmd[2])));
                }
            }
        }
        return invests.values().stream()
                .map(invest -> String.format("%s,%s,%d", invest.id, invest.name, invest.amount))
                .toArray(String[]::new);
    }
}
