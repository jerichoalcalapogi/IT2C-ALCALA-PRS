
package it2c.alcala.prs;

import java.util.Scanner;


public class IT2CALCALAPRS {
 public void addCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print (" First Name: ");
        String fname = sc.next();
        System.out.print (" Last Name: ");
        String lname = sc.next();
        System.out.print (" Purok: ");
        String purok = sc.next();
        System.out.print (" Contact number: ");
        String contact = sc.next();
        System.out.print( " Status: ");
        String status = sc.next();

        String sql = "INSERT INTO citizen (f_name, l_name, e_purok,s_contact, e_status) VALUES (?, ?, ?, ?,?)";


        conf.addRecord(sql, fname, lname, purok,contact, status);


    }
  
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
            cs. citizen();
            break;
             case 2:
         Activities ac = new Activities();
           ac. activity();
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
        

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//       Scanner sc=new Scanner(System.in);
//       String response;
//       do{
//           System.out.println ("1. ADD");
//           System.out.println ("2. VIEW");
//           System.out.println ("3. UPDATE");
//           System.out.println ("4. DELETE");
//           System.out.println ("5. EXIT");
//           
//           System.out.println("Enter Action:");
//            int action =sc.nextInt();
//            IT2CALCALAPRS purok= new IT2CALCALAPRS ();
//            switch(action){
//                case 1:
//                     
//                     purok.addCitizen();
//           break;
//                case 2:
//                     
//                     purok.viewCitizen();
//                case 3:
//                    
//                     purok.viewCitizen();
//                     purok.updateCitizen(); 
//                case 4:
//                    
//                     purok.viewCitizen();
//                     purok.deleteCitizen(); 
//                     purok.viewCitizen();
//            }
//            System.out.println("Continue?(yes/no):");
//            response=sc.next();
//       }while(response.equals("yes"));
//            System.out.println("Thank you for using");
//              
//             
//            
//}
//    
//    private void viewCitizen() {
//        String citizenQuery = "SELECT * FROM citizen";
//        String [] citizenHeaders = {"ID","First Name","Last Name","Purok", "Contact","Status"};
//        String[] citizenColumns = {"s_id", "f_name", "l_name","e_purok", "s_contact", "e_status"};
//       config conf = new config();
//        conf.viewRecords(citizenQuery, citizenHeaders, citizenColumns);
//    }
//    
//    private void updateCitizen(){
//
//     Scanner sc= new Scanner(System.in);   
//        System.out.println  ("Enter citizen ID:");
//        int id = sc.nextInt();
//        System.out.println ("Enter new First Name:");
//        String nfname= sc.next();
//        System.out.println ("Enter new LastName:");
//        String nlname= sc.next();
//        System.out.println ("Enter new Purok:");
//        String nfpurok= sc.next();
//        System.out.println ("Enter new Contact:");
//        String ncontact= sc.next(); 
//        System.out.println ("Enter new Status:");
//        String nstat= sc.next();
//
//
//        String qry = "UPDATE citizen SET f_name = ?,l_name = ?, e_purok = ?, s_contact = ?, e_status = ? WHERE s_id = ?";
//
//        config conf = new config();
//        conf.updateRecord(qry, nfname,nlname,nfpurok,ncontact,nstat,id);
//
//       }
//   private void deleteCitizen(){
//
//        Scanner sc= new Scanner(System.in);   
//           System.out.println("Enter citizen ID to delete:");
//           int id = sc.nextInt();
//
//           String sqlDelete = "DELETE FROM citizen  WHERE s_id=?";
//           config conf = new config();
//           conf.deleteRecord(sqlDelete, id);
//       }
//
//   }

    }
}