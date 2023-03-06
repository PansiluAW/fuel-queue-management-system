import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        String firstName = "";
        String secondName = "";
        String vehicleNo = "";
        String requiredLiters = "";
        String option;
        int updateFuelStock;
        int front = 1;
        int rear = 1;
        String[][] waitingQueue = new String[8][];
        int[] circularQueue = new int[2];

        Scanner scanner = new Scanner(System.in);

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
                    circularQueue = loadFromFile(queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers, waitingQueue, front, rear);
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
                    while (true) {
                        try {
                            updateFuelStock = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("\nInvalid Input Format...\nPlease retry with valid inputs\n");
                        }
                    }
                    FuelQueue.updateFuelStock(updateFuelStock);
                    break;
                case "110":
                case "IFQ":
                case "ifq":
                    displayFuelQueueIncome(queue1, queue2, queue3, queue4, queue5);
                    break;
                case "999":
                case "EXT":
                case "ext":
                    System.out.println("Program exited successfully.\nGood Bye.");
                    break;
                default:
                    System.out.println("Invalid numeric or character input! \n Please check the menu and re-enter option...");
            }
            System.out.println();
            if (option.equals("999") || option.equals("EXT") || option.equals("ext")) {
                break;
            }
        }
    }

    public static void viewAll(FuelQueue[] objArray, String[][] waitingQueue) {
        int count = 1;
        for (FuelQueue queues : objArray) {
            System.out.println("*__________________________________________________________________________________________________*");
            System.out.format("%50s %d\n", "FUEL QUEUE ", count);
            System.out.println("*__________________________________________________________________________________________________*");
            System.out.format("%15s %25s %25s %25s\n", "FIRST NAME", "SECOND NAME", "VEHICLE NUMBER", "REQUIRED LITERS");
            queues.getPassengerDetails();
            System.out.println("*__________________________________________________________________________________________________*\n\n");
            System.out.printf("%50s\n\n", "++++++++");
            count++;
        }
        int index = 1;
        System.out.println("_______________________________\n_________WAITING QUEUE_________");
        for (String[] element: waitingQueue) {
            if (element != null) {
                System.out.print((index+1)+". ");
                for (int subCount = 0; subCount<4; subCount++){
                    if (subCount == 0){
                        System.out.print("First Name : ");
                    }else if (subCount == 1){
                        System.out.print("Second Name : ");
                    }else if (subCount == 2){
                        System.out.print("Vehicle Number : ");
                    }else if (subCount == 3){
                        System.out.print("Required Liters : ");
                    }
                    System.out.println(element[subCount]);
                }
                index++;
                System.out.println("");
            }
        }
        if (Arrays.stream(waitingQueue).allMatch(Objects::isNull)){
            System.out.println("\n+The Waiting list is empty.+");
        }
        System.out.println("_______________________________");
    }

    public static void viewEmptyQueues(FuelQueue[] objArray) {
        int count = 1;
        System.out.print("FUEL QUEUES WITH AN EMPTY SPOT~\n---------------------------------------\n");
        for (FuelQueue queues : objArray) {
            System.out.printf("Queue %5d %5s ", count, ":");
            queues.getEmptyQueues();
            count++;
        }
        System.out.print("---------------------------------------\n");
    }

    public static int removeFromLocation(FuelQueue[] objArray, Scanner scanner, int front, int rear, String[][] waitingQueue, Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers) {
        System.out.print("Enter the queue from which you want to remove customer : ");
        String queue = scanner.next();
        System.out.print("Enter the customer's queue location : ");
        String locationFromQueue = scanner.next();
        if (queue.equals("1") || queue.equals("2") || queue.equals("3") || queue.equals("4") || queue.equals("5")) {
            if (locationFromQueue.equals("1") || locationFromQueue.equals("2") || locationFromQueue.equals("3") || locationFromQueue.equals("4") || locationFromQueue.equals("5") || locationFromQueue.equals("6")) {
                objArray[Integer.parseInt(queue) - 1].removeFromQueueLocation(Integer.parseInt(locationFromQueue));
                System.out.println("\nCustomer removed from location " + locationFromQueue + " of queue " + queue);
            } else
                System.out.println("\nInvalid fuel queue location... \nPlease try again after re-check\n");
            circularQueueImplementAtRemove(queue, front, rear,waitingQueue, queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers);
            front++;
        } else
            System.out.println("\nFuel queue not in range(only queues inclusive from 1-5 available)... \nPlease try again after re-check\n");
        return front;
    }

    public static int removeServedCustomer(Scanner scanner, FuelQueue[] objArray,  int front, int rear, String[][] waitingQueue, Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers){
        System.out.print("Enter the queue of the served customer : ");
        String queue = scanner.next();
        if (queue.equals("1") || queue.equals("2") || queue.equals("3") || queue.equals("4") || queue.equals("5")) {
            objArray[Integer.parseInt(queue) - 1].removeFromQueueLocation(Integer.parseInt("1"));
            System.out.println("\nServed Customer removed from fuel queue " + queue);
            circularQueueImplementAtRemove(queue, front, rear,waitingQueue, queue1Passengers, queue2Passengers, queue3Passengers, queue4Passengers, queue5Passengers);
            front++;
        } else
            System.out.println("\nFuel queue not in range(only queues inclusive from 1-5 available)... \nPlease try again after re-check\n");
        return front;
    }

    public static void circularQueueImplementAtRemove(String queue, int front, int rear, String[][] waitingQueue, Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers){
        if (!Arrays.stream(waitingQueue).allMatch(Objects::isNull)) {
            System.out.println(front);
            if (queue.equals("1")) {
                for (Passenger element : queue1Passengers) {
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
            waitingQueue[front] = null;
            System.out.println("Waiting Queue has been updated.");

        }
    }

    public static int addCustomer(FuelQueue queue1, FuelQueue queue2, FuelQueue queue3, FuelQueue queue4, FuelQueue queue5, String firstName, String secondName, String vehicleNo, String requiredLiters, String[][] waitingQueue, Scanner scanner, FuelQueue[] objArray, int front, int rear) {
        int highNullCount = 0;
        boolean exists = false;
        boolean allAlpha = true;
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
        while (true) {
            System.out.printf("%s %5s", "Enter the below details,\nFirst Name of the customer", ":");
            firstName = scanner.next();
            System.out.printf("%s %4s", "Second Name of the customer", ":");
            secondName = scanner.next();
            allAlpha = (firstName + secondName).matches("[a-zA-Z]+");
            if (!allAlpha) {
                System.out.println("\nInvalid format used for names...\nPlease re-check and try again.\n");
                continue;
            }
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
                    System.out.printf("Name already exists in queue %d. Please enter different customer details...\n", index + 1);
                    break;
                }
            }
            if (exists == false) break;
        }
        System.out.printf("%s %17s", "Vehicle Number", ":");
        vehicleNo = scanner.next();
        boolean allNumerics = true;
        while (true) {
            System.out.printf("%s %8s", "Required liters of fuel", ":");
            requiredLiters = scanner.next();
            char[] numerics = requiredLiters.toCharArray();
            for (char element : numerics) {
                if (!Character.isDigit(element)) {
                    System.out.println("\nInvalid format used for liters required in numerics...\nPlease re-check and try again.\n");
                    allNumerics = false;
                    break;
                } else allNumerics = true;
            }
            if (allNumerics) break;
            else continue;
        }
        if (queue5.fuelQueueFullStatus() == true) {
            if (Arrays.stream(waitingQueue).allMatch(Objects::isNull)){
                waitingQueue[rear] = new String[]{firstName, secondName, vehicleNo, requiredLiters};
                rear++;
                System.out.println("Waiting Queue has been updated with the new customer.");
            }else if (Arrays.stream(waitingQueue).allMatch(Objects::nonNull)) {
                System.out.print("Waiting Queue is full at the moment... Wait until one customer gets off the queue.");
            }else{
                waitingQueue[rear% waitingQueue.length] = new String[]{firstName, secondName, vehicleNo, requiredLiters};
                rear++;
                System.out.println("Waiting Queue has been updated with the new customer.");
            }
        } else {
            addCustomerToQueue(queue1, queue2, queue3, queue4, queue5, highNullCount, firstName, secondName, vehicleNo, requiredLiters);
        }
        return rear;
    }

    public static void addCustomerToQueue(FuelQueue queue1, FuelQueue queue2, FuelQueue queue3, FuelQueue queue4, FuelQueue queue5, int highNullCount, String firstName, String secondName, String vehicleNo, String requiredLiters) {
        FuelQueue[] objArray = {queue1, queue2, queue3, queue4, queue5};
        if (objArray[highNullCount].passenger1.getFirstName() == null) {
            objArray[highNullCount].passenger1.setFirstName(firstName);
            objArray[highNullCount].passenger1.setSecondName(secondName);
            objArray[highNullCount].passenger1.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger1.setRequiredLiters(requiredLiters);
        } else if (objArray[highNullCount].passenger2.getFirstName() == null) {
            objArray[highNullCount].passenger2.setFirstName(firstName);
            objArray[highNullCount].passenger2.setSecondName(secondName);
            objArray[highNullCount].passenger2.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger2.setRequiredLiters(requiredLiters);
        } else if (objArray[highNullCount].passenger3.getFirstName() == null) {
            objArray[highNullCount].passenger3.setFirstName(firstName);
            objArray[highNullCount].passenger3.setSecondName(secondName);
            objArray[highNullCount].passenger3.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger3.setRequiredLiters(requiredLiters);
        } else if (objArray[highNullCount].passenger4.getFirstName() == null) {
            objArray[highNullCount].passenger4.setFirstName(firstName);
            objArray[highNullCount].passenger4.setSecondName(secondName);
            objArray[highNullCount].passenger4.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger4.setRequiredLiters(requiredLiters);
        } else if (objArray[highNullCount].passenger5.getFirstName() == null) {
            objArray[highNullCount].passenger5.setFirstName(firstName);
            objArray[highNullCount].passenger5.setSecondName(secondName);
            objArray[highNullCount].passenger5.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger5.setRequiredLiters(requiredLiters);
        } else if (objArray[highNullCount].passenger6.getFirstName() == null) {
            objArray[highNullCount].passenger6.setFirstName(firstName);
            objArray[highNullCount].passenger6.setSecondName(secondName);
            objArray[highNullCount].passenger6.setVehicleNo(vehicleNo);
            objArray[highNullCount].passenger6.setRequiredLiters(requiredLiters);
        }
        FuelQueue.deduceFuelStock();
        System.out.println("\nCustomer added successfully to fuel queue " + (highNullCount + 1));
    }

    public static void sortFuelQueues(FuelQueue[] objArray, Scanner scanner) {
        int queueNumber;
        String[] sortedQueue = new String[6];
        while (true)
            try{
                System.out.print("Enter queue to be sorted : ");
                queueNumber = scanner.nextInt();
                break;
            }catch (Exception e){
                System.out.println("Invalid input!!");
            }

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
            }
        }
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
            FileWriter writer = new FileWriter("FuelCenterClassVersionStoredData.txt");
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
            System.out.println("Program data stored to file successfully.");
        }catch (IOException e){
            System.out.print("An error has occurred...");
        }
    }
    public static int[] loadFromFile(Passenger[] queue1Passengers, Passenger[] queue2Passengers, Passenger[] queue3Passengers, Passenger[] queue4Passengers, Passenger[] queue5Passengers, String[][] waitingQueue, int front, int rear){
        String[] waitingQueueLoadData = new String[4];
        try {
            File file = new File("FuelCenterClassVersionStoredData.txt");
            Scanner fileReader = new Scanner(file);
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
            for (int index = 0; index<8; index++){
                String queueElement = fileReader.nextLine().replace("\n", "");
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
                    waitingQueue[index] = waitingQueueLoadData;
                }
            }
            front = Integer.parseInt(fileReader.nextLine().replace("\n",""));
            rear = Integer.parseInt(fileReader.nextLine().replace("\n",""));
            fileReader.close();
        }catch (IOException e){
            System.out.print("An error has occurred...Please check if the file exits.");
        }
        int[] circularQueue = {front, rear};
        return circularQueue;
    }
    public static void displayFuelQueueIncome(FuelQueue queue1, FuelQueue queue2, FuelQueue queue3, FuelQueue queue4, FuelQueue queue5){
        System.out.println("Total income of all fuel queues\n____________________________________________");
        System.out.printf("\nFuel Queue 1 : Rs. %d",queue1.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 2 : Rs. %d",queue2.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 3 : Rs. %d",queue3.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 4 : Rs. %d",queue4.getFuelQueueIncome()*430);
        System.out.printf("\nFuel Queue 5 : Rs. %d",queue5.getFuelQueueIncome()*430);
    }
}