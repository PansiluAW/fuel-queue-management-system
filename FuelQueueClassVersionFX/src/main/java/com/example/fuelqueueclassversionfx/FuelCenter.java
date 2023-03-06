//import packages
package com.example.fuelqueueclassversionfx;
import com.example.fuelqueueclassversionfx.FuelQueue;
import com.example.fuelqueueclassversionfx.Passenger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class FuelCenter {

    public static void main(String[] args) throws IOException {
        //declare variables
        String firstName = "";
        String secondName = "";
        String vehicleNo = "";
        String requiredLiters = "";
        String option;
        int updateFuelStock = 0;
        int front = 1;
        int rear = 1;
        String[][] waitingQueue = new String[8][];
        int[] circularQueue = new int[2];

        Scanner scanner = new Scanner(System.in);

        //create objects for each queue
        FuelQueue queue1 = new FuelQueue();
        FuelQueue queue2 = new FuelQueue();
        FuelQueue queue3 = new FuelQueue();
        FuelQueue queue4 = new FuelQueue();
        FuelQueue queue5 = new FuelQueue();
        FuelQueue[] objArray = {queue1, queue2, queue3, queue4, queue5};

        Passenger[] queue1Passengers = {queue1.passenger1, queue1.passenger2, queue1.passenger3, queue1.passenger4, queue1.passenger5, queue1.passenger6};
        Passenger[] queue2Passengers = {queue2.passenger1, queue2.passenger2, queue2.passenger3, queue2.passenger4, queue2.passenger5, queue2.passenger6};
        Passenger[] queue3Passengers = {queue3.passenger1, queue3.passenger2, queue3.passenger3, queue3.passenger4, queue3.passenger5, queue3.passenger6};
        Passenger[] queue4Passengers = {queue4.passenger1, queue4.passenger2, queue4.passenger3, queue4.passenger4, queue4.passenger5, queue4.passenger6};
        Passenger[] queue5Passengers = {queue5.passenger1, queue5.passenger2, queue5.passenger3, queue5.passenger4, queue5.passenger5, queue5.passenger6};

        //loop until the user commands to exit the program
        while (true) {
            //display the menu for the operator to choose the options
            System.out.println("\nSelect a option (using numerics or characters given) ,\n ");
            System.out.println("\t100 or VFQ: View all Fuel Queues.");
            System.out.println("\t101 or VEQ: View all Empty Queues.");
            System.out.println("\t102 or ACQ: Add customer to a Queue.");
            System.out.println("\t103 or RCQ: Remove a customer from a Queue.");
            System.out.println("\t104 or PCQ: Remove a served customer.");
            System.out.println("\t105 or VCS: View Customers Sorted in alphabetical order");
            System.out.println("\t106 or SPD: Store Program Data into file.");
            System.out.println("\t107 or LPD: Load Program Data from file.");
            System.out.println("\t108 or STK: View Remaining Fuel Stock.");
            System.out.println("\t109 or AFS: Add Fuel Stock.");
            System.out.println("\t110 or IFQ: Display Total Fuel Queue Income.");
            System.out.println("\t999 or EXT: Exit the Program.");

            //get option as an input from the user
            System.out.print("\n\t\t>>>>  ");
            option = scanner.next();
            System.out.println();
            //call methods according to user's option choice
            switch (option) {
                case "100":
                case "VFQ":
                case "vfq":
                    viewAll(objArray, waitingQueue);
                    break;
                case "101":
                case "VEQ":
                case "veq":
                    viewEmptyQueues(objArray);
                    break;
                case "ACQ":
                case "102":
                case "acq":
                    rear = addCustomer(queue1, queue2, queue3, queue4, queue5, firstName, secondName, vehicleNo, requiredLiters, waitingQueue, scanner, objArray, front, rear);
                    break;
                case "RCQ":
                case "103":
                case "rcq":
                    front = removeFromLocation(objArray, scanner, front, rear, waitingQueue, queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers);
                    break;
                case "104":
                case "PCQ":
                case "pcq":
                    front = removeServedCustomer(scanner, objArray, front, rear, waitingQueue, queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers);
                    break;
                case "105":
                case "VCS":
                case "vcs":
                    sortFuelQueues(objArray, scanner);
                    break;
                case "106":
                case "SPD":
                case "spd":
                    storeToFile(queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers, waitingQueue, front, rear);
                    break;
                case "107":
                case "LPD":
                case "lpd":
                    circularQueue = loadFromFile(queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers, waitingQueue, front, rear, objArray);
                    front = circularQueue[0];
                    rear = circularQueue[1];
                    break;
                case "108":
                case "STK":
                case "stk":
                    System.out.print("Remaining Fuel Stock : " + String.valueOf(FuelQueue.getFuelStock()));
                    break;
                case "109":
                case "AFS":
                case "afs":
                    addFuelStock(updateFuelStock, scanner);
                    break;
                case "110":
                case "IFQ":
                case "ifq":
                    displayFuelQueueIncome(queue1, queue2, queue3, queue4, queue5);
                    break;
                case "111":
                case "JFX":
                case "jfx":
                    break;
                case "999":
                case "EXT":
                case "ext":
                    storeToFxmlSaveFile(queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers, waitingQueue);
                    //open fuel center gui application
                    FuelCenterApplication.main(null);
                    System.out.println("Program exited successfully.\nGood Bye.");
                    break;
                default:
                    //display message to the user for invalid inputs
                    System.out.println("Invalid numeric or character input! \n Please check the menu and re-enter option...");
            }
            System.out.println();
            //break out of loop as the user selects exit as the option
            if (option.equals("999") || option.equals("EXT") || option.equals("ext")) {
                break;
            }
        }
    }

    public static void viewAll(FuelQueue[] objArray, String[][] waitingQueue) {
        int count = 1;
        for (FuelQueue queues : objArray) {
            //display the data of the fuel queues individually as in a table view
            System.out.println("*__________________________________________________________________________________________________*");
            System.out.format("%50s %d\n", "FUEL QUEUE ", count);
            System.out.println("*__________________________________________________________________________________________________*");
            System.out.format("%15s %25s %25s %25s\n", "FIRST NAME", "SECOND NAME", "VEHICLE NUMBER", "REQUIRED LITERS");
            //call method to display passenger details for each queue
            queues.getPassengerDetails();
            System.out.println("*__________________________________________________________________________________________________*\n\n");
            System.out.printf("%50s\n\n", "++++++++");
            //fuel queue number
            count++;
        }
        int index = 1;
        //display waiting queue with customers.
        System.out.println("_______________________________\n_________WAITING QUEUE_________");
        for (String[] element: waitingQueue) {
            if (element != null) {
                System.out.print((index+1)+". ");
                for (int subCount = 0; subCount<4; subCount++){
                    //display data in a detailed view in the waiting list
                    if (subCount == 0){
                        System.out.print("First Name : ");
                    }else if (subCount == 1){
                        System.out.print("Second Name : ");
                    }else if (subCount == 2){
                        System.out.print("Vehicle Number : ");
                    }else if (subCount == 3){
                        System.out.print("Required Liters : ");
                    }
                    //display data with the correct label before it.
                    System.out.println(element[subCount]);
                }
                index++;
                System.out.println("");
            }
        }
        //display message if the waiting list is empty
        if (Arrays.stream(waitingQueue).allMatch(Objects::isNull)){
            System.out.println("\n+The Waiting list is empty.+");
        }
        System.out.println("_______________________________");
    }

    public static void viewEmptyQueues(FuelQueue[] objArray) {
        int count = 1;
        //display queues with empty sports remaining in them
        System.out.print("FUEL QUEUES WITH AN EMPTY SPOT~\n---------------------------------------\n");
        //loop through queues to check if there are any such queues
        for (FuelQueue queues : objArray) {
            System.out.printf("Queue %5d %5s ", count, ":");
            queues.getEmptyQueues();
            count++;
        }
        System.out.print("---------------------------------------\n");
    }

    public static int removeFromLocation(FuelQueue[] objArray, Scanner scanner, int front, int rear, String[][] waitingQueue, Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers) {
        //prompt user to give the queue number, and it's customer specific location
        //input of the queue number is taken
        System.out.print("Enter the queue from which you want to remove customer : ");
        String queue = scanner.next();
        //input of the location of customer is taken
        System.out.print("Enter the customer's queue location : ");
        String locationFromQueue = scanner.next();
        //check whether the option is validly inputted
        //queue number validation
        if (queue.equals("1") || queue.equals("2") || queue.equals("3") || queue.equals("4") || queue.equals("5")) {
            //specific customer location validation
            if (locationFromQueue.equals("1") || locationFromQueue.equals("2") || locationFromQueue.equals("3") || locationFromQueue.equals("4") || locationFromQueue.equals("5") || locationFromQueue.equals("6")) {
                //call method from queue objects to remove the respective elements and rearrange the elements without a gap between elements
                FuelQueue.updateFuelStock(objArray[Integer.parseInt(queue) - 1].removeFromQueueLocation(Integer.parseInt(locationFromQueue)));
                //display confirmation message
                System.out.println("\nCustomer removed from location " + locationFromQueue + " of queue " + queue);
            } else
                //display message relevant to invalid input
                System.out.println("\nInvalid fuel queue location... \nPlease try again after re-check\n");
            //update the circular queue after adding customer from the waiting queue to the specific customer removed queues
            circularQueueImplementAtRemove(queue, front, rear,waitingQueue, queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers);
            //update the circular queue starting point accordingly
            front++;
        } else
            //display message for invalid inputs
            System.out.println("\nFuel queue not in range(only queues inclusive from 1-5 available)... \nPlease try again after re-check\n");
        return front;
    }

    public static int removeServedCustomer(Scanner scanner, FuelQueue[] objArray,  int front, int rear, String[][] waitingQueue, Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers){
        //prompt user for queue number to remove served customer
        System.out.print("Enter the queue of the served customer : ");
        String queue = scanner.next();
        //check whether the queue number is valid
        if (queue.equals("1") || queue.equals("2") || queue.equals("3") || queue.equals("4") || queue.equals("5")) {
            //call method from queue objects to remove the first passenger elements and rearrange the elements without a gap between elements
            FuelQueue.updateFuelStock(objArray[Integer.parseInt(queue) - 1].removeFromQueueLocation(Integer.parseInt("1")));
            //display the conformation message
            System.out.println("\nServed Customer removed from fuel queue " + queue);
            //update the circular queue after adding customer from the waiting queue to the specific customer removed queues
            circularQueueImplementAtRemove(queue, front, rear,waitingQueue, queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers);
            //update the circular queue starting point accordingly
            front++;
        } else
            //display message for invalid inputs
            System.out.println("\nFuel queue not in range(only queues inclusive from 1-5 available)... \nPlease try again after re-check\n");
        return front;
    }

    public static void circularQueueImplementAtRemove(String queue, int front, int rear, String[][] waitingQueue, Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers){
        //check if the waiting queue is not empty
        if (!Arrays.stream(waitingQueue).allMatch(Objects::isNull)) {
            /* insert data into the queues according to user's choice of queue
            and transfer data about customers from waiting list as necessary */
            if (queue.equals("1")) {
                for (Passenger element : queue1Passengers) {
                    //transfer data into respective places of object queues
                    if (element.getFirstName() == null) {
                        element.setFirstName(waitingQueue[front][0]);
                        element.setSecondName(waitingQueue[front][1]);
                        element.setVehicleNo(waitingQueue[front][2]);
                        element.setRequiredLiters(waitingQueue[front][3]);
                    }
                }
            } else if (queue.equals("2")) {
                for (Passenger element : queue2Passengers) {
                    if (element.getFirstName() == null) {
                        element.setFirstName(waitingQueue[front][0]);
                        element.setSecondName(waitingQueue[front][1]);
                        element.setVehicleNo(waitingQueue[front][2]);
                        element.setRequiredLiters(waitingQueue[front][3]);
                    }
                }
            } else if (queue.equals("3")) {
                for (Passenger element : queue3Passengers) {
                    if (element.getFirstName() == null) {
                        element.setFirstName(waitingQueue[front][0]);
                        element.setSecondName(waitingQueue[front][1]);
                        element.setVehicleNo(waitingQueue[front][2]);
                        element.setRequiredLiters(waitingQueue[front][3]);
                    }
                }
            } else if (queue.equals("4")) {
                for (Passenger element : queue4Passengers) {
                    if (element.getFirstName() == null) {
                        element.setFirstName(waitingQueue[front][0]);
                        element.setSecondName(waitingQueue[front][1]);
                        element.setVehicleNo(waitingQueue[front][2]);
                        element.setRequiredLiters(waitingQueue[front][3]);
                    }
                }
            } else if (queue.equals("5")) {
                for (Passenger element : queue5Passengers) {
                    if (element.getFirstName() == null) {
                        element.setFirstName(waitingQueue[front][0]);
                        element.setSecondName(waitingQueue[front][1]);
                        element.setVehicleNo(waitingQueue[front][2]);
                        element.setRequiredLiters(waitingQueue[front][3]);
                    }
                }
            }
            //set element in waiting queue to null after extracting data into the main queues
            waitingQueue[front] = null;
            //display confirm message that the waiting queue has been updated
            System.out.println("Waiting Queue has been updated.");

        }
    }

    public static int addCustomer(FuelQueue queue1, FuelQueue queue2, FuelQueue queue3, FuelQueue queue4, FuelQueue queue5, String firstName, String secondName, String vehicleNo, String requiredLiters, String[][] waitingQueue, Scanner scanner, FuelQueue[] objArray, int front, int rear) {
        int highNullCount = 0;
        boolean exists = false;
        boolean allAlpha = true;
        //check which of the queues has the most null values to add the new customer to that postition
        if (queue1.getQueueNullcount() >= queue2.getQueueNullcount() && queue1.getQueueNullcount() >= queue3.getQueueNullcount() && queue1.getQueueNullcount() >= queue4.getQueueNullcount() && queue1.getQueueNullcount() >= queue5.getQueueNullcount()) {
            highNullCount = 0;
        } else if (queue2.getQueueNullcount() >= queue1.getQueueNullcount() && queue2.getQueueNullcount() >= queue3.getQueueNullcount() && queue2.getQueueNullcount() >= queue4.getQueueNullcount() && queue2.getQueueNullcount() >= queue5.getQueueNullcount()) {
            highNullCount = 1;
        } else if (queue3.getQueueNullcount() >= queue1.getQueueNullcount() && queue3.getQueueNullcount() >= queue2.getQueueNullcount() && queue3.getQueueNullcount() >= queue4.getQueueNullcount() && queue3.getQueueNullcount() >= queue5.getQueueNullcount()) {
            highNullCount = 2;
        } else if (queue4.getQueueNullcount() >= queue1.getQueueNullcount() && queue4.getQueueNullcount() >= queue2.getQueueNullcount() && queue4.getQueueNullcount() >= queue3.getQueueNullcount() && queue4.getQueueNullcount() >= queue5.getQueueNullcount()) {
            highNullCount = 3;
        } else if (queue5.getQueueNullcount() >= queue1.getQueueNullcount() && queue5.getQueueNullcount() >= queue2.getQueueNullcount() && queue5.getQueueNullcount() >= queue3.getQueueNullcount() && queue5.getQueueNullcount() >= queue4.getQueueNullcount()) {
            highNullCount = 4;
        }
        //loop till the user inputs valid data
        while (true) {
            //prompt user with new customer details
            System.out.printf("%s %5s", "Enter the below details,\nFirst Name of the customer", ":");
            firstName = scanner.next();
            System.out.printf("%s %4s", "Second Name of the customer", ":");
            secondName = scanner.next();
            //check if the name inputs has all alphabetical charcters to proceed further
            allAlpha = (firstName + secondName).matches("[a-zA-Z]+");
            if (!allAlpha) {
                //display message for invalid input
                System.out.println("\nInvalid format used for names...\nPlease re-check and try again.\n");
                continue;
            }
            //check whether name already consists in any queue
            for (int index = 0; index < 5; index++) {
                if (objArray[index].passenger1.getName().compareTo(firstName + " " + secondName) == 0) {
                    exists = true;
                } else if (objArray[index].passenger2.getName().compareTo(firstName + " " + secondName) == 0) {
                    exists = true;
                } else if (objArray[index].passenger3.getName().compareTo(firstName + " " + secondName) == 0) {
                    exists = true;
                } else if (objArray[index].passenger4.getName().compareTo(firstName + " " + secondName) == 0) {
                    exists = true;
                } else if (objArray[index].passenger5.getName().compareTo(firstName + " " + secondName) == 0) {
                    exists = true;
                } else if (objArray[index].passenger6.getName().compareTo(firstName + " " + secondName) == 0) {
                    exists = true;
                } else exists = false;
                if (exists == true) {
                    //display message (with queue number) for entering customer name that already exists in a queue
                    System.out.printf("Name already exists in queue %d. Please enter different customer details...\n", index + 1);
                    break;
                }
            }
            //proceed further if the name doesn't exist
            if (exists == false) break;
        }
        //prompt user for the vehicle number
        System.out.printf("%s %17s", "Vehicle Number", ":");
        vehicleNo = scanner.next();
        boolean allNumerics = true;
        while (true) {
            System.out.printf("%s %8s", "Required liters of fuel", ":");
            requiredLiters = scanner.next();
            //convert input to char type list to check whether each character is a numeric character
            char[] numerics = requiredLiters.toCharArray();
            //check if the fuel liters input has all numerics to proceed further
            for (char element : numerics) {
                if (!Character.isDigit(element)) {
                    //display message if the input has any non-numeric character
                    System.out.println("\nInvalid format used for liters required in numerics...\nPlease re-check and try again.\n");
                    allNumerics = false;
                    break;
                } else allNumerics = true; //proceed further if the user input is valid
            }
            //break out of loop if valid inputs given
            if (allNumerics) break;
                //jump into next loop cycle to prompt new inputs for required liters if invalid inputs given
            else continue;
        }
        //access waiting queue customers if the main queues are full
        if (queue5.fuelQueueFullStatus() == true) {
            //check if the waiting list is containing empty spots
            if (Arrays.stream(waitingQueue).allMatch(Objects::isNull)){
                waitingQueue[rear] = new String[]{firstName, secondName, vehicleNo, requiredLiters};
                rear++;
                //display confirmation message
                System.out.println("Waiting Queue has been updated with the new customer.");
            }else if (Arrays.stream(waitingQueue).allMatch(Objects::nonNull)) {
                //display message if the waiting queue is full
                System.out.print("Waiting Queue is full at the moment... Wait until one customer gets off the queue.");
            }else{
                //implement circular queue if the waiting queue was previously used for retrieving customer data
                waitingQueue[rear% waitingQueue.length] = new String[]{firstName, secondName, vehicleNo, requiredLiters};
                rear++;
                //display confirmation message
                System.out.println("Waiting Queue has been updated with the new customer.");
            }
            //add customer to a main queue if they are not full
        } else {
            addCustomerToQueue(queue1, queue2, queue3, queue4, queue5, highNullCount, firstName, secondName, vehicleNo, requiredLiters);
        }
        return rear;
    }

    public static void addCustomerToQueue(FuelQueue queue1, FuelQueue queue2, FuelQueue queue3, FuelQueue queue4, FuelQueue queue5, int highNullCount, String firstName, String secondName, String vehicleNo, String requiredLiters) {
        //list of fuel queue objects
        FuelQueue[] objArray = {queue1, queue2, queue3, queue4, queue5};
        /* add customer data to relevant queues with the highest empty spots
        and update fuel stock as with the customer's required liters are given to customer */
        if (objArray[highNullCount].passenger1.getFirstName() == null) {
            objArray[highNullCount].passenger1.setFirstName(firstName);
            objArray[highNullCount].passenger1.setSecondName(secondName);
            objArray[highNullCount].passenger1.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger1.setRequiredLiters(requiredLiters);
            FuelQueue.updateFuelStockAfterLoad( Integer.parseInt(objArray[highNullCount].passenger1.getRequiredLiters()));
        } else if (objArray[highNullCount].passenger2.getFirstName() == null) {
            objArray[highNullCount].passenger2.setFirstName(firstName);
            objArray[highNullCount].passenger2.setSecondName(secondName);
            objArray[highNullCount].passenger2.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger2.setRequiredLiters(requiredLiters);
            FuelQueue.updateFuelStockAfterLoad( Integer.parseInt(objArray[highNullCount].passenger2.getRequiredLiters()));
        } else if (objArray[highNullCount].passenger3.getFirstName() == null) {
            objArray[highNullCount].passenger3.setFirstName(firstName);
            objArray[highNullCount].passenger3.setSecondName(secondName);
            objArray[highNullCount].passenger3.setVehicleNo(vehicleNo);
            FuelQueue.updateFuelStockAfterLoad( Integer.parseInt(objArray[highNullCount].passenger3.getRequiredLiters()));
            objArray[highNullCount].passenger3.setRequiredLiters(requiredLiters);
        } else if (objArray[highNullCount].passenger4.getFirstName() == null) {
            objArray[highNullCount].passenger4.setFirstName(firstName);
            objArray[highNullCount].passenger4.setSecondName(secondName);
            objArray[highNullCount].passenger4.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger4.setRequiredLiters(requiredLiters);
            FuelQueue.updateFuelStockAfterLoad( Integer.parseInt(objArray[highNullCount].passenger4.getRequiredLiters()));
        } else if (objArray[highNullCount].passenger5.getFirstName() == null) {
            objArray[highNullCount].passenger5.setFirstName(firstName);
            objArray[highNullCount].passenger5.setSecondName(secondName);
            objArray[highNullCount].passenger5.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger5.setRequiredLiters(requiredLiters);
            FuelQueue.updateFuelStockAfterLoad( Integer.parseInt(objArray[highNullCount].passenger5.getRequiredLiters()));
        } else if (objArray[highNullCount].passenger6.getFirstName() == null) {
            objArray[highNullCount].passenger6.setFirstName(firstName);
            objArray[highNullCount].passenger6.setSecondName(secondName);
            objArray[highNullCount].passenger6.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger6.setRequiredLiters(requiredLiters);
            FuelQueue.updateFuelStockAfterLoad( Integer.parseInt(objArray[highNullCount].passenger6.getRequiredLiters()));
        }
        //display confirmation message
        System.out.println("\nCustomer added successfully to fuel queue " + (highNullCount + 1));
    }

    public static void sortFuelQueues(FuelQueue[] objArray, Scanner scanner) {
        int queueNumber;
        String[] sortedQueue = new String[6];
        //prompt user for the queue number to be sorted
        while (true)
            try{
                System.out.print("Enter queue to be sorted : ");
                queueNumber = scanner.nextInt();
                break;
            }catch (Exception e){
                //display message for providing invalid input
                System.out.println("Invalid input!!");
            }

        //swap null values in the queues to a value ("~") that comes after the sorted position of letter "z" of the alphabetical order
        if (objArray[queueNumber-1].passenger1.getName() == null) sortedQueue[0] = "~";
        else sortedQueue[0] = objArray[queueNumber-1].passenger1.getName();
        if (objArray[queueNumber-1].passenger2.getName() == null) sortedQueue[1] = "~";
        else sortedQueue[1] = objArray[queueNumber-1].passenger2.getName();
        if (objArray[queueNumber-1].passenger3.getName() == null) sortedQueue[2] = "~";
        else sortedQueue[2] = objArray[queueNumber-1].passenger3.getName();
        if (objArray[queueNumber-1].passenger4.getName() == null) sortedQueue[3] = "~";
        else sortedQueue[3] = objArray[queueNumber-1].passenger4.getName();
        if (objArray[queueNumber-1].passenger5.getName() == null) sortedQueue[4] = "~";
        else sortedQueue[4] = objArray[queueNumber-1].passenger5.getName();
        if (objArray[queueNumber-1].passenger6.getName() == null) sortedQueue[5] = "~";
        else sortedQueue[5] = objArray[queueNumber-1].passenger6.getName();
        for (int index = 0; index <= 5; index++) {
            for (int indexSub = index + 1; indexSub <= 5; indexSub++) {
                /*Title: Java Program To Sort an Array in Alphabetical Order
                  Author: Studytonight.com
                  Date: 10/07/2022
                  Code Version: 1.0
                  Availability: https://www.studytonight.com/java-programs/java-program-to-sort-an-array-in-alphabetical-order*/
                if (sortedQueue[index].compareToIgnoreCase(sortedQueue[indexSub]) > 0) {
                    String element = sortedQueue[index];
                    sortedQueue[index] = sortedQueue[indexSub];
                    sortedQueue[indexSub] = element;
                }
                //above referenced code is used for the sorting logic
            }
        }
        //display the sorted queue with details of the customers
        System.out.print("\nSORTED FUEL QUEUE\n___________________________________________\n");
        for (int index = 0; index<6; index++){
            if (sortedQueue[index].equals(objArray[queueNumber-1].passenger1.getName())){
                System.out.printf("%d.\nName : %s\nVehicle Number : %s\nRequired Liters : %s\n___________________\n",index+1,objArray[queueNumber-1].passenger1.getName(),objArray[queueNumber-1].passenger1.getVehicleNo(),objArray[queueNumber-1].passenger1.getRequiredLiters());
            }else if (sortedQueue[index].equals(objArray[queueNumber-1].passenger2.getName())){
                System.out.printf("%d.\nName : %s\nVehicle Number : %s\nRequired Liters : %s\n___________________\n", index + 1, objArray[queueNumber-1].passenger2.getName(), objArray[queueNumber-1].passenger2.getVehicleNo(), objArray[queueNumber-1].passenger2.getRequiredLiters());
            }else if (sortedQueue[index].equals(objArray[queueNumber-1].passenger3.getName())) {
                System.out.printf("%d.\nName : %s\nVehicle Number : %s\nRequired Liters : %s\n___________________\n", index + 1, objArray[queueNumber-1].passenger3.getName(), objArray[queueNumber-1].passenger3.getVehicleNo(), objArray[queueNumber-1].passenger3.getRequiredLiters());
            }else if (sortedQueue[index].equals(objArray[queueNumber-1].passenger4.getName())){
                System.out.printf("%d.\nName : %s\nVehicle Number : %s\nRequired Liters : %s\n___________________\n",index+1,objArray[queueNumber-1].passenger4.getName(),objArray[queueNumber-1].passenger4.getVehicleNo(),objArray[queueNumber-1].passenger4.getRequiredLiters());
            }else if (sortedQueue[index].equals(objArray[queueNumber-1].passenger5.getName())){
                System.out.printf("%d.\nName : %s\nVehicle Number : %s\nRequired Liters : %s\n___________________\n",index+1,objArray[queueNumber-1].passenger5.getName(),objArray[queueNumber-1].passenger5.getVehicleNo(),objArray[queueNumber-1].passenger5.getRequiredLiters());
            }else if (sortedQueue[index].equals(objArray[queueNumber-1].passenger6.getName())){
                System.out.printf("%d.\nName : %s\nVehicle Number : %s\nRequired Liters : %s\n___________________\n",index+1,objArray[queueNumber-1].passenger6.getName(),objArray[queueNumber-1].passenger6.getVehicleNo(),objArray[queueNumber-1].passenger6.getRequiredLiters());
            }
        }
    }
    public static void storeToFile(Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers, String[][] waitingQueue, int front, int rear){
        try {
            //open save file "FuelCenterClassVersionStoredData.txt" for writing data
            FileWriter writer = new FileWriter("FuelCenterClassVersionStoredData.txt");
            //write each queue details into the file line by line
            for (Passenger element : queue1Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }
            for (Passenger element : queue2Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (Passenger element : queue3Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (Passenger element : queue4Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (Passenger element : queue5Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (String[] element: waitingQueue){
                if (element == null){
                    writer.write("-\n");
                }else{
                    for (int index = 0; index<4; index++){
                        writer.write(element[index]+"\n");
                    }
                }
            }
            writer.write(front+"\n");
            writer.write(rear+"\n");
            writer.close();
            //display confirmation message
            System.out.println("Program data stored to file successfully.");
        }catch (IOException e){
            //display message any file errors
            System.out.print("An error has occurred...");
        }
    }
    public static int[] loadFromFile(Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers, String[][] waitingQueue, int front, int rear, FuelQueue[] objArray){
        String[] waitingQueueLoadData = new String[4];
        try {
            //open file named "FuelCenterClassVersionStoredData.txt" for loading the previously saved program data
            File file = new File("FuelCenterClassVersionStoredData.txt");
            Scanner fileReader = new Scanner(file);
            //read file line by line and assign the retrieved data into the fuel queue object methods
            for (Passenger element : queue1Passengers) {
                element.setName(fileReader.nextLine().replace("\n", ""));
                element.setVehicleNo(fileReader.nextLine().replace("\n", ""));
                element.setRequiredLiters(fileReader.nextLine().replace("\n", ""));
            }
            for (Passenger element : queue2Passengers) {
                element.setName(fileReader.nextLine().replace("\n", ""));
                element.setVehicleNo(fileReader.nextLine().replace("\n", ""));
                element.setRequiredLiters(fileReader.nextLine().replace("\n", ""));
            }
            for (Passenger element : queue3Passengers) {
                element.setName(fileReader.nextLine().replace("\n", ""));
                element.setVehicleNo(fileReader.nextLine().replace("\n", ""));
                element.setRequiredLiters(fileReader.nextLine().replace("\n", ""));
            }
            for (Passenger element : queue4Passengers) {
                element.setName(fileReader.nextLine().replace("\n", ""));
                element.setVehicleNo(fileReader.nextLine().replace("\n", ""));
                element.setRequiredLiters(fileReader.nextLine().replace("\n", ""));
            }
            for (Passenger element : queue5Passengers) {
                element.setName(fileReader.nextLine().replace("\n", ""));
                element.setVehicleNo(fileReader.nextLine().replace("\n", ""));
                element.setRequiredLiters(fileReader.nextLine().replace("\n", ""));
            }
            int customerCount = 0;
            //update waiting queue with retrieved data
            for (int index = 0; index<8; index++){
                String queueElement = fileReader.nextLine().replace("\n", "");
                //swap data to null if the symbol "-" exists in current line
                if (queueElement.equals("-")){
                    waitingQueue[index] = null;
                }else {
                    waitingQueueLoadData[0] = queueElement;
                    for (int count = 1; count<4; count++){
                        if (count == 3){
                            waitingQueueLoadData[count] = fileReader.nextLine().replace("\n", "");
                        }else {
                            waitingQueueLoadData[count] = fileReader.nextLine().replace("\n", "");
                        }
                    }
                    customerCount+= Integer.parseInt(waitingQueueLoadData[3]);
                    waitingQueue[index] = waitingQueueLoadData;
                }
            }
            //update circular queue front and rear position
            front = Integer.parseInt(fileReader.nextLine().replace("\n",""));
            rear = Integer.parseInt(fileReader.nextLine().replace("\n",""));
            fileReader.close();
            //display confirmation message
            System.out.println("Save data loaded to the program successfully.");

            //update fuel stock with the data retrieved into the program form the save data file
            for (FuelQueue element: objArray){
                if (!element.passenger1.getFirstName().equals("null")){
                    customerCount+= Integer.parseInt(element.passenger1.getRequiredLiters());
                }
                if (!element.passenger2.getFirstName().equals("null")){
                    customerCount+= Integer.parseInt(element.passenger2.getRequiredLiters());
                }
                if (!element.passenger3.getFirstName().equals("null")){
                    customerCount+= Integer.parseInt(element.passenger3.getRequiredLiters());
                }
                if (!element.passenger4.getFirstName().equals("null")){
                    customerCount+= Integer.parseInt(element.passenger4.getRequiredLiters());
                }
                if (!element.passenger5.getFirstName().equals("null")){
                    customerCount+= Integer.parseInt(element.passenger5.getRequiredLiters());
                }
                if (!element.passenger6.getFirstName().equals("null")){
                    customerCount+= Integer.parseInt(element.passenger6.getRequiredLiters());
                }
            }
            //call the method to update fuel stock from the fuel queue class
            FuelQueue.updateFuelStockAfterLoad(customerCount);
        }catch (IOException e){
            //display message if any file error occurs
            System.out.print("An error has occurred...Please check if the file exits.");
        }
        //return updated circular queue positions
        int[] circularQueue = {front, rear};
        return circularQueue;
    }
    public static void addFuelStock(int updateFuelStock, Scanner scanner){
        //loop until valid inputs are given
        while (true) {
            try {
                //prompt user for stock amount to be added
                System.out.print("Enter stock amount to be added : ");
                updateFuelStock = scanner.nextInt();
                break;
            } catch (Exception e) {
                //display message for invalid inputs
                System.out.println("\nInvalid Input Format...\nPlease retry with valid inputs\n");
            }
        }
        //call method for updating the fuel stock in the fuel queue class
        FuelQueue.updateFuelStock(updateFuelStock);
    }
    public static void displayFuelQueueIncome(FuelQueue queue1, FuelQueue queue2, FuelQueue queue3, FuelQueue queue4, FuelQueue queue5){
        /* display the individual income from the fuel queues
        calling the relevant method to calculate the income of each fuel queue */
        System.out.println("Total income of all fuel queues\n____________________________________________");
        System.out.printf("\nFuel Queue 1 : Rs. %d",queue1.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 2 : Rs. %d",queue2.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 3 : Rs. %d",queue3.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 4 : Rs. %d",queue4.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 5 : Rs. %d",queue5.getFuelQueueIncome()*430);
    }
    public static void storeToFxmlSaveFile(Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers, String[][] waitingQueue){
        try {
            //open file named "FuelCenterFxmlStoreData.txt" for saving data of the current run to be used at the gui application
            FileWriter writer = new FileWriter("FuelCenterFxmlStoreData.txt");
            //save data of the current program run to the file access
            for (Passenger element : queue1Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }
            for (Passenger element : queue2Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (Passenger element : queue3Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (Passenger element : queue4Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (Passenger element : queue5Passengers) {
                writer.write(element.getName() + "\n");
                writer.write(element.getVehicleNo() + "\n");
                writer.write(element.getRequiredLiters() + "\n");
            }

            for (String[] element: waitingQueue){
                if (element == null){
                    writer.write("-\n");
                }else{
                    for (int index = 0; index<4; index++){
                        writer.write(element[index]+"\n");
                    }
                }
            }

            writer.close();
        }catch (IOException e){
            //display message for invalid inputs
            System.out.print("An error has occurred...");
        }
    }
}
