import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.opencsv.CSVReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Chris Clarke
 * TM CJCSoft
 */

public class CustomerAccountClass 
{
    
    public class CustomerAccountDetails extends CustomerAccountClass
    {
    
       String CustomerAccountNumber;
       String CustomerName;
       String CustomerAddressLine1;
       String CustomerAddressLine2;
       String CustomerCity;
       String CustomerProvince;
       String CustomerPostalCode;
       String CustomerAttention;
       String CustomerPhone;        
       String CustomerFax;
       String CustomerEmail;                             
        
    }
    
    public class SpecialInstructions extends CustomerAccountClass
    {
        
        String SpecialInstructions;
        
    }
    
    //LinkedList<CustomerAccount> listOfFoundCustomers = new LinkedList();     
    
    public boolean boolFound = false;
    
    public int intPositionInList = 0;  
               
    public boolean boolFoundAccount()
    {
        
        return boolFound;
        
    }
    
    public int intPositionInList()
    {
        
        int intPosition = intPositionInList;
        
        return intPosition;
        
    }
   
    public CustomerAccountClass.CustomerAccountDetails getSavedBillToCustomerDetails(CustomerAccountClass.CustomerAccountDetails foundCustomer, CompleteOrderClass savedOrder)
    {        
        // Loads the bill to customer details into the doundCustomer object
        
        foundCustomer.CustomerAccountNumber = savedOrder.billToCustomerAccountNumber;
        foundCustomer.CustomerName = savedOrder.billToCustomerName;
        foundCustomer.CustomerAddressLine1 = savedOrder.billToCustomerAddressLine1;
        foundCustomer.CustomerAddressLine2 = savedOrder.billToCustomerAddressLine2;
        foundCustomer.CustomerCity = savedOrder.billToCustomerCity;
        foundCustomer.CustomerProvince = savedOrder.billToCustomerProvince;
        foundCustomer.CustomerPostalCode = savedOrder.billToCustomerPostalCode;
        foundCustomer.CustomerAttention = savedOrder.billToCustomerAttention;
        foundCustomer.CustomerPhone = savedOrder.billToCustomerPhone;
        foundCustomer.CustomerFax = savedOrder.billToCustomerFax;
        foundCustomer.CustomerEmail = savedOrder.billToCustomerEmail;
        
        return foundCustomer;
        
    }
    
    public CustomerAccountClass.CustomerAccountDetails getSavedShipToCustomerDetails(CustomerAccountClass.CustomerAccountDetails foundCustomer, CompleteOrderClass savedOrder)
    {        
        // Loads the bill to customer details into the doundCustomer object
        
        foundCustomer.CustomerAccountNumber = savedOrder.shipToCustomerAccountNumber;
        foundCustomer.CustomerName = savedOrder.shipToCustomerName;
        foundCustomer.CustomerAddressLine1 = savedOrder.shipToCustomerAddressLine1;
        foundCustomer.CustomerAddressLine2 = savedOrder.shipToCustomerAddressLine2;
        foundCustomer.CustomerCity = savedOrder.shipToCustomerCity;
        foundCustomer.CustomerProvince = savedOrder.shipToCustomerProvince;
        foundCustomer.CustomerPostalCode = savedOrder.shipToCustomerPostalCode;
        foundCustomer.CustomerAttention = savedOrder.shipToCustomerAttention;
        foundCustomer.CustomerPhone = savedOrder.shipToCustomerPhone;
        foundCustomer.CustomerFax = savedOrder.shipToCustomerFax;
        foundCustomer.CustomerEmail = savedOrder.shipToCustomerEmail;
        
        return foundCustomer;
        
    }   
            
    public LinkedList<CustomerAccountClass.CustomerAccountDetails> SearchForCustomerAllFields(String strTextToFind, LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers)            
    {
        //Searchers alls fields for the selected text        
              
        boolean boolAllFields = true;

        CustomerAccountClass.CustomerAccountDetails foundCustomer = new CustomerAccountClass.CustomerAccountDetails(); //Create new customer account to compare 
        
        LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfFoundCustomers = new LinkedList();
                
        for (int i = 0; i < 11; i++)
        {
           
           listOfFoundCustomers = SearchForCustomerBySpecifiedField(strTextToFind, listOfCustomers, listOfFoundCustomers, boolAllFields, i); // i = field number
       
        }
        
        return listOfFoundCustomers;
       
    }
    
