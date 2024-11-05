
package it2c.alcala.prs;

import java.util.Scanner;


public class IT2CALCALAPRS {
 
  
    public static void main(String[] args) {
        
            Scanner sc= new Scanner(System.in);
        boolean exit = true;
        do{
        System.out.println("WELCOME TO PUROK ATTENDANCE SYSTEM");
        System.out.println("");
        System.out.println("1. CITIZEN");
        System.out.println("2. ACTIVITIES");
        System.out.println("3. PRS ATTENDANCE");
        System.out.println("4. REPORTS");
        System.out.println("5 .EXIT");
        
        System.out.println("Enter Action:");
        int action = sc.nextInt();
        
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