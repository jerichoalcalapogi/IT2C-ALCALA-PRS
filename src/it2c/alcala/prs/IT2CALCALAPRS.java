
package it2c.alcala.prs;

import java.util.Scanner;


public class IT2CALCALAPRS {
 
  
    public static void main(String[] args) {
        
            Scanner sc= new Scanner(System.in);
        boolean exit = true;
        do{
            
   
            
        System.out.println ("WELCOME TO PUROK ATTENDANCE SYSTEM");
        System.out.println ("--------------------------------------");
        System.out.println ("1. CITIZEN");
        System.out.println ("2. ACTIVITIES");
        System.out.println ("3. ATTENDANCE");
        System.out.println ("4. REPORTS");
        System.out.println ("5 .EXIT");
       
        int action;
               while (true) {
                System.out.print("Enter Action: ");
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
        
        switch(action){
      case 1:
            Citizen cs = new Citizen();
            cs.citizen();
            break;
      case 2:
            Activities ac = new Activities();
            ac.activity();
            break;
      case 3:
            Attendance att = new Attendance();
            att.Aimplementation();
            break;
      case 4:
            Reports rep = new Reports();
            rep.ReportsMenu();
            break;           
      case 5:
            System.out.println("Exit Selected...type 'yes' to continue");
            String resp=sc.next();
            if(resp.equalsIgnoreCase("yes")){
            exit = false;
                }
                
            break;
        }
        
                
      }while(exit);

    }
}