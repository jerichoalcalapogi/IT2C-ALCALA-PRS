
package it2c.alcala.prs;

import java.sql.Date;
import java.util.Scanner;



public class Reports {
   public void showReportsMenu() {
        Scanner sc = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n========== REPORTS MENU ==========");
            System.out.println("1. Individual Citizen Report");
            System.out.println("2. Summary Report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int option = getValidChoice(sc, 1, 3);

            switch (option) {
                case 1:
                    generateIndividualReport(sc);
                    break;
                case 2:
                  
                    break;
                case 3:
                    System.out.println("Exiting the Reports Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.print("Would you like to return to the menu? (yes/no): ");
            choice = sc.next();

        } while (choice.equalsIgnoreCase("yes"));
    }

    private int getValidChoice(Scanner sc, int min, int max) {
        int choice;
        while (true) {
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid input. Please select between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Enter a numeric value.");
                sc.next(); 
            }
        }
    }

  private void generateIndividualReport(Scanner sc) {
    config conf = new config();

    System.out.println("\n--- Individual Citizen Report ---");

     String citizenQuery = "SELECT * FROM citizen";
        String [] citizenHeaders = {"ID","First Name","Last Name","Purok", "Contact","Status"};
        String[] citizenColumns = {"s_id", "f_name", "l_name","e_purok", "s_contact", "e_status"};
    
       conf.viewRecords(citizenQuery, citizenHeaders, citizenColumns);
    

    int citizenId = getValidCitizenId(sc, conf);

  
    displayCitizenDetails(citizenId);
}


    private int getValidCitizenId(Scanner sc, config conf) {
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
    String qry = "SELECT c.s_id, c.f_name, c.l_name, c.e_purok, c.s_contact, c.e_status, "
            + "a.a_id, a.a_name AS ActivityName, a.a_time AS Time, a.a_location AS Location, "
            + "att.att_date AS AttendanceDate, att.att_status AS AttendanceStatus "
            + "FROM citizen c "
            + "LEFT JOIN attendance att ON c.s_id = att.s_id "
            + "LEFT JOIN activity a ON att.a_id = a.a_id "
            + "WHERE c.s_id = " + citizenId;

    String[] headers = {
            "Citizen ID", "First Name", "Last Name", "Purok", "Contact", "Status",
            "Activity ID", "Activity Name", "Time", "Location", "Attendance Date", "Attendance Status"
    };
    String[] columns = {
            "s_id", "f_name", "l_name", "e_purok", "s_contact", "e_status",
            "a_id", "ActivityName", "Time", "Location", "AttendanceDate", "AttendanceStatus"
    };

 
    conf.viewRecords(qry, headers, columns);
}
}