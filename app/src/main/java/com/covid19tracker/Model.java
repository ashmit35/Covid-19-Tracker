package com.covid19tracker;

public class Model {
    private String confirmed,active,deceased,recovered,delta_confirmed,delta_deceased,delta_recovered;
    private String state,city,notes;

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDeceased() {
        return deceased;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDelta_confirmed() {
        return delta_confirmed;
    }

    public void setDelta_confirmed(String delta_confirmed) {
        this.delta_confirmed = delta_confirmed;
    }

    public String getDelta_deceased() {
        return delta_deceased;
    }

    public void setDelta_deceased(String delta_deceased) {
        this.delta_deceased = delta_deceased;
    }

    public String getDelta_recovered() {
        return delta_recovered;
    }

    public void setDelta_recovered(String delta_recovered) {
        this.delta_recovered = delta_recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Model(String confirmed, String active, String recovered,String deceased,String state, String city) {
        this.confirmed = confirmed;
        this.active = active;
        this.deceased = deceased;
        this.recovered = recovered;
        this.state = state;
        this.city = city;
    }
}
