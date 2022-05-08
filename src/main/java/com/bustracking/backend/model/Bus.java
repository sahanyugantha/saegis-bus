package com.bustracking.backend.model;

import org.springframework.stereotype.Component;

@Component
public class Bus {
    private String owner_name;
    private String owner_email;
    private String owner_mobile;
    private String bus_id;
    private String bus_no;
    private String route;
    private String destination;
    private String gps_serial;
    private String gps_imei;
    private String location_set;

    public String getLocation_set() {
        return location_set;
    }

    public void setLocation_set(String location_set) {
        this.location_set = location_set;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_email() {
        return owner_email;
    }

    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }

    public String getOwner_mobile() {
        return owner_mobile;
    }

    public void setOwner_mobile(String owner_mobile) {
        this.owner_mobile = owner_mobile;
    }



    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getBus_no() {
        return bus_no;
    }

    public void setBus_no(String bus_no) {
        this.bus_no = bus_no;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getGps_serial() {
        return gps_serial;
    }

    public void setGps_serial(String gps_serial) {
        this.gps_serial = gps_serial;
    }

    public String getGps_imei() {
        return gps_imei;
    }

    public void setGps_imei(String gps_imei) {
        this.gps_imei = gps_imei;
    }
}
