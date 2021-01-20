import java.io.*;
import java.net.*;
import java.util.*;

// Define a Login interface
    interface Login 
    {
        boolean verify(); // use a Abstract metthod
    }

    class Account implements Login
    {
        private final String card_number;
        private final String password;
        private double balance;

        
        public Account(String card_number, String password, double balance) {
            this.card_number = card_number;
            this.password = password;
            this.balance = balance;
        }

        
@Override
public boolean verify() {
    try {
        File myObj = new File("C:\\Users\\MSI\\Desktop\\Cyclone\\Family\\src\\credentials.txt");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] parts = line.split(",");
            String c_number = parts[0];
            String pwd = parts[1];
            if (this.card_number.equals(c_number) && this.password.equals(pwd)) {
                myReader.close(); // Close the scanner before returning
                return true;
            }
            // No need to return false here, as it should continue checking other lines
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    // If no matching credentials were found in the file
    return false;
}


        
}







