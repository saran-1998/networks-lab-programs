import java.net.*; 
import java.io.*; 
import java.util.*;
public class Client 
{ 
    private Socket socket = null; 
    private DataInputStream input = null; 
    private DataOutputStream out = null; 
    public Client(String address, int port) 
    { 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            input = new DataInputStream(System.in); 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(Exception e)
        {
            System.out.println(e);
        }
  		Scanner sc = new Scanner(System.in);
        int n;
 		System.out.println("Enter the number of integers\n");
 		n = sc.nextInt();
 		int num[] = new int[n+1];
        int sum = 0;
        System.out.println("Enter the integers\n");
 		for(int i =0;i < n;i++)
 		{
 			num[i]  = sc.nextInt();
 			sum+=num[i];
 		}
 		num[n] = ~sum; 
        try
        { 
            System.out.println("Sending data");
            for(int i = 0;i < n;i++)
            {
                System.out.println(num[i]);
            } 
            System.out.println("Checksum" + num[n]);
            out.writeUTF(Arrays.toString(num)); 
            System.out.println("Data sent");
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 5000); 
    } 
} 
