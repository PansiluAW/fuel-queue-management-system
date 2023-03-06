public class Passenger {
    private String firstName = null;
    private String secondName = null;
    private String vehicleNo = null;
    private String requiredLiters = null;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setName(String name){
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
        if (!this.firstName.equals(null)){
            return true;
        }else return false;
    }
}