import java.io.*;
import java.net.*;
import java.util.*;
class Client
{
    public static void main(String args[])
    {
        try
        {
            DatagramSocket client=new DatagramSocket();
            InetAddress addr=InetAddress.getByName("127.0.0.1");
            byte[] sendbyte=new byte[1024];
            byte[] receivebyte=new byte[1024];
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            while(true)
            {
                System.out.print("Enter your choice : 1. DNS\t 2. Reverse DNS\t 3. Exit\n- -\b\b");
                int n = Integer.parseInt(System.console().readLine());
                if(n==1)
                {
                    sendbyte = Integer.toString(n).getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender);
                    System.out.println("Enter the DOMAIN NAME :");
                    String str=in.readLine();
                    sendbyte=str.getBytes();
                    sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender);
                    DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
                    client.receive(receiver);
                    String s=new String(receiver.getData());
                    System.out.println("IP address : "+s.trim());
                }
                if(n==2)
                {
                    sendbyte = Integer.toString(n).getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender); 
                    System.out.println("Enter the IP adress:");
                    String str=in.readLine();
                    sendbyte=str.getBytes();
                    sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
                    client.send(sender);
                    DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
                    client.receive(receiver);
                    String s=new String(receiver.getData());
                    System.out.println("DOMAIN NAME : "+s.trim());
                }
                if(n == 3)
                    break;
            }
            client.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}





