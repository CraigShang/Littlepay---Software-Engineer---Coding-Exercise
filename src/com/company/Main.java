package com.company;

import com.company.model.Tap;
import com.company.model.Trip;
import com.company.utility.TapRecordFormatter;
import com.company.utility.TapRecordTransFormer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader;
        BufferedWriter writer;
        try{
            reader = new BufferedReader(new FileReader("./taps.csv"));
            List<Tap> tapList = new ArrayList<>();
            TapRecordFormatter formatter = new TapRecordFormatter();
            //read record from the file
            String line = reader.readLine();
            while(line != null){
                Tap tap = formatter.format(line);
                if(tap!=null){
                    tapList.add(tap);
                }
                line = reader.readLine();
            }
            //transform records
            TapRecordTransFormer transFormer = new TapRecordTransFormer();
            List<Trip> tripList = transFormer.transform(tapList);
            //output to a csv
            writer = new BufferedWriter(new FileWriter("./trips.csv"));
            for(Trip eachTrip : tripList){
                System.out.println(eachTrip.generateEntry());
                writer.write(eachTrip.generateEntry());
                writer.newLine();
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
