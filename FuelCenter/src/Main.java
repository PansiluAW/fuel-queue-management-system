import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
import java.io.File;
public class Main {
//I Assume that before retrieving data from the stored data file, customers are served with fuel from the stock.
    private static int stockAmount = 6600;
    public static void main(String[] args) {
        String[] pump1 = new String[6];
        String[] pump2 = new String[6];
        String[] pump3 = new String[6];
        String option, customerName = "";
        int queueNumber = 0;

        //scanner object
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("\t999 or EXT: Exit the Program.");

            //get option as an input from the user
            System.out.print("\n\t\t>>>>  ");
            option = scanner.next();
            System.out.println();

            //switch case to select option to proceed with according to relevant user input numeric or character codes
            switch (option) {
                case "100":
                case "VFQ":
                case "vfq":
                    viewAll(pump1, pump2, pump3);
                    break;
                case "101":
                case "VEQ":
                case "veq":
                    viewEmpty(pump1, pump2, pump3);
                    break;
                case "ACQ":
                case "102":
                case "acq":
                    addToQueue(pump1, pump2, pump3, customerName, queueNumber, scanner);
                    break;
                case "RCQ":
                case "103":
                case "rcq":
                    removeFromLocation(pump1, pump2, pump3, queueNumber, scanner);
                    break;
                case "104":
                case "PCQ":
                case "pcq":
                    removeServedCustomer(pump1, pump2, pump3, customerName, queueNumber, scanner);
                    break;
                case "105":
                case "VCS":
                case "vcs":
                    sortQueue(pump1, pump2, pump3, queueNumber, scanner);
                    break;
                case "106":
                case "SPD":
                case "spd":
                    fileHandling(pump1, pump2, pump3, true, scanner);
                    break;
                case "107":
                case "LPD":
                case "lpd":
                    fileHandling(pump1, pump2, pump3, false, scanner);
                    break;
                case "108":
                case "STK":
                case "stk":
                    System.out.print("Remaining Fuel Stock : "+String.valueOf(stockAmount));
                    break;
                case "109":
                case "AFS":
                case "afs":
                    addFuelStock(scanner);
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
            if (option.equals("999") || option.equals("EXT") || option.equals("ext")) break;
        }
    }

    //method for viewing all fuel queues
    public static void viewAll(String[] pump1, String[] pump2, String[] pump3) {
        //display the relevant header for dislaying values
        System.out.println("||All Fuel Queues||");
        //display values with an equal whitespace between each element display
        System.out.printf("%s%26s%28s","Pump 1", "Pump 2", "Pump 3");
        for (int count = 1; count <= 6; count++) {
            System.out.printf("%n%s", pump1[count - 1]);
            System.out.printf("%28s", pump2[count - 1]);
            System.out.printf("%28s", pump3[count - 1]);
        }
    }

    //viewing all empty queues
    public static void viewEmpty(String[] pump1, String[] pump2, String[] pump3) {
        int count;
        boolean noNull = false;
        //check arrays(fuel queues) with a null value
        //and display each queue if such empty queues exists, with their customers and their location in the queue or else display relevant message to the user
        if (Arrays.asList(pump1).contains(null)) {
            count = 1;
            System.out.print("Pump 1 : ");
            for (String element : pump1) {
                System.out.printf(" %d. %s\t", count, element);
                count++;
            }
            System.out.println("\n");
        }else  noNull = true;

        if (Arrays.asList(pump2).contains(null)) {
            count = 1;
            System.out.print("Pump 2 : ");
            for (String element : pump2) {
                System.out.printf(" %d. %s\t", count, element);
                count++;
            }
            System.out.println("\n");
        }else noNull = true;

        if (Arrays.asList(pump3).contains(null)) {
            count = 1;
            System.out.print("Pump 3 : ");
            for (String element : pump3) {
                System.out.printf(" %d. %s\t", count, element);
                count++;
            }
            System.out.println("\n");
        }else noNull = true;
        //display relevant message if all queues are full
        if (noNull == true) System.out.println("All queues are full at the moment.");
    }

