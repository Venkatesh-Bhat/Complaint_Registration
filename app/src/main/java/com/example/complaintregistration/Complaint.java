package com.example.complaintregistration;

import androidx.annotation.Nullable;

public class Complaint {
    String name,address,date,compType,comp,status,compID;
    Complaint(){}
    Complaint(String name,String address,String date,String compType, String comp,String status,String compID){
        this.name = name;
        this.comp = comp;
        this.compType = compType;
        this.date = date;
        this.address = address;
        this.status = status;
        this.compID = compID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getComp() {
        return comp;
    }

    public String getCompType() {
        return compType;
    }

    public String getDate() {
        return date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }
}

