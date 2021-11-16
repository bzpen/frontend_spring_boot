package com.xiazhi.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Sample {
    private int sampleId;
    private String sampleNumber;
    private int spiderId;
    private int userId;
    private String sampleGenetic;
    private String sampleTime;
    private String sampleLocations;
    private String sampleJdu;
    private String sampleWdu;
    private String sampleList;
    private int sampleLength;
    private String sampleInstruct;

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleNumber() {
        return sampleNumber;
    }

    public void setSampleNumber(String sampleNumber) {
        this.sampleNumber = sampleNumber;
    }

    public int getSpiderId() {
        return spiderId;
    }

    public void setSpiderId(int spiderId) {
        this.spiderId = spiderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSampleGenetic() {
        return sampleGenetic;
    }

    public void setSampleGenetic(String sampleGenetic) {
        this.sampleGenetic = sampleGenetic;
    }

    public String getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(String sampleTime) {
        this.sampleTime = sampleTime;
    }

    public String getSampleLocations() {
        return sampleLocations;
    }

    public void setSampleLocations(String sampleLocations) {
        this.sampleLocations = sampleLocations;
    }

    public String getSampleJdu() {
        return sampleJdu;
    }

    public void setSampleJdu(String sampleJdu) {
        this.sampleJdu = sampleJdu;
    }

    public String getSampleWdu() {
        return sampleWdu;
    }

    public void setSampleWdu(String sampleWdu) {
        this.sampleWdu = sampleWdu;
    }

    public String getSampleList() {
        return sampleList;
    }

    public void setSampleList(String sampleList) {
        this.sampleList = sampleList;
    }

    public int getSampleLength() {
        return sampleLength;
    }

    public void setSampleLength(int sampleLength) {
        this.sampleLength = sampleLength;
    }

    public String getSampleInstruct() {
        return sampleInstruct;
    }

    public void setSampleInstruct(String sampleInstruct) {
        this.sampleInstruct = sampleInstruct;
    }
}
