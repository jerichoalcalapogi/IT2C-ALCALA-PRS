
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
        
        System.out.println("Enter Selection:");
        int act = sc.nextInt();
        
       Activities ac =new Activities();
        switch(act){
            
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
        System.out.println("Do you want to continue?(yes/no):");
        response = sc.next();
        
        
        
    }while(response.equalsIgnoreCase("yes"));
     }
      public void addActivity(){
        Scanner sc = new Scanner(System.in);
       
        System.out.print (" Activity Name: ");
        String aname = sc.next();
        System.out.print (" Activity Date: ");
        String adate = sc.next();
        System.out.print (" Location: ");
        String loc = sc.next();
        System.out.print (" Activity Sponsor: ");
        String asponsor = sc.next();
       

        String sql = "INSERT INTO activity (a_name, a_date, a_location,a_sponsor) VALUES (?, ?, ?, ?)";

         config conf = new config();
        conf.addRecord(sql, aname, adate, loc,asponsor);
    }    
       public void viewActivity() {
        String citizenQuery = "SELECT * FROM activity";
        String [] citizenHeaders = {"ID","Activity Name","Date","Location", "Sponsor"};
        String[] citizenColumns = {"a_id", "a_name", "a_date","a_location", "a_sponsor"};
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
              System.out.println("Select Activity ID Again");
              id=sc.nextInt();
          }
        System.out.println ("Enter new Activity Name:");
        String naname= sc.next();
        System.out.println ("Enter new Date:");
        String nadate= sc.next();
        System.out.println ("Enter new Location:");
        String nloc= sc.next();
        System.out.println ("Enter new Sponsor:");
        String nasponsor= sc.next(); 
      


        String qry = "UPDATE activity SET a_name = ?,a_date = ?, a_location = ?, a_sponsor = ? WHERE a_id = ?";

    
        conf.updateRecord(qry, naname,nadate,nloc,nasponsor);

       }
          private void deleteActivity(){

        Scanner sc= new Scanner(System.in);   
         config conf = new config();
           System.out.println("Enter activity ID to delete:");
           int id = sc.nextInt();
            while(conf.getSingleValue("SELECT a_id FROM activity  WHERE a_id=?  ",id)==0){
              System.out.println("Selected ID doesn't exist");
              System.out.println("Select Activity ID Again");
              id=sc.nextInt();
          }

          String sqlDelete = "DELETE FROM activity  WHERE a_id=?";
          
           conf.deleteRecord(sqlDelete, id);
      }

}

