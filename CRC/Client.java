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
            input  = new DataInputStream(System.in); 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(Exception e) 
        { 
            System.out.println(e); 
        } 
  		Scanner sc = new Scanner(System.in);
        String line = ""; 
 		String divi;
 		System.out.println("Enter the dividend");
 		divi = sc.nextLine();
 		String div;
 		System.out.println("Enter the divisor");
 		div = sc.nextLine();
	    String temp = encodedData(divi,div);
	    try
        { 
            System.out.println("Sending data");
            System.out.println("key " + div);
            System.out.println("Encoded data " + temp); 
            out.writeUTF(div);
            out.writeUTF(temp);
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
    public static boolean bitOf(char in)
    {
    	return (in == '1');
	}
    public static char charOf(boolean in)
    {
	    return (in) ? '1' : '0';
	}	
    public String XOR(String a,String b)
    {
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
    public String encodedData(String data,String key)
    {
    	int len = key.length();
    	String append_str = data;
        for(int i =1;i<len;i++)
        {
    		append_str+="0";
        }
        String rem = modDiv(append_str,key);
    	String codeWord = data + rem;
    	return codeWord; 
    }
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 5000); 
    } 
} 