    public CustomerAccountClass.CustomerAccountDetails SearchForCustomerByAccountNumber(String strAcctNum, LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers)
    {    
        //Searches for the customer based on the account details          
        
        CustomerAccountClass newCustomerAccountClass = new CustomerAccountClass();
        
        CustomerAccountClass.CustomerAccountDetails foundCustomer = newCustomerAccountClass.new CustomerAccountDetails();
        
        String strAcctToFind = "";
        
      //  LoadCustomerDatabase(listOfCustomers); //Load the customers from the database file into a list       
             
        for(int i = 0; i < listOfCustomers.size(); i++)
        {
          
           strAcctToFind = listOfCustomers.get(i).CustomerAccountNumber.toUpperCase();          
           
           if(Objects.equals(strAcctToFind, strAcctNum.toUpperCase()))
           {        
               
               foundCustomer.CustomerAccountNumber = listOfCustomers.get(i).CustomerAccountNumber;
               foundCustomer.CustomerName = listOfCustomers.get(i).CustomerName;
               foundCustomer.CustomerAddressLine1 = listOfCustomers.get(i).CustomerAddressLine1;
               foundCustomer.CustomerAddressLine2 = listOfCustomers.get(i).CustomerAddressLine2;
               foundCustomer.CustomerCity = listOfCustomers.get(i).CustomerCity;
               foundCustomer.CustomerProvince = listOfCustomers.get(i).CustomerProvince;
               foundCustomer.CustomerPostalCode = listOfCustomers.get(i).CustomerPostalCode;
               foundCustomer.CustomerAttention = listOfCustomers.get(i).CustomerAttention;
               foundCustomer.CustomerPhone = listOfCustomers.get(i).CustomerPhone;
               foundCustomer.CustomerFax = listOfCustomers.get(i).CustomerFax;
               foundCustomer.CustomerEmail = listOfCustomers.get(i).CustomerEmail;
                    
               boolFound = true;
                                          
               intPositionInList = i;
               
               break;
               
           } 
           else
           {
               
               boolFound = false;
               
               intPositionInList = -1;
               
           }
           
       }     
                 
        return foundCustomer;
        
    }
    
