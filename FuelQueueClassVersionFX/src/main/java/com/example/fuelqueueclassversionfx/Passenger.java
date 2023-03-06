//import packages
package com.example.fuelqueueclassversionfx;

public class Passenger {
    //create attributes
    private String firstName = null;
    private String secondName = null;
    private String vehicleNo = null;
    private String requiredLiters = null;

    //provide accessors for the passenger attributes
    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public String getName() {
        return this.firstName + " " + this.secondName;
    }

    public String getVehicleNo() {
        return this.vehicleNo;
    }

    public String getRequiredLiters() {
        return this.requiredLiters;
    }

    //provide mutators for the passenger attributes
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setName(String name){
        //set first name and second name when the full name given to the method
        String[] splitNames = name.split(" ");
        this.firstName = splitNames[0];
        this.secondName = splitNames[1];
    }
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setRequiredLiters(String requiredLiters) {
        this.requiredLiters = requiredLiters;
    }

    public boolean fuelQueueFullStatus(){
        //return if the passenger is empty
        if (!this.firstName.equals(null)){
            return true;
        }else return false;
    }
}