    //method to add customer to a queue
    public static void addToQueue(String[] pump1, String[] pump2, String[] pump3, String customerName, int queueNumber, Scanner scanner) {
        while (true) {
            try {
                while (true) {
                    System.out.print("Enter queue : ");
                    queueNumber = scanner.nextInt();
                    //check if user input in range
                    if (queueNumber > 3 || queueNumber < 1) {
                        System.out.println("Queue number must be between 1 to 3 (including)! Please re-try...");
                        continue;
                    }
                    break;
                }
                break;
            } catch (Exception e) {
                System.out.print("Invalid input! Please try again with a valid input...");
                scanner.nextLine();
            }
        }
        System.out.print("Enter the name of the customer to be added to queue : ");
        customerName = scanner.next();
        //check if the customer name is existing as an array of any queue list
        if (Arrays.asList(pump1).contains(customerName) || Arrays.asList(pump2).contains(customerName) || Arrays.asList(pump3).contains(customerName)) {
            System.out.println("Customer name already exits in a queue!");
        } else {
            //add to next empty location of the selected queue
            for (int index = 0; index <= 5; index++) {
                if (queueNumber == 1 && pump1[index] == null) {
                    pump1[index] = customerName;
                    break;
                } else if (queueNumber == 2 && pump2[index] == null) {
                    pump2[index] = customerName;
                    break;
                } else if (queueNumber == 3 && pump3[index] == null) {
                    pump3[index] = customerName;
                    break;
                }
            }
            System.out.println("Customer added to the selected queue.");
        }
        //update fuel stock with each customer (with warning at 500 liters)
        stockAmount -= 10;
        if (stockAmount <= 500)
            System.out.println("Warning!! \nTotal fuel stock reached value 500...liters \n Current fuel stock is at " + String.valueOf(stockAmount));
    }

    //method to remove a customer from specific location a queue
    public static void removeFromLocation(String[] pump1, String[] pump2, String[] pump3, int queueNumber, Scanner scanner) {
        String[] pumpIn = new String[6];
        int pumpNo;
        int queueLocation = 0;
        while (true) {
            try {
                while (true) {
                    System.out.print("Enter queue : ");
                    queueNumber = scanner.nextInt();
                    //check if user input in range
                    if (queueNumber > 3 || queueNumber < 1) {
                        System.out.println("Queue number must be between 1 to 3 (including)! Please re-try...");
                        continue;
                    }
                    break;
                }
                //ask user for queue location to be removed
                while (true) {
                    System.out.printf("Enter the specific location to be removed from queue %s : ", queueNumber);
                    queueLocation = scanner.nextInt();
                    //check if user input in range
                    if (queueLocation > 6 || queueLocation < 0) {
                        System.out.println("In-Queue location cannot must be between  1 to 6 (including)! Please re-try...");
                        continue;
                    }
                    break;
                }
                break;
            } catch (Exception e) {
                System.out.print("Invalid input! Please try-again with a valid input...");
                scanner.nextLine();
            }
        }
        //push elements after an element is removed from the queue to fill the qap
        if (queueNumber == 1){
            pump1[queueLocation - 1] = null;
            pumpIn = pump1;
            pumpNo = 1;
        }
        else if (queueNumber == 2){
            pump2[queueLocation - 1] = null;
            pumpIn = pump2;
            pumpNo = 2;
        }
        else {
            pump3[queueLocation - 1] = null;
            pumpIn = pump3;
            pumpNo = 3;
        }

        for (String element: pumpIn){
            if (element == null){
                int nullIndex = Arrays.asList(pumpIn).indexOf(null);
                for (int index = nullIndex; index<=4; index++){
                    String currentElement = pump1[index];
                    pumpIn[index] = pumpIn[index+1];
                    pumpIn[index+1] = currentElement;
                }
            }
        }

        if (pumpNo == 1){
            pump1 = pumpIn;
        }else if (pumpNo == 2){
            pump2 = pumpIn;
        }else pump3 = pumpIn;

        //update fuel stock when a customer is removed
        stockAmount += 10;
        System.out.println("Customer removed from the selected location of the queue successfully.");
    }

