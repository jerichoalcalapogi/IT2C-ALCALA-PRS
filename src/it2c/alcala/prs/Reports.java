package it2c.alcala.prs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;


public class Reports {
   public void ReportsMenu() {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("\n========== REPORTS MENU ==========");
            System.out.println("1. Individual Citizen Report");
            System.out.println("2. General Report");
            System.out.println("3. Exit");
             int action;
               while (true) {
                System.out.print("Enter your choice: ");
                if (sc.hasNextInt()) {
                    action = sc.nextInt();
                if (action >= 1 && action <= 3) {
                    break; 
                    }
                } else {
                    sc.next(); 
                }
                System.out.println("Invalid selection,Please enter a number between 1 and 3 only.");
            }
          
               Reports rep = new Reports();
            switch (action) {
                case 1:
                    rep.individualReport(sc);
                    break;
                case 2:
                  rep. generalReport();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            
            case 5:
                
                break;
            
        }
         while (true) {
        System.out.print("\nDo you want to continue? (yes/no): ");
        response = sc.next();
        if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no")) {
            break; 
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'");
        }
    }
    
    if (response.equalsIgnoreCase("no")) {
        System.out.println("Going back to the main menu...\n");
    }  
} while(response.equalsIgnoreCase("yes"));

   }
   

  private void individualReport(Scanner sc) {
    config conf = new config();

    System.out.println("\n--- Individual Citizen Report ---");

     String citizenQuery = "SELECT * FROM citizen";
        String [] citizenHeaders = {"ID","First Name","Last Name","Purok", "Contact No.","Status"};
        String[] citizenColumns = {"s_id", "f_name", "l_name","e_purok", "s_contact", "e_status"};
    
       conf.viewRecords(citizenQuery, citizenHeaders, citizenColumns);
    

    int citizenId = validCitizenID(sc, conf);

  
    displayCitizenDetails(citizenId);
}


    private int validCitizenID(Scanner sc, config conf) {
        int citizenId;
        String sql = "SELECT COUNT(*) FROM citizen WHERE s_id = ?";
        while (true) {
            System.out.print("Enter the Citizen ID: ");
            if (sc.hasNextInt()) {
                citizenId = sc.nextInt();
                if (conf.getSingleValue(sql, citizenId) > 0) {
                    return citizenId;
                } else {
                    System.out.println("Invalid Citizen ID. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Enter a numeric value.");
                sc.next(); 
            }
        }
    }

private void displayCitizenDetails(int citizenId) {
        config conf = new config();
        String qry = "SELECT citizen.s_id, citizen.f_name, citizen.l_name, citizen.e_purok, citizen.s_contact, citizen.e_status, "
                + "a.a_id AS activity_id, a.a_name AS activity_name, a.a_time AS activity_time, a.a_location AS activity_location, "
                + "a.a_sponsor AS activity_sponsor, att.att_date AS attendance_date "
                + "FROM citizen "
                + "LEFT JOIN attendance att ON citizen.s_id = att.s_id "
                + "LEFT JOIN activity a ON att.a_id = a.a_id "
                + "WHERE citizen.s_id = " + citizenId;

        System.out.println("\n--- Individual Citizen Report ---");

        String citizenInfoQuery = "SELECT s_id, f_name, l_name, e_purok, s_contact, e_status FROM citizen WHERE s_id = " + citizenId;
        try (ResultSet rs = conf.executeQuery(citizenInfoQuery)) {
            if (rs.next()) {
                System.out.printf("Citizen ID    : %-5d\n", rs.getInt("s_id"));
                System.out.printf("First Name    : %-15s\n", rs.getString("f_name"));
                System.out.printf("Last Name     : %-15s\n", rs.getString("l_name"));
                System.out.printf("Purok         : %-15s\n", rs.getString("e_purok"));
                System.out.printf("Contact No.   : %-15s\n", rs.getString("s_contact"));
                System.out.printf("Status        : %-15s\n", rs.getString("e_status"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving citizen details: " + e.getMessage());
        }

        System.out.println("\n--- Activities for Citizen ID: " + citizenId + " ---");
        String[] activityHeaders = {"Activity ID", "Activity Name", "Time", "Location", "Sponsor", "Attendance Date"};
        String[] activityColumns = {"activity_id", "activity_name", "activity_time", "activity_location", "activity_sponsor", "attendance_date"};
        conf.viewRecords(qry, activityHeaders, activityColumns);

        System.out.println("\nSummary:");

        String summaryQuery =
                "WITH ranked_activities AS ( " +
                "    SELECT att.att_date, a.a_name, a.a_time, " +
                "           ROW_NUMBER() OVER (PARTITION BY att.att_date ORDER BY a.a_time ASC) AS first_rank, " +
                "           ROW_NUMBER() OVER (PARTITION BY att.att_date ORDER BY a.a_time DESC) AS last_rank " +
                "    FROM attendance att " +
                "    JOIN activity a ON att.a_id = a.a_id " +
                "    WHERE att.s_id = " + citizenId + " " +
                ") " +
                "SELECT COUNT(*) AS total_activities_attended, " +
                "    (SELECT a_name FROM ranked_activities WHERE first_rank = 1 ORDER BY att_date ASC LIMIT 1) AS first_activity_name, " +
                "    (SELECT a_name FROM ranked_activities WHERE last_rank = 1 ORDER BY att_date DESC LIMIT 1) AS last_activity_name " +
                "FROM ranked_activities;";

        try (ResultSet rs = conf.executeQuery(summaryQuery)) {
            if (rs.next()) {
                int totalActivities = rs.getInt("total_activities_attended");
                String firstActivityName = rs.getString("first_activity_name");
                String lastActivityName = rs.getString("last_activity_name");

                System.out.printf(" Total  Activities Attended : %-2d\n", totalActivities);
                System.out.printf(" First  Activity   Attended : %-15s\n", (firstActivityName != null ? firstActivityName : "No activity attended"));
                System.out.printf(" Last   Activity   Attended : %-15s\n", (lastActivityName != null ? lastActivityName : "No activity attended"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving summary: " + e.getMessage());
        }
    }


private void generalReport() {
    config conf = new config();
    System.out.println("\n--- General Report ---");

    String qry = "WITH RankedActivities AS ("
                + "    SELECT citizen.f_name, citizen.l_name, "
                + "           a.a_name AS activity_name, a.a_location AS location, "
                + "           att.att_date AS activity_date, "
                + "           ROW_NUMBER() OVER (PARTITION BY citizen.s_id ORDER BY att.att_date) AS row_num "
                + "    FROM citizen "
                + "    LEFT JOIN attendance att ON citizen.s_id = att.s_id "
                + "    LEFT JOIN activity a ON att.a_id = a.a_id "
                + ") "
                + "SELECT f_name, l_name, activity_name, location, activity_date "
                + "FROM RankedActivities "
                + "WHERE row_num = 1";

    String[] headers = {
            "First Name", "Last Name", "Activity Name", "Location", "Activity Date"
    };

    String[] columns = {
            "f_name", "l_name", "activity_name", "location", "activity_date"
    };

    conf.viewRecords(qry, headers, columns);
}
}