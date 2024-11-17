
package it2c.alcala.prs;

import java.util.Scanner;


public class Citizen {

    public void citizen(){
        
        Scanner sc=new Scanner(System.in);
        String response;
        do{
        System.out.println("\n----------------------");
        System.out.println("WELCOME TO CITIZEN PANEL");
        System.out.println("1.ADD CITIZEN");
        System.out.println("2.VIEW  CITIZEN");
        System.out.println("3.UPDATE  CITIZEN");
        System.out.println("4.DELETE  CITIZEN");
        System.out.println("5. EXIT");
          int action;
         while (true) {
                System.out.print("Enter Selection: ");
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                    if (action >= 1 && action <= 5) {
                        break; 
                    }
                } else {
                    sc.next(); 
                }
                System.out.println("Invalid selection,Please enter a number between 1 and 5 only.");
            }
        Citizen cs =new Citizen();
        
        switch(action){
            
            case 1:
                cs.addCitizen();
                break;
            case 2:
                cs.viewCitizen();
                break;
            case 3:
                cs.viewCitizen();
                cs.updateCitizen();
                cs.viewCitizen();
                break;
            case 4:
                cs.viewCitizen();
                cs.deleteCitizen();
                cs.viewCitizen();
                break;
             case 5:
                
                break;
            
        }
        System.out.println("Do you want to continue?(yes/no):");
        response = sc.next();
      
        if (response.equalsIgnoreCase("no")) {
            System.out.println("Going back to the main menu...\n");
        
        }  
    }while(response.equalsIgnoreCase("yes"));
        
   }
    
     public void addCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
      String fname,lname,purok,contact,status;
      
        while (true) {
            System.out.print("First Name: ");
            fname = sc.nextLine();
            if (fname.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the first name.");
            }
        }
      
        while (true) {
            System.out.print("Last Name: ");
             lname = sc.nextLine();
            if (lname.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the last name.");
            }
        }
         while (true) {
            System.out.print("Purok: ");
             purok = sc.nextLine();
          if (purok.matches("[a-zA-Z0-9 ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters and numbers for purok.");
            }
        }
         while (true) {
            System.out.print("Contact Number: ");
             contact = sc.nextLine();
           if (contact.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter numbers only for purok.");
            }
        }
          while (true) {
        System.out.print("Status (Single/Married/Divorced): ");
        status = sc.nextLine();
        if (status.equalsIgnoreCase("Single") || status.equalsIgnoreCase("Married") || status.equalsIgnoreCase("Divorced")) {
            break;
        } else {
            System.out.println("Invalid input. Please enter 'Single', 'Married', or 'Divorced'.");
        }
    }

        String sql = "INSERT INTO citizen (f_name, l_name, e_purok,s_contact, e_status) VALUES (?, ?, ?, ?,?)";
     

        conf.addRecord(sql, fname, lname, purok,contact, status);
     

    }
    
   public void viewCitizen() {
        String citizenQuery = "SELECT * FROM citizen";
        String [] citizenHeaders = {"ID","First Name","Last Name","Purok", "Contact","Status"};
        String[] citizenColumns = {"s_id", "f_name", "l_name","e_purok", "s_contact", "e_status"};
       config conf = new config();
       conf.viewRecords(citizenQuery, citizenHeaders, citizenColumns);
    }
     private void updateCitizen(){
    Scanner sc= new Scanner(System.in);   
    config conf = new config();
        System.out.println  ("Enter citizen ID:");
        int id = sc.nextInt();
            
          while(conf.getSingleValue("SELECT s_id FROM citizen  WHERE s_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Citizen ID Again:");
              id=sc.nextInt();
          }
     String nfname,nlname,npurok,ncontact,nstatus;
      
        while (true) {
            System.out.print(" Enter new First Name: ");
            nfname = sc.next();
            if (nfname.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the first name.");
            }
        }
      
        while (true) {
            System.out.print("Enter new Last Name: ");
             nlname = sc.next();
            if (nlname.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the last name.");
            }
        }
         while (true) {
            System.out.print("Enter new Purok: ");
             npurok = sc.next();
          if (npurok.matches("[a-zA-Z0-9 ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters and numbers for purok.");
            }
        }
         while (true) {
            System.out.print("Enter new Contact Number: ");
             ncontact = sc.next();
           if (ncontact.matches("\\d+")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter numbers only for contact number.");
            }
        }
         while (true) {
        System.out.print("Enter new Status (Single/Married/Divorced): ");
        nstatus = sc.next();
        if (nstatus.equalsIgnoreCase("Single") || nstatus.equalsIgnoreCase("Married") || nstatus.equalsIgnoreCase("Divorced")) {
            break;
        } else {
            System.out.println("Invalid input. Please enter 'Single', 'Married', or 'Divorced'.");
        }
    }



        String qry = "UPDATE citizen SET f_name = ?,l_name = ?, e_purok = ?, s_contact = ?, e_status = ? WHERE s_id = ?";

    
        conf.updateRecord(qry, nfname,nlname,npurok,ncontact,nstatus,id);

       }
    private void deleteCitizen(){

        Scanner sc= new Scanner(System.in);   
         config conf = new config();
           System.out.println("Enter citizen ID to delete:");
           int id = sc.nextInt();
            while(conf.getSingleValue("SELECT s_id FROM citizen  WHERE s_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Citizen ID Again:");
              id=sc.nextInt();
          }

          String sqlDelete = "DELETE FROM citizen  WHERE s_id=?";
          
           conf.deleteRecord(sqlDelete, id);
      }

  }

    