    //method to remove a served customer from a queue
    public static void removeServedCustomer(String[] pump1, String[] pump2, String[] pump3,String  customerName, int queueNumber, Scanner scanner) {
        String[] updatedPumpQueue = {null, null, null, null, null, null};
        int index;
        while (true) {
            try {
                while (true) {
                    System.out.print("Enter queue : ");
                    queueNumber = scanner.nextInt();
                    //check if user input in range
                    if (queueNumber > 3 || queueNumber < 1) {
                        System.out.println("Queue number must be between 1 to 3 (including)! Please re-try...");
                        continue;
                    }
                    break;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please try-again with a valid input...");
                scanner.nextLine();
            }
        }
        //push the remaining elements of the queue array starting from the first element (index 1) to remove the empty space at location 1 (index 0) in the queue
        for (index = 1; index <= 5; index++) {
            if (queueNumber == 1) updatedPumpQueue[index - 1] = pump1[index];
            else if (queueNumber == 2) updatedPumpQueue[index - 1] = pump2[index];
            else updatedPumpQueue[index - 1] = pump3[index];
        }
        index = 0;
        for (String element : updatedPumpQueue) {
            if (queueNumber == 1) {
                pump1[index] = element;
            } else if (queueNumber == 2) {
                pump2[index] = element;
            } else {
                pump3[index] = element;
            }
            index++;
        }
        System.out.println("Served customer removed from the selected queue successfully.");
    }

    //method to write program data and/or retrieve data stored from a previous run back to the program
    public static void fileHandling(String[] pump1, String[] pump2, String[] pump3, boolean fileOption, Scanner scanner){
        try{
            //storing to the data of the program to the file as per the user's menu option selection
            if (fileOption == true){
                FileWriter writer = new FileWriter("FuelCenterStoredData.txt");
                writer.write("Pump1\n");
                for (String elements: pump1){
                    writer.write(elements+"\n");
                }

                writer.write("Pump2\n");
                for (String elements: pump2){
                    writer.write(elements+"\n");
                }

                writer.write("Pump3\n");
                for (String elements: pump3){
                    writer.write(elements+"\n");
                }
                writer.close();
                System.out.println("Program data stored to file successfully.");

                //loading the data of a previous run saved to the program as per the user's menu option selection
            }else {
                File file = new File("FuelCenterStoredData.txt");
                Scanner fileReader = new Scanner(file);
                String textLine;
                int pumpNum = 0, pump1Index = 0, pump2Index = 0, pump3Index = 0;
                while(fileReader.hasNextLine()){
                    //replacing the new line character when switching data back to the queue arrays
                    textLine = fileReader.nextLine().replace("\n","");
                    //differenciating queues with the titles in the store data file
                    if (textLine.equals("Pump1")){
                        pumpNum = 1;
                    }else if (textLine.equals("Pump2")){
                        pumpNum = 2;
                    }else if (textLine.equals("Pump3")){
                        pumpNum = 3;
                    }
                    if (pumpNum == 1 && !textLine.equals("Pump1")){
                        if (textLine.equals("null")){
                            pump1[pump1Index] = null;
                        }else{
                            pump1[pump1Index] = textLine;
                        }
                        pump1Index++;
                    }else if(pumpNum == 2 && !textLine.equals("Pump2")){
                        if (textLine.equals("null")){
                            pump2[pump2Index] = null;
                        }else{
                            pump2[pump2Index] = textLine;
                        }
                        pump2Index++;
                    }else if (pumpNum == 3 && !textLine.equals("Pump3")){
                        if (textLine.equals("null")){
                            pump3[pump3Index] = null;
                        }else{
                            pump3[pump3Index] = textLine;
                        }
                        pump3Index++;
                        }
                    //updating fuel stocks according to retrieved stored data
                    if (!textLine.equals("null") && !(textLine.equals("Pump1") || textLine.equals("Pump2") || textLine.equals("Pump3"))){
                        stockAmount -=10;
                    }
                }
                System.out.println("Stored data retrieved to program successfully.");
            }
        }catch (IOException e){
                System.out.print("An error has occurred...Please check if the file exits.");
        }
    }

    //method to add to current fuel stock as the program runs
    public static void addFuelStock(Scanner scanner){
        int addStock;
        try{
            System.out.println("Remaining stock in the fuel center : "+String.valueOf(stockAmount));
            System.out.print("Enter the amount of stock to be added : ");
            addStock = scanner.nextInt();
            stockAmount += addStock;
            System.out.println("Fuel stock updated successfully.");
            System.out.println("Updated remaining stock in the fuel center : "+String.valueOf(stockAmount));
        }catch(Exception e){
            System.out.println("Invalid Input!! Please try again...");
        }
    }

    //method to sort each pump queue according to user input
    public static void sortQueue(String[] pump1, String[] pump2, String[] pump3, int queueNumber, Scanner scanner) {
        String[] sortedQueue = new String[6];
        try{
            System.out.print("Enter queue to be sorted : ");
            queueNumber = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Invalid input!!");
        }
        //creating a new list of the chosen list to make modifications before assigning it back
        if (queueNumber == 1){
            for (int index = 0; index<6; index++){
                sortedQueue[index] = pump1[index];
            }
        }
        else if (queueNumber == 2) {
            for (int index = 0; index < 6; index++) {
                sortedQueue[index] = pump1[index];
            }
        }else if (queueNumber == 3) {
            for (int index = 0; index < 6; index++) {
                sortedQueue[index] = pump1[index];
            }
        }

        for (int index1 = 0; index1<6; index1++){
            if (sortedQueue[index1] == null){
                sortedQueue[index1] = "~";
            }
        }
        //check each element with each other element to check its location according to alphabetical order
        for (int index = 0; index<=5; index++ ){
            for (int indexSub = index+1; indexSub<=5; indexSub++){
                /*Title: Java Program To Sort an Array in Alphabetical Order
                  Author: Studytonight.com
                  Date: 10/07/2022
                  Code Version: 1.0
                  Availability: https://www.studytonight.com/java-programs/java-program-to-sort-an-array-in-alphabetical-order*/
                if (sortedQueue[index].compareToIgnoreCase(sortedQueue[indexSub])>0){
                    String element = sortedQueue[index];
                    sortedQueue[index] = sortedQueue[indexSub];
                    sortedQueue[indexSub] = element;
                }
            }
        }
        System.out.println("Sorted Queue ; ");
        for (String element: sortedQueue){
            System.out.println("\t"+element);
        }
    }
}