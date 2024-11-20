
package it2c.alcala.prs;

import java.util.Scanner;


public class Activities {
     public void activity(){
        
        Scanner sc=new Scanner(System.in);
        String response;
        do{
        System.out.println("\n----------------------");
        System.out.println("ACTIVITY PANEL");
        System.out.println("1.ADD ACTIVITY");
        System.out.println("2.VIEW  ACTIVITY");
        System.out.println("3.UPDATE ACTIVITY");
        System.out.println("4.DELETE  ACTIVITY");
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
        
       Activities ac =new Activities();
        switch(action){
            
            case 1:
                ac.addActivity();
                break;
            case 2:
                ac.viewActivity();
                break;
            case 3:
                ac.viewActivity();
                ac.updateActivity();
                ac.viewActivity();
                break;
            case 4:
                ac.viewActivity();
                ac.deleteActivity();
                ac.viewActivity();
                break;
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
      public void addActivity(){
        Scanner sc = new Scanner(System.in);
       String aname,atime,loc,asponsor;
         while (true) {
            System.out.print("Activity Name: ");
            aname =sc.nextLine();
            if (aname.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the activity name.");
            }
        }
         while (true) {
            System.out.print("Activity Time: ");
            atime =sc.nextLine();
          if (atime.matches("[a-zA-Z0-9 ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters and numbers for activity time.");
            }
        }
          while (true) {
            System.out.print("Location: ");
           loc = sc.nextLine();
            if (loc.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the location.");
            }
        }
       while (true) {
            System.out.print("Activity Sponsor: ");
          asponsor = sc.nextLine();
            if (asponsor.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the sponsor.");
            }
        }

        String sql = "INSERT INTO activity (a_name, a_time, a_location,a_sponsor) VALUES (?, ?, ?, ?)";

         config conf = new config();
        conf.addRecord(sql, aname, atime, loc,asponsor);
    }    
       public void viewActivity() {
        String citizenQuery = "SELECT * FROM activity";
        String [] citizenHeaders = {"ID","Activity Name","Time","Location", "Sponsor"};
        String[] citizenColumns = {"a_id", "a_name", "a_time","a_location", "a_sponsor"};
       config conf = new config();
       conf.viewRecords(citizenQuery, citizenHeaders, citizenColumns);
    }
        private void updateActivity(){
    Scanner sc= new Scanner(System.in);   
    config conf = new config();
        System.out.println  ("Enter Activity ID:");
        int id = sc.nextInt();
            
          while(conf.getSingleValue("SELECT a_id FROM activity  WHERE a_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Activity ID Again:");
              id=sc.nextInt();
          }
     String naname,natime,nloc,nasponsor;
         while (true) {
            System.out.print("Enter new Activity Name: ");
            naname =sc.next();
            if (naname.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the activity name.");
            }
        }
         while (true) {
            System.out.print("Enter new Activity Time: ");
            natime =sc.next();
          if (natime.matches("[a-zA-Z0-9 ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters and numbers for activity time.");
            }
        }
          while (true) {
            System.out.print("Enter new Location: ");
           nloc = sc.next();
            if (nloc.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the location.");
            }
        }
       while (true) {
            System.out.print("Enter new Activity Sponsor: ");
          nasponsor = sc.next();
            if (nasponsor.matches("[a-zA-Z ]+")) { 
                break;
            } else {
                System.out.println("Invalid input. Please enter letters only for the sponsor.");
            }
        }

        String qry = "UPDATE activity SET a_name = ?,a_time = ?, a_location = ?, a_sponsor = ? WHERE a_id = ?";

    
        conf.updateRecord(qry, naname,natime,nloc,nasponsor,id);

       }
          private void deleteActivity(){

        Scanner sc= new Scanner(System.in);   
         config conf = new config();
           System.out.println("Enter activity ID to delete:");
           int id = sc.nextInt();
            while(conf.getSingleValue("SELECT a_id FROM activity  WHERE a_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Activity ID Again:");
              id=sc.nextInt();
          }

          String sqlDelete = "DELETE FROM activity  WHERE a_id=?";
          
           conf.deleteRecord(sqlDelete, id);
      }

}

