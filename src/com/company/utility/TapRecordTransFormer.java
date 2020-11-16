package com.company.utility;

import com.company.model.Tap;
import com.company.model.Trip;
import com.company.model.TripStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TapRecordTransFormer {

    public List<Trip> transform(List<Tap> tapList) {
        HashMap<String, List<Tap>> tapPerPassenger = groupByPassenger(tapList);
        List<Trip> trips = new ArrayList<>();
        for (List<Tap> passengerTap : tapPerPassenger.values()) {
            trips.addAll(transformTapToTrips(passengerTap));
        }
        return trips;
    }

    private List<Trip> transformTapToTrips(List<Tap> passengerTap) {
        List<Trip> trips = new ArrayList<>();
        ChargeCalculator calculator = new ChargeCalculator();
        for (int i = 0; i < passengerTap.size(); i++) {
            Tap start = passengerTap.get(i);
            Tap end = i + 1 < passengerTap.size() ? passengerTap.get(i + 1) : null;
            if (start.getTapType().isOn()) {
                Trip newTrip = new Trip();
                newTrip.setStartDate(start.getDate());
                newTrip.setBusId(start.getBusId());
                newTrip.setCompanyId(start.getCompanyId());
                newTrip.setPan(start.getPan());
                newTrip.setFromStopId(start.getStopId());
                if (end != null && end.getTapType().isOff()
                        && start.getBusId().equals(end.getBusId())
                        && start.getCompanyId().equals(end.getCompanyId())) {
                    newTrip.setEndDate(end.getDate());
                    newTrip.setToStopId(end.getStopId());
                    newTrip.setDurationSec((end.getDate().getTime() - start.getDate().getTime()) / 1000);
                    if (start.getStopId().equals(end.getStopId())) {
                        //cancelled
                        newTrip.setChargeAmount(0);
                        newTrip.setStatus(TripStatus.CANCELLED);
                    } else {
                        //complete
                        newTrip.setChargeAmount(calculator.calculate(start.getStopId(), end.getStopId()));
                        newTrip.setStatus(TripStatus.COMPLETE);
                    }
                    i += 1;
                } else {
                    //incomplete
                    newTrip.setChargeAmount(calculator.calculate(start.getStopId(), null));
                    newTrip.setStatus(TripStatus.INCOMPLETE);
                }
                trips.add(newTrip);
            }
        }
        return trips;
    }

    private HashMap<String, List<Tap>> groupByPassenger(List<Tap> tapList) {
        HashMap<String, List<Tap>> tapPerPassenger = new HashMap<>();
        //group the tap by pan
        for (Tap tap : tapList) {
            List<Tap> passengerTaps = null;
            if (tapPerPassenger.containsKey(tap.getPan())) {
                passengerTaps = tapPerPassenger.get(tap.getPan());
                //insert in an ascending order
                boolean insert = false;
                for (int i = 0; i < passengerTaps.size(); i++) {
                    if (tap.getDate().before(passengerTaps.get(i).getDate())) {
                        passengerTaps.add(i, tap);
                        insert = true;
                    }
                }
                if (!insert) {
                    passengerTaps.add(tap);
                }
            } else {
                passengerTaps = new ArrayList<>();
                passengerTaps.add(tap);
                tapPerPassenger.put(tap.getPan(), passengerTaps);
            }
        }
        return tapPerPassenger;
    }
}
