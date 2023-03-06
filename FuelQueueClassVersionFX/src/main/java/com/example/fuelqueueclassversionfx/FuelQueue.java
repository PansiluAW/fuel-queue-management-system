//import packages
package com.example.fuelqueueclassversionfx;

public class FuelQueue {
    //create attributes
    private static int fuelstock = 6600;
    private String firstName;
    private String secondName;
    private String vehicleNo;
    private String requiredLiters;
    private int fuelQueueIncome;
    private int nullcount = 0;
    private boolean fuelQueueFullStatus;

    //create objects of the passenger class for each passenger of the fuel queue
    Passenger passenger1 = new Passenger();
    Passenger passenger2 = new Passenger();
    Passenger passenger3 = new Passenger();
    Passenger passenger4 = new Passenger();
    Passenger passenger5 = new Passenger();
    Passenger passenger6 = new Passenger();

    //list of passenger objects
    Passenger[] passengersArr = {passenger1, passenger2, passenger3, passenger4, passenger5, passenger6};


    public void getPassengerDetails() {
        //display passenger details
        System.out.format("%10s %25s %25s %25s\n", passenger1.getFirstName(), passenger1.getSecondName(), passenger1.getVehicleNo(), passenger1.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger2.getFirstName(), passenger2.getSecondName(), passenger2.getVehicleNo(), passenger2.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger3.getFirstName(), passenger3.getSecondName(), passenger3.getVehicleNo(), passenger3.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger4.getFirstName(), passenger4.getSecondName(), passenger4.getVehicleNo(), passenger4.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger5.getFirstName(), passenger5.getSecondName(), passenger5.getVehicleNo(), passenger5.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger6.getFirstName(), passenger6.getSecondName(), passenger6.getVehicleNo(), passenger6.getRequiredLiters());
    }

    public void getEmptyQueues() {
        //display queue with details if the queue is not full
        if (passenger1.getFirstName() == null || passenger2.getFirstName() == null || passenger3.getFirstName() == null || passenger4.getFirstName() == null || passenger5.getFirstName() == null || passenger6.getFirstName() == null) {
            System.out.format("%10s %10s %10s %10s %10s %10s\n", passenger1.getFirstName(), passenger2.getFirstName(), passenger2.getFirstName(), passenger3.getFirstName(), passenger4.getFirstName(), passenger5.getFirstName(), passenger6.getFirstName());
        }
    }
    public int getQueueNullcount() {
        //get the count of empty spots in the fuel queue
        nullcount = 0;
        for (Passenger passenger : passengersArr) {
            if (passenger.getFirstName() == null) {
                nullcount++;
            }
        }
        return nullcount;
    }
    public int getFuelQueueIncome(){
        //get income of the fuel queue
        fuelQueueLiterCount();
        return this.fuelQueueIncome;
    }
    public void setFuelQueueFullStatus() {
        //set fuel queue status
        this.fuelQueueFullStatus = passenger6.fuelQueueFullStatus();
    }
    public boolean getFuelQueueFullStatus(){
        //get the status whether the fuel queue is full or not
        if (this.fuelQueueFullStatus){
            return true;
        }else return false;
    }

    public void addcustomer(String firstName, String secondName, String vehicleNo, String requiredLiters) {
        //add details of the customer to relevant locations of the empty passenger spot
        for (Passenger passenger : passengersArr) {
            if (passenger == null) {
                passenger.setFirstName(firstName);
                passenger.setSecondName(secondName);
                passenger.setVehicleNo(vehicleNo);
                passenger.setRequiredLiters(requiredLiters);
            }
        }
    }

    public int removeFromQueueLocation(int locationFromQueue) {
        int fuelRequiredBackToStock = 0;
        //get the required fuel amount from the customer to be removed and add it back to the fuel stock
        if (passengersArr[locationFromQueue - 1].getFirstName() != null) {
            if(passengersArr[locationFromQueue - 1].getRequiredLiters().matches("[0-9]+")) {
                fuelRequiredBackToStock = Integer.parseInt(passengersArr[locationFromQueue - 1].getRequiredLiters());
            }
        }
        //swap the select passenger attributes to null values
        passengersArr[locationFromQueue - 1].setFirstName(null);
        passengersArr[locationFromQueue - 1].setSecondName(null);
        passengersArr[locationFromQueue - 1].setVehicleNo(null);
        passengersArr[locationFromQueue - 1].setRequiredLiters(null);

        //rearrange the passengers after the removed passenger position of the fuel queue
        for (int count = 0; count < 6; count++) {
            if (passengersArr[count].getFirstName() == null) {
                for (int subCount = count; subCount < 6; subCount++) {
                    //swap values except for the last values which is to be null
                    if (passengersArr[subCount].getFirstName() != null && passengersArr[subCount] != passengersArr[5] || passengersArr[subCount] == passengersArr[count]) {
                        passengersArr[subCount].setFirstName(passengersArr[subCount + 1].getFirstName());
                        passengersArr[subCount].setSecondName(passengersArr[subCount + 1].getSecondName());
                        passengersArr[subCount].setVehicleNo(passengersArr[subCount + 1].getVehicleNo());
                        passengersArr[subCount].setRequiredLiters(passengersArr[subCount + 1].getRequiredLiters());
                    }else{
                        passengersArr[subCount].setFirstName(null);
                        passengersArr[subCount].setSecondName(null);
                        passengersArr[subCount].setVehicleNo(null);
                        passengersArr[subCount].setRequiredLiters(null);
                    }
                }
                break;
            }
        }
        return fuelRequiredBackToStock;
    }
    public void fuelQueueLiterCount(){
        //get total fuel liter required by the customers to calculate the income
        for (Passenger passenger: passengersArr) {
            if (!passenger.getRequiredLiters().equals("null") || passenger.getRequiredLiters().equals(null)) {
                this.fuelQueueIncome += Integer.parseInt(passenger.getRequiredLiters());
            }
        }
    }
    public boolean fuelQueueFullStatus(){
        //get status of the last passenger of the queue (passenger 6)
        if (passenger6.getFirstName() != null){
            return true;
        }else return false;
    }

    public static int getFuelStock() {
        //get fuel stock of the fuel center
        return fuelstock;
    }

    public static void updateFuelStock(int updateFuelStock) {
        //update fuel stock with a new stock amount
        fuelstock += updateFuelStock;
        System.out.print("Fuel stock added successfully");
    }
    public static void updateFuelStockAfterLoad(int updateFuelStockWithCustomerLoadCount) {
        //deduce fuel stock
        fuelstock -= updateFuelStockWithCustomerLoadCount;
    }

}
