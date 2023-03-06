public class FuelQueue {
    private static int fuelstock = 6600;
    private String firstName;
    private String secondName;
    private String vehicleNo;
    private String requiredLiters;
    private int fuelQueueIncome;
    private int nullcount = 0;
    private boolean fuelQueueFullStatus;

    Passenger passenger1 = new Passenger();
    Passenger passenger2 = new Passenger();
    Passenger passenger3 = new Passenger();
    Passenger passenger4 = new Passenger();
    Passenger passenger5 = new Passenger();
    Passenger passenger6 = new Passenger();
    Passenger[] passengersArr = {passenger1, passenger2, passenger3, passenger4, passenger5, passenger6};


    public void getPassengerDetails() {
        System.out.format("%10s %25s %25s %25s\n", passenger1.getFirstName(), passenger1.getSecondName(), passenger1.getVehicleNo(), passenger1.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger2.getFirstName(), passenger2.getSecondName(), passenger2.getVehicleNo(), passenger2.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger3.getFirstName(), passenger3.getSecondName(), passenger3.getVehicleNo(), passenger3.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger4.getFirstName(), passenger4.getSecondName(), passenger4.getVehicleNo(), passenger4.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger5.getFirstName(), passenger5.getSecondName(), passenger5.getVehicleNo(), passenger5.getRequiredLiters());
        System.out.format("%10s %25s %25s %25s\n", passenger6.getFirstName(), passenger6.getSecondName(), passenger6.getVehicleNo(), passenger6.getRequiredLiters());
    }

    public void getEmptyQueues() {
        if (passenger1.getFirstName() == null || passenger2.getFirstName() == null || passenger3.getFirstName() == null || passenger4.getFirstName() == null || passenger5.getFirstName() == null || passenger6.getFirstName() == null) {
            System.out.format("%10s %10s %10s %10s %10s %10s\n", passenger1.getFirstName(), passenger2.getFirstName(), passenger2.getFirstName(), passenger3.getFirstName(), passenger4.getFirstName(), passenger5.getFirstName(), passenger6.getFirstName());
        }
    }
    public int getQueueNullcount() {
        nullcount = 0;
        for (Passenger passenger : passengersArr) {
            if (passenger.getFirstName() == null) {
                nullcount++;
            }
        }
        return nullcount;
    }
    public int getFuelQueueIncome(){
        fuelQueueLiterCount();
        return this.fuelQueueIncome;
    }
    public void setFuelQueueFullStatus() {
        this.fuelQueueFullStatus = passenger6.fuelQueueFullStatus();
    }
    public boolean getFuelQueueFullStatus(){
        if (this.fuelQueueFullStatus){
            return true;
        }else return false;
    }

    public void addcustomer(String firstName, String secondName, String vehicleNo, String requiredLiters) {
        for (Passenger passenger : passengersArr) {
            if (passenger == null) {
                passenger.setFirstName(firstName);
                passenger.setSecondName(secondName);
                passenger.setVehicleNo(vehicleNo);
                passenger.setRequiredLiters(requiredLiters);
            }
        }
    }

    public void removeFromQueueLocation(int locationFromQueue) {
        passengersArr[locationFromQueue - 1].setFirstName(null);
        passengersArr[locationFromQueue - 1].setSecondName(null);
        passengersArr[locationFromQueue - 1].setVehicleNo(null);
        passengersArr[locationFromQueue - 1].setRequiredLiters(null);

        for (int count = 0; count < 6; count++) {
            if (passengersArr[count].getFirstName() == null) {
                for (int subCount = count; subCount < 6; subCount++) {
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
    }
    public void fuelQueueLiterCount(){
        for (Passenger passenger: passengersArr) {
            if (passenger.getRequiredLiters() != null) {
                this.fuelQueueIncome += Integer.parseInt(passenger.getRequiredLiters());
            }
        }
    }
    public boolean fuelQueueFullStatus(){
        if (passenger6.getFirstName() != null){
            return true;
        }else return false;
    }

    public static int getFuelStock() {
        return fuelstock;
    }

    public static void deduceFuelStock() {
        fuelstock -= 10;
    }

    public static void increaseFuelStock() {
        fuelstock += 10;
    }

    public static void updateFuelStock(int updateFuelStock) {
        fuelstock += updateFuelStock;
    }
}