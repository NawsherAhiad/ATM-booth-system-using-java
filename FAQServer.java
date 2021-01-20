import java.io.*;
import java.net.*;
import java.util.*;

public class FAQServer 
{
    public static void main(String[] args) throws IOException
    {
        ArrayList<FAQ> faqs = new ArrayList<>();
        faqs.add(new FAQ("How do I change my PIN?", "To change your PIN, visit a local branch of our bank and fill out a PIN change request form."));
        faqs.add(new FAQ("What should I do if my card is lost or stolen?", "If your card is lost or stolen, please report it immediately to our customer support at +880-2-9530451."));
        faqs.add(new FAQ("How can I check my account balance?", "You can check your account balance by selecting option 1 on the ATM menu."));
        // Add more FAQs as needed

        try 
        {
            ServerSocket serverSocket = new ServerSocket(55555);
            System.out.println("FAQ Server is running and listening on port 55555...");

            while (true) 
            {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                ObjectOutputStream faqOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                faqOutputStream.writeObject(faqs);
                faqOutputStream.flush();
                clientSocket.close();
            }
        } 
        catch (Exception e) 
        {
            System.out.println("FAQ Server error: " + e.getMessage());
        }
    }
}