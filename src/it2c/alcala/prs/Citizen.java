
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
        System.out.println("5,EXIT");
        
        System.out.println("Enter Selection");
        int act = sc.nextInt();
        
        Citizen cs =new Citizen();
        
        switch(act){
            
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
        
        
        
    }while(response.equalsIgnoreCase("yes"));
        
   }
    
     public void addCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print (" First Name: ");
        String fname = sc.next();
        System.out.print (" Last Name: ");
        String lname = sc.next();
        System.out.print (" Purok: ");
        String purok = sc.next();
        System.out.print (" Contact number: ");
        String contact = sc.next();
        System.out.print( " Status: ");
        String status = sc.next();

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
              System.out.println("Select Citizen ID Again");
              id=sc.nextInt();
          }
        System.out.println ("Enter new First Name:");
        String nfname= sc.next();
        System.out.println ("Enter new LastName:");
        String nlname= sc.next();
         System.out.println ("Enter new Purok:");
        String nfpurok= sc.next();
        System.out.println ("Enter new Contact:");
        String ncontact= sc.next(); 
        System.out.println ("Enter new Status:");
        String nstat= sc.next();


        String qry = "UPDATE citizen SET f_name = ?,l_name = ?, e_purok = ?, s_contact = ?, e_status = ? WHERE s_id = ?";

    
        conf.updateRecord(qry, nfname,nlname,nfpurok,ncontact,nstat,id);

       }
    private void deleteCitizen(){

        Scanner sc= new Scanner(System.in);   
         config conf = new config();
           System.out.println("Enter citizen ID to delete:");
           int id = sc.nextInt();
            while(conf.getSingleValue("SELECT s_id FROM citizen  WHERE s_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Citizen ID Again");
              id=sc.nextInt();
          }

          String sqlDelete = "DELETE FROM citizen  WHERE s_id=?";
          
           conf.deleteRecord(sqlDelete, id);
      }

  }

    
