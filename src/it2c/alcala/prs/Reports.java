
package it2c.alcala.prs;

import java.sql.Date;
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
        System.out.print("Do you want to continue? (yes/no): ");
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
        String [] citizenHeaders = {"ID","First Name","Last Name","Purok", "Contact","Status"};
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
        + "a.a_id, a.a_name AS a_name, a.a_time AS a_time, a.a_location AS a_location, "
        + "att.att_date AS att_date, att.att_status AS att_status "
        + "FROM citizen "
        + "LEFT JOIN attendance att ON citizen.s_id = att.s_id "
        + "LEFT JOIN activity a ON att.a_id = a.a_id "
        + "WHERE citizen.s_id = " + citizenId;


    String[] headers = {
            "Citizen ID", "First Name", "Last Name", "Purok", "Contact", "Status",
            "Activity ID", "Activity Name", "Time", "Location", "Attendance Date", "Attendance Status"
    };
    String[] columns = {
            "s_id", "f_name", "l_name", "e_purok", "s_contact", "e_status",
            "a_id", "a_name", "a_time", "a_location", "att_date", "att_status"
    };

 
    conf.viewRecords(qry, headers, columns);
}
private void generalReport() {
    config conf = new config();
    System.out.println("\n--- General Report ---");
    

   
   String qry = "SELECT citizen.s_id, citizen.f_name, citizen.l_name, citizen.e_purok, citizen.s_contact, citizen.e_status, "
        + "a.a_id, a.a_name AS a_name, a.a_time AS a_time, a.a_location AS a_location, "
        + "att.att_date AS att_date, att.att_status AS att_status "
        + "FROM citizen "
        + "LEFT JOIN attendance att ON citizen.s_id = att.s_id "
        + "LEFT JOIN activity a ON att.a_id = a.a_id";

    
    String[] headers = {
            "Citizen ID", "First Name", "Last Name", "Purok", "Contact", "Status",
            "Activity ID", "Activity Name", "Time", "Location", "Attendance Date", "Attendance Status"
    };

  
    String[] columns = {
            "s_id", "f_name", "l_name", "e_purok", "s_contact", "e_status",
            "a_id", "a_name", "a_time", "a_location", "att_date", "att_status"
    };

    
    conf.viewRecords(qry, headers, columns);
}

}