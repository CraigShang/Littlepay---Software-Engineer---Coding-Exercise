package com.company.utility;

import com.company.model.Tap;
import com.company.model.TapType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TapRecordFormatter {

    private String dateFormatString = "dd-MM-yyyy HH:mm:ss";

    public Tap format(String line) {
        String[] fields = line.split(",");
        if (fields.length != 7) {
            return null;
        }
        Tap tap = new Tap();
        tap.setId(fields[0]);
        try {
            tap.setDate(new SimpleDateFormat(dateFormatString).parse(fields[1]));
        } catch (ParseException e) {
            return null;
        }
        if (fields[2].equals("ON")) {
            tap.setTapType(TapType.ON);
        } else if (fields[2].equals("OFF")) {
            tap.setTapType(TapType.OFF);
        } else {
            return null;
        }
        tap.setStopId(fields[3]);
        tap.setCompanyId(fields[4]);
        tap.setBusId(fields[5]);
        tap.setPan(fields[6]);
        return tap;
    }
}
