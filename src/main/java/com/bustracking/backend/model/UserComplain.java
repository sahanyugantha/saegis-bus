package com.bustracking.backend.model;

import org.springframework.stereotype.Component;

@Component
public class UserComplain {
    String complain_id;
    String user_id;
    String bus_no;
    String complain_title;
    String complain_body;
    String created_at;

    public String getComplain_id() {
        return complain_id;
    }

    public void setComplain_id(String complain_id) {
        this.complain_id = complain_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBus_no() {
        return bus_no;
    }

    public void setBus_no(String bus_no) {
        this.bus_no = bus_no;
    }

    public String getComplain_title() {
        return complain_title;
    }

    public void setComplain_title(String complain_title) {
        this.complain_title = complain_title;
    }

    public String getComplain_body() {
        return complain_body;
    }

    public void setComplain_body(String complain_body) {
        this.complain_body = complain_body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