    public LinkedList<CustomerAccountClass.CustomerAccountDetails> SearchForCustomerBySpecifiedField(String strTextToFind, LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers, 
            LinkedList<CustomerAccountClass.CustomerAccountDetails> currentListOfFoundCustomers, boolean boolAllFields, int intFieldNum)
    {    
        //Searches for the customer based on the account details          
           
        CustomerAccountClass.CustomerAccountDetails foundCustomer = new CustomerAccountClass.CustomerAccountDetails(); //Create new customer account to compare        
        
        String strTextToCompare = null;                               
                   
        for(int i = 0; i < listOfCustomers.size(); i++)
        {
            
            foundCustomer = new CustomerAccountClass.CustomerAccountDetails();
            
            switch(intFieldNum)
            {
                
                case 0: //Customer account number
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerAccountNumber; 
                       break;                   
                   
                case 1: //Customer name
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerName; 
                       break;
                
                case 2: //Customer address line 1
                                    
                       strTextToCompare = listOfCustomers.get(i).CustomerAddressLine1; 
                       break;
                
                case 3: //Customer address line 2
            
                        strTextToCompare = listOfCustomers.get(i).CustomerAddressLine2; 
                        break;
                
                case 4: //Customer city
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerCity; 
                       break;
                
                case 5: //Customer province
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerProvince; 
                       break;
                       
                case 6: //Customer postal code
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerPostalCode; 
                       break;
                
                case 7: //Customer attention
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerAttention; 
                       break;
                       
                case 8: //Customer phone number
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerPhone; 
                       break;
                       
                case 9: //Customer fax number
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerFax; 
                       break;
                       
                case 10: //Customer email
                    
                       strTextToCompare = listOfCustomers.get(i).CustomerEmail; 
                       break;
                                 
            }
            
           if(Objects.equals(strTextToFind, strTextToCompare) || strTextToCompare.contains(strTextToFind))
           {
               
               foundCustomer.CustomerAccountNumber = listOfCustomers.get(i).CustomerAccountNumber;
               foundCustomer.CustomerName = listOfCustomers.get(i).CustomerName;
               foundCustomer.CustomerAddressLine1 = listOfCustomers.get(i).CustomerAddressLine1;
               foundCustomer.CustomerAddressLine2 = listOfCustomers.get(i).CustomerAddressLine2;
               foundCustomer.CustomerCity = listOfCustomers.get(i).CustomerCity;
               foundCustomer.CustomerProvince = listOfCustomers.get(i).CustomerProvince;
               foundCustomer.CustomerPostalCode = listOfCustomers.get(i).CustomerPostalCode;
               foundCustomer.CustomerAttention = listOfCustomers.get(i).CustomerAttention;
               foundCustomer.CustomerPhone = listOfCustomers.get(i).CustomerPhone;
               foundCustomer.CustomerFax = listOfCustomers.get(i).CustomerFax;
               foundCustomer.CustomerEmail = listOfCustomers.get(i).CustomerEmail;
                    
               currentListOfFoundCustomers.add(foundCustomer);
               
               boolFound = true;             
               
           }
           else
           {
               
               boolFound = false;
               
               
           }
                      
       }    
        
        return currentListOfFoundCustomers;
       
    }
    
    public void AddCustomerToFile(LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers, CustomerAccountClass.CustomerAccountDetails newAccount)
    {
        // Adds new customer to customer database file
        
        FileIOClass newFileIO = new FileIOClass();        
       
        //Add account
        listOfCustomers.add(newAccount);        
               
        //Sort list
       
        SortCustomerList(listOfCustomers);
        
        //Save to file
        
        newFileIO.WriteCustomerListToFile(listOfCustomers);
                
    }
    
    public void DeleteCustomerFromFile(LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers, int intPositionInFile)
    {
    
        // Deletes the customer account from the customer database file
           
        FileIOClass newFileIO = new FileIOClass();
        
        // LoadCustomerDatabase(listOfCustomers);
        
        // Delete account from list          
              
        listOfCustomers.remove(intPositionInFile);
        
        //Sort list
       
         SortCustomerList(listOfCustomers);
                
        //Write list to file
        
        newFileIO.WriteCustomerListToFile(listOfCustomers);
        
        
    }
        
    private void SortCustomerList(LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers)            
    {        
        //Sorts the list of customers    
            
        Collections.sort(listOfCustomers, (a, b) -> a.CustomerAccountNumber.compareTo(b.CustomerAccountNumber));
                
    }
    
    public void UpdateCustomerAccountInFile(LinkedList<CustomerAccountClass.CustomerAccountDetails> listOfCustomers, int intPositionInList, CustomerAccountClass.CustomerAccountDetails newAccount)              
    {              
        //Updates the customer account details in the file
             
        FileIOClass newFileIO = new FileIOClass();
        
       // LoadCustomerDatabase(listOfCustomers);   
           
        //Update list
        
        listOfCustomers.set(intPositionInList, newAccount);
        
        //Sort list
        
        SortCustomerList(listOfCustomers);
        
        //Write list to file
        
        newFileIO.WriteCustomerListToFile(listOfCustomers);
           
    }
     
    private String CheckString(String strToCheck)
    {
        // Edits the string for proper values to save to CSV file
        
        String empty = "";
        
        String strReturn = "";
        
        if(strToCheck.contains("\""))
        {
            
            strToCheck = strToCheck.replace("\"", "\"\"");
            
        }
        
        if(strToCheck.isEmpty() || strToCheck.equals(empty))
        {
            
            strToCheck = " ";
            
            
        }      
              
        strReturn = strToCheck;
        
        return strToCheck;
        
    }
    
}
