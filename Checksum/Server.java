import java.net.*; 
import java.io.*; 
public class Server 
{ 
    private Socket socket = null; 
    private ServerSocket server = null; 
    private DataInputStream in =  null; 
    public Server(int port) 
    { 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
            System.out.println("Waiting for a client ..."); 
            socket = server.accept(); 
            System.out.println("Client accepted"); 
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
            String line = ""; 
            try
            { 
                line = in.readUTF(); 
                System.out.println("Data Recieved");
                String[] items = line.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                int[] results = new int[items.length];
                int check = 0;	
                for (int i = 0; i < items.length; i++)
                {
                    results[i] = Integer.parseInt(items[i]);
                    System.out.println(results[i]);
                    check+=results[i];
                } 
                if(~check == 0)
                {
                    System.out.println("Total:" + check);
                    System.out.println("Complement of Total: " + ~check);
                    System.out.println("Successful");
                }
                else
                {
                    System.out.println("Total:" + check);
                    System.out.println("Unsuccessful");
                }
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            }  
            System.out.println("Closing connection"); 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 
