import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Balance_query {

    private final String card_number;
    

    public Balance_query(String card_number) {
        this.card_number = card_number;
        
    }


    

    public double getBalance() {
        try {
            File myObj = new File("C:\\Users\\MSI\\Desktop\\Cyclone\\Family\\src\\balance.txt");
            Scanner myReader = new Scanner(myObj);
    
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] parts = line.split(",");
                String c_number = parts[0];
                String bal = parts[1];
                Double amount = Double.parseDouble(bal);
    
                if (c_number.equals(card_number)) {
                    myReader.close();
                    return amount;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            // Return a default value or throw an exception
            // For example, return 0.0 or throw a custom exception
        }
    
        // Return a default value or throw an exception
        // For example, return 0.0 or throw a custom exception
        return 0.0;
    }
    
        
        


    public boolean deposit(double depo_amount) {
        ArrayList<Pair<String, Double>> stringDoubleList = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\MSI\\Desktop\\Cyclone\\Family\\src\\balance.txt");
            Scanner myReader = new Scanner(myObj);

           

            ///loading all data of files to arraylist
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] parts = line.split(",");
                String c_number = parts[0];
                String bal = parts[1];
                Double amount = Double.parseDouble(bal);
                stringDoubleList.add(new Pair<>(c_number, amount));
            }

            ///finding the corresponding balance of card number, and summing with the deposited balance
            for (Pair<String, Double> pair : stringDoubleList) {
                if (pair.getFirst().equals(card_number)) {
                    double total = pair.getSecond() + depo_amount;
                    pair.setSecond(total);
                    
                }
            }
            myReader.close();

           
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //writing the arraylist to the file
        try{
    
            FileWriter myWriter = new FileWriter("C:\\Users\\MSI\\Desktop\\Cyclone\\Family\\src\\balance.txt");
            for (Pair<String, Double> pair : stringDoubleList) {
                String line = pair.getFirst() + "," + pair.getSecond() + "\n";
                myWriter.write(line);
                
               
            }
        
            myWriter.close();
            return true;

        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
     


        return false;
    }

    public boolean withdraw(double W_amount) {
        ArrayList<Pair<String, Double>> stringDoubleList = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\MSI\\Desktop\\Cyclone\\Family\\src\\balance.txt");
            Scanner myReader = new Scanner(myObj);

           

            ///loading all data of files to arraylist
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] parts = line.split(",");
                String c_number = parts[0];
                String bal = parts[1];
                Double amount = Double.parseDouble(bal);
                stringDoubleList.add(new Pair<>(c_number, amount));
            }

            ///finding the corresponding balance of card number, and summing with the deposited balance
            for (Pair<String, Double> pair : stringDoubleList) {
                if (pair.getFirst().equals(card_number)) {
                    if(pair.getSecond()>= W_amount)
                    {
                        double total = pair.getSecond() - W_amount;
                        pair.setSecond(total);
                    }
                    
                    
                }
            }
            myReader.close();

           
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //writing the arraylist to the file
        try{
    
            FileWriter myWriter = new FileWriter("C:\\Users\\MSI\\Desktop\\Cyclone\\Family\\src\\balance.txt");
            
            for (Pair<String, Double> pair : stringDoubleList) {
                String line = pair.getFirst() + "," + pair.getSecond() + "\n";
                myWriter.write(line);
               
            }
        
            myWriter.close();
            return true;
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
     


        return false;
        
    }


 
}

class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }
}