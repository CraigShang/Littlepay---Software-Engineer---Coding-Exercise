package com.company.utility;

import java.util.HashMap;

public class ChargeCalculator {
    private HashMap<String, Double> defaultChargePerStop;

    public ChargeCalculator() {
        defaultChargePerStop = new HashMap<>();
        defaultChargePerStop.put("Stop1", 7.3);
        defaultChargePerStop.put("Stop2", 5.5);
        defaultChargePerStop.put("Stop3", 7.3);
        defaultChargePerStop.put("Stop1Stop2", 3.25);
        defaultChargePerStop.put("Stop2Stop3", 5.5);
        defaultChargePerStop.put("Stop1Stop3", 7.3);
    }

    public double calculate(String beginStopId, String endStopId) {
        if (beginStopId != null) {
            if (endStopId == null) {
                return defaultChargePerStop.get(beginStopId);
            } else {
                for (String combination : defaultChargePerStop.keySet()) {
                    if (combination.contains(beginStopId) && combination.contains(endStopId)) {
                        return defaultChargePerStop.get(combination);
                    }
                }
            }
        }
        return 0;
    }
}
