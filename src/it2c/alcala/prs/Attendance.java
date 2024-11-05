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
            System.out.print("Enter Selection: ");
            int act = sc.nextInt();
            Attendance att = new Attendance();
            switch (act) {
                case 1:
                    att.addAttendance();
                    break;
                case 2:
                   att.viewAttendance();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return; 
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

   
    sc.nextLine();
    System.out.print("Enter attendance date (YYYY-MM-DD): "); 
    String datee = sc.nextLine();
    System.out.print("Enter status (present/absent): ");
    String status = sc.nextLine();
     System.out.println("Total attendees");

    
    String attqry = "INSERT INTO attendance (s_id, a_id, att_date, att_status) VALUES (?, ?, ?, ?)";
    
    // Update this line to pass the correct types: cid, aid are integers; datee and status are strings; totalAttendees is integer
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
}