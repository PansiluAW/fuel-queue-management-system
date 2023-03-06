//import packages and libraries
package com.example.fuelqueueclassversionfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HelloController  {
    @FXML
    private Label informationDisplayLabel1;

    @FXML
    private Label informationDisplayLabel2;
    @FXML
    private Label searchInformationDisplayLabel;
    @FXML
    private TextField searchBox;

    @FXML
    protected void onViewAllButtonClick()
    {
        //variables declared with the text to be displayed for the customer list and waiting list
        String customerList = "___________________________________________\nList of Customers in the Fuel Queues\n___________________________________________\n";
        String waitingList = "___________________________________________\nList of Customers in the Waiting Queues\n___________________________________________\n";
        try {
            //open file named "FuelCenterFxmlStoreData.txt" to load save data from the current run of the program
            File file = new File("FuelCenterFxmlStoreData.txt");
            Scanner fileReader = new Scanner(file);

            //acess the file content to update the text to be displayed in the GUI (Graphical User Interface)
            for (int count = 1; count<=30; count++) {
                //read line and assign line to variable
                String currentQueueLine = fileReader.nextLine();
                //proceed if the passenger data is not empty
                if (!currentQueueLine.contains("null")) {
                    //update text with data
                    customerList += "Name : " + currentQueueLine + "   ";
                    customerList += "Vehicle Number : " + fileReader.nextLine() + "   ";
                    customerList += "Required Liters : " + fileReader.nextLine() + "\n\n";
                }else {
                    //skip line if details are empty
                    fileReader.nextLine();
                    fileReader.nextLine();
                }
            }

            for (int count = 1; count<=8; count++){
                String currentLine = fileReader.nextLine();
                //check if the waiting list data contains any passenger data
                if (!currentLine.equals("-")){
                    //update if the line contains passenger details
                    waitingList +="Name : "+currentLine+fileReader.nextLine()+"   ";
                    waitingList +=" Vehicle Number : "+fileReader.nextLine()+"   ";
                    waitingList +="Required Liters : "+fileReader.nextLine()+"\n\n";
                }
            }

            //update text if the waiting list and customer list is empty
            if (waitingList.equals("___________________________________________\nList of Customers in the Waiting Queues\n___________________________________________\n") || customerList.equals("___________________________________________\nList of Customers in the Fuel Queues\n___________________________________________\n")){
                waitingList += "\nWaiting List is empty";
                customerList += "\nCustomer List is empty";
            }
            fileReader.close();

        }catch (IOException e){
            //display message if file error occurs
            System.out.print("An error has occurred...Please check if the file exits.");
        }
        //set text and color for the label
        informationDisplayLabel1.setText(customerList);
        informationDisplayLabel2.setText(waitingList);
        searchInformationDisplayLabel.setText("");
        informationDisplayLabel1.setStyle("-fx-background-color:#e2e2e2");
        informationDisplayLabel2.setStyle("-fx-background-color:#e2e2e2");
        searchInformationDisplayLabel.setStyle("-fx-background-color: transparent");


    }

    @FXML
    protected void onSearchCustomerButtonClick(){
        //get input given to the search box
        String searchCustomerName = searchBox.getText();
        //assign variable with the text to display inside label
        String searchCustomerDetails = "-----------------Search Result----------------\n\n-----------------------------------------------------\n";
        String searchCustomerNotFound = "";
        try {
            //open file named "FuelCenterFxmlStoreData.txt" to access the saved data from the current run
            File file = new File("FuelCenterFxmlStoreData.txt");
            Scanner fileReader = new Scanner(file);
            //read content until end with a loop
            while(fileReader.hasNext()){
                //assign variable with data in the line
                String currentLine = fileReader.nextLine();
                //check if the search box text matches the text in the current line
                if (currentLine.equals(searchCustomerName)){
                    //update text to be displayed
                    searchCustomerDetails += "Name : "+currentLine+"\n";
                    searchCustomerDetails += "Vehicle Number : "+fileReader.nextLine()+"\n";
                    searchCustomerDetails += "Required Liters : "+fileReader.nextLine()+"\n";
                    break;
                //update text if there is no match found
                }else searchCustomerNotFound = "No match found";
            }
            fileReader.close();

        }catch (IOException e){
            //display message if invalid input is given
            System.out.print("An error has occurred...Please check if the file exits.");
        }

        //set text to be displayed in the label with the background color
        searchInformationDisplayLabel.setText(searchCustomerDetails+searchCustomerNotFound);
        informationDisplayLabel1.setText("");
        informationDisplayLabel2.setText("");
        searchInformationDisplayLabel.setStyle("-fx-background-color:#e2e2e0");
        informationDisplayLabel1.setStyle("-fx-background-color:#e2e2e9");
        informationDisplayLabel2.setStyle("-fx-background-color: #e2e2e9");

    }
}