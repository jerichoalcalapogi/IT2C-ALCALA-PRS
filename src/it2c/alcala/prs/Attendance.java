package it2c.alcala.prs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Attendance {
    public void Aimplementation() {
        Scanner sc = new Scanner(System.in);
        String response;
        do {
            System.out.println("\n----------------------");
            System.out.println("WELCOME TO ATTENDANCE PANEL");
            System.out.println("1.ADD ATTENDANCE");
            System.out.println("2.VIEW ATTENDANCE");
            System.out.println("3.UPDATE ATTENDANCE");
            System.out.println("4.DELETE ATTENDANCE");
            System.out.println("5.EXIT");
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
        
            Attendance att = new Attendance();
            switch (action) {
                case 1:
                   att.addAttendance();
                    break;
                case 2:
                   att.viewAttendance();
                    break;
                case 3:
                   att.viewAttendance();
                   att.updateAttendance();
                   att.viewAttendance();
                   break;
                       case 4:
                   att.viewAttendance();
                   att.deleteAttendance();
                   att.viewAttendance();
                   break;
               case 5:
                
                break;
            
            }
            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));
    }

 private void addAttendance() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    Citizen cs = new Citizen();
    cs.viewCitizen();

    System.out.print("Enter the ID of Citizen: ");
    int cid = sc.nextInt();

    String csql = "SELECT s_id FROM citizen WHERE s_id = ?";
    while (conf.getSingleValue(csql, cid) == 0) {
        System.out.print("Citizen does not exist, select again: ");
        cid = sc.nextInt();
    }

    Activities ac = new Activities();
    ac.viewActivity();

    System.out.print("Enter the ID of Activity: ");
    int aid = sc.nextInt();

    String asql = "SELECT a_id FROM activity WHERE a_id = ?";
    while (conf.getSingleValue(asql, aid) == 0) {
        System.out.print("Activity does not exist, select again: ");
        aid = sc.nextInt();
    }

     String datee,status;
    

    
    while (true) {
        System.out.print("Enter attendance date (YYYY-MM-DD): ");
        datee = sc.next(); 
        if (datee.matches("\\d{4}-\\d{2}-\\d{2}")) { 
            break; 
        } else {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
        }
    }

   
    while (true) {
        System.out.print("Status (Present/Absent): ");
        status = sc.next(); 
        if (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent")) {
            break; 
        } else {
            System.out.println("Invalid input. Please enter Present/Absent.");
        }
    }
    
    
    
    String attqry = "INSERT INTO attendance (s_id, a_id, att_date, att_status) VALUES (?, ?, ?, ?)";
    
    conf.addRecord(attqry, String.valueOf(cid), String.valueOf(aid), datee, status);
    System.out.println("Attendance added successfully!");
}
     
     

    
 
  private void viewAttendance() {
    
        String citizenQuery = "SELECT att_id,l_name,a_name,a_time,a_location,att_date,att_status FROM attendance " 
                +"LEFT JOIN citizen ON citizen.s_id = attendance.s_id "
                + "LEFT JOIN activity ON activity.a_id = attendance.a_id";
        
        String [] citizenHeaders = {"Attendance ID","Citizen Name","Activity Name","Time", "Location","Date","Status"};
        String[] citizenColumns = {"att_id", "l_name", "a_name","a_time", "a_location", "att_date", "att_status"};
       config conf = new config();
       conf.viewRecords(citizenQuery, citizenHeaders, citizenColumns);
    }
   private void updateAttendance(){
    Scanner sc= new Scanner(System.in);   
    config conf = new config();
        System.out.println  ("Enter Attendance ID:");
        int id = sc.nextInt();
            
          while(conf.getSingleValue("SELECT att_id FROM attendance  WHERE att_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Attendance ID Again:");
              id=sc.nextInt();
          }
       String attdate,attstat;
    

    
    while (true) {
        System.out.print("Enter new attendance date (YYYY-MM-DD): ");
       attdate = sc.next(); // 
        if (attdate.matches("\\d{4}-\\d{2}-\\d{2}")) { 
            break; 
        } else {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
        }
    }

   
    while (true) {
        System.out.print("Enter new Status (Present/Absent): ");
       attstat = sc.next(); 
        if (attstat.equalsIgnoreCase("Present") || attstat.equalsIgnoreCase("Absent")) {
            break; 
        } else {
            System.out.println("Invalid input. Please enter Present/Absent.");
        }
    }
    
         String qry = "UPDATE attendance SET att_date = ?,att_status = ?  WHERE att_id = ?";

    
        conf.updateRecord(qry, attdate,attstat,id);      
   }
   
         private void deleteAttendance(){

        Scanner sc= new Scanner(System.in);   
         config conf = new config();
           System.out.println("Enter attendance ID to delete:");
           int id = sc.nextInt();
            while(conf.getSingleValue("SELECT att_id FROM attendance  WHERE att_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Attendance ID Again:");
              id=sc.nextInt();
          }

          String sqlDelete = "DELETE FROM attendance  WHERE att_id=?";
          
           conf.deleteRecord(sqlDelete, id);
      }
}
