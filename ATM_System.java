import java.io.*;
import java.net.*;
import java.util.*;

public class ATM_System 
{
    public static void main(String[] args) throws IOException 
    {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t\t<~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~>");
        System.out.println("\t\t\t\t<~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~>");
        System.out.println("\t\t\t\t<~><~><~><~>                                                   <~><~><~><~>");
        System.out.println("\t\t\t\t<~><~><~><~>             Welcome to the ATM System             <~><~><~><~>");
        System.out.println("\t\t\t\t<~><~><~><~>                                                   <~><~><~><~>");
        System.out.println("\t\t\t\t<~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~>");
        System.out.println("\t\t\t\t<~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~><~>");
        System.out.print("\t\t\t\t\t\tEnter your card_number: ");
        String User_name = scanner.nextLine();
        System.out.print("\t\t\t\t\t\tEnter your password: ");
        String User_password = scanner.nextLine();
         Account userAccount = new Account(User_name, User_password, 5000.);
        if (userAccount.verify()) 
        {
            System.out.println();
            System.out.println("\t\t\t\t\t\t\tLogin successful!");
            while (true) {
                System.out.println();
                System.out.println("\t\t\t\t\t\t\tChoose an option:");
                System.out.println("\t\t\t\t\t\t\t1. Check Balance");
                System.out.println("\t\t\t\t\t\t\t2. Deposit");
                System.out.println("\t\t\t\t\t\t\t3. Withdraw");
                System.out.println("\t\t\t\t\t\t\t4. FAQ");
                System.out.println("\t\t\t\t\t\t\t5. Exit");
                System.out.println();
                System.out.print("\t\t\t\t\t\t\tEnter your choice: ");
                int choice = scanner.nextInt();

                Balance_query query = new Balance_query(User_name);
                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.println("\t\t\t\t\t\t\tBalance: " + query.getBalance()+"$");
                        System.out.print("\t\t\t\t\t\t\tEnter Any Key For Back >>");
                        char ch1 = (char) System.in.read();
                        break;
                       
                    case 2:
                        System.out.println();
                        System.out.print("\t\t\t\t\t\t\tEnter the deposit amount ($): ");
                        double depositAmount = scanner.nextDouble();
                        if(query.deposit(depositAmount))
                        {
                           System.out.println("\t\t\t\t\t\t\tBalance updated"); 
                        }
                        System.out.print("\t\t\t\t\t\t\tEnter Any Key For Back >>");
                        char ch2 = (char) System.in.read();
                        break;
                       
                    case 3:
                        System.out.println();
                        System.out.print("\t\t\t\t\t\t\tEnter the withdrawal amount $: ");
                        double withdraAmount = scanner.nextDouble();
                        if( query.withdraw(withdraAmount))
                        {
                         System.out.println("\t\t\t\t\t\t\tYour Withdrawal Has Been Successful!");
                        }
                        System.out.println();
                        break;
                       
                    case 4:
                        System.out.println();
                    // Display the FAQ Server
                        try {
                            Socket faqSocket = new Socket("127.0.0.1", 55555);
                            ObjectInputStream fis = new ObjectInputStream(faqSocket.getInputStream());

                            ArrayList<FAQ> faqs = (ArrayList<FAQ>)fis.readObject();

                            System.out.println();
                            System.out.println("\t\t\t\t\t\t>>>>>>>>Frequently Asked Questions<<<<<<<<");
                            System.out.println();
                            for (FAQ faq : faqs) {
                                System.out.println("\t\t\tQ: " + faq.getQuestion());
                                System.out.println("\t\t\tA: " + faq.getAnswer());
                                System.out.println();
                            }

                            faqSocket.close();
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    break;
                   
                case 5:
                    System.out.println();
                    System.out.println("\t\t\t\t\t\tThank you for using the ATM. Have a nice day!");
                    System.exit(0);
                   
                default:
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t  Invalid choice. Please try again.");
                }
            }
        }
        else {
            System.out.println();
            System.out.println("\t\t\t\t\t\tLogin failed. Invalid card_number or password.");
        }
    }
}
class FAQ implements Serializable 
{
    private String question;
    private String answer;

    public FAQ(String question, String answer) 
    {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() 
    {
        return question;
    }

    public String getAnswer() 
    {
        return answer;
    }
}


