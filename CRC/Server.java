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
            String line1 = ""; 
 			String line2 = "";  
            try
            { 
                line1 = in.readUTF(); 
                line2 = in.readUTF();
                System.out.println("Data Recieved");
                System.out.println("Dividend: " + line1);
                System.out.println("Key: " + line2);
                String ans = modDiv(line2,line1);
                int fl = 0;
                for(int i =0;i<ans.length();i++){
                    if(ans.substring(i,i+1).equals("1"))
                    {
                        fl = 1;
                        break;
                    }
                }
                System.out.println("Remainder: " + ans);
                if(fl == 1)
                    System.out.println("Incorrect data recieved");
                else
                    System.out.println("Correct data recieved");
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
    public static boolean bitOf(char in) {
    	return (in == '1');
	}
	public static char charOf(boolean in) {
	    return (in) ? '1' : '0';
	}	
    public String XOR(String a,String b){
    	StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++)
        {
		    sb.append(charOf(bitOf(a.charAt(i)) ^ bitOf(b.charAt(i))));
		}
    	String result = sb.toString();
		return result;
    }
    public String modDiv(String append_str,String key)
    {
    	int len = key.length();
    	int index = 0;
    	int len1 = len;
    	String temp = append_str.substring(0,len);
    	System.out.println(append_str);
    	while(len < append_str.length())
    	{
    		if(temp.substring(0,1).equals("1"))
    		{
    			temp = XOR(key,temp) + append_str.substring(len,len+1);			
    		}
    		else
    		{
    			String temp2 = "";
    			for(int i = 0;i<len1;i++)
    				temp2+="0";
    			temp = XOR(temp2,temp) + append_str.substring(len,len+1);
    		}
    		len+=1;
    		temp = temp.substring(1,temp.length());
    	}	
    	if(temp.substring(0,1).equals("1"))
		{
			temp = XOR(key,temp);			
		}
		else
		{
			String temp2 = "";
            for(int i = 0;i<len1;i++)
            {
                temp2+="0";
            }
			temp = XOR(temp2,temp);
		}
		temp = temp.substring(1,temp.length());
		return temp;
    }
    public static void main(String args[]) 
    { 
        Server server = new Server(5000); 
    } 
} 
