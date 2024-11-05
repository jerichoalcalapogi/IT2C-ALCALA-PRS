

package it2c.alcala.prs;

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
            System.out.println("Enter Selection:");
            int act = sc.nextInt();
            Attendance att = new Attendance();
            switch (act) {
                case 1:
                    att.addAttendance();
                    break;
                case 2:
                 
                    break;
                case 5:
                 
                    break;
            }
            System.out.println("Do you want to continue? (yes/no)");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));
    }

    private void addAttendance() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        Citizen cs = new Citizen();
        cs.viewCitizen();

        System.out.print("Enter the ID of Citizen:");
        int cid = sc.nextInt();
        
        String csql = "SELECT s_id FROM citizen WHERE s_id = ?";
        
        while (conf.getSingleValue(csql, cid) == 0) {
            System.out.print("Citizen does not exist, select again: ");
            cid = sc.nextInt();
        }
          Activities ac = new Activities();
            ac.viewActivity();
            
        System.out.print("Enter the ID of Activity:");
        int aid = sc.nextInt();
        
        String asql = "SELECT a_id FROM activity WHERE a_id = ?";
        
        while (conf.getSingleValue(asql, aid) == 0) {
            System.out.print("Activity does not exist, select again: ");
            aid = sc.nextInt();
        }
    
        System.out.println("Enter ");
    }
}
