
package it2c.alcala.prs;

import java.util.Scanner;


public class IT2CALCALAPRS {
 public void addCitizen(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print(" First Name: ");
        String fname = sc.next();
        System.out.print(" Last Name: ");
        String lname = sc.next();
        System.out.print(" Purok: ");
        String purok = sc.next();
        System.out.print(" Contact number: ");
        String contact = sc.next();
        System.out.print(" Status: ");
        String status = sc.next();

        String sql = "INSERT INTO citizen (f_name, l_name, e_purok,s_contact, e_status) VALUES (?, ?, ?, ?,?)";


        conf.addRecord(sql, fname, lname, purok,contact, status);


    }
  
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       String response;
       do{
           System.out.println("1. ADD");
           System.out.println("2. VIEW");
           System.out.println("3. UPDATE");
           System.out.println("4. DELETE");
           System.out.println("5. EXIT");
           
           System.out.println("Enter Action");
            int action =sc.nextInt();
            switch(action){
                case 1:
           IT2CALCALAPRS purok= new IT2CALCALAPRS ();
           purok.addCitizen();
           break;
            }
            System.out.println("Continue?(yes/no):");
            response=sc.next();
       }while(response.equals("yes"));
            System.out.println("Thank you for using");
              
           
           
            
}
}