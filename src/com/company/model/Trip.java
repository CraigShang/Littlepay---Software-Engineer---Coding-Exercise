package com.company.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trip {
    private Date startDate;
    private Date endDate;
    private long durationSec;
    private String fromStopId;
    private String toStopId;
    private double chargeAmount;
    private String companyId;
    private String busId;
    private String pan;
    private TripStatus status;

    private String dateFormatString = "dd-MM-yyyy HH:mm:ss";

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(long durationSec) {
        this.durationSec = durationSec;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public String generateEntry() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormatString);
        StringBuffer buffer =  new StringBuffer();
        buffer.append(simpleDateFormat.format(startDate));
        if(endDate != null){
        buffer.append(",");
        buffer.append(simpleDateFormat.format(endDate));
        }
        buffer.append(",");
        buffer.append(durationSec);
        buffer.append(",");
        buffer.append(fromStopId);
        buffer.append(",");
        buffer.append(toStopId);
        buffer.append(",");
        buffer.append(chargeAmount);
        buffer.append(",");
        buffer.append(companyId);
        buffer.append(",");
        buffer.append(busId);
        buffer.append(",");
        buffer.append(pan);
        buffer.append(",");
        buffer.append(status);
        return buffer.toString();
    }
}
