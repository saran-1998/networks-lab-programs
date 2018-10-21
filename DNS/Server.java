import java.io.*;
import java.net.*;
import java.util.*;
class Server
{
    public static void main(String args[])
    {
        try
        {
            DatagramSocket server=new DatagramSocket(1309);
            while(true)
            {
                byte[] sendbyte=new byte[1024];
                byte[] receivebyte=new byte[1024];
                DatagramPacket receiver=new DatagramPacket(receivebyte,receivebyte.length);
                server.receive(receiver);
                int n=Integer.parseInt(new String(receiver.getData()).trim());
                System.out.println(n);                
                server.receive(receiver);
                String str=new String(receiver.getData());
                String s=str.trim();
                System.out.println(s);
                InetAddress addr=receiver.getAddress();
                int port=receiver.getPort();
                if(n == 1)
                {
                    InetAddress address;
                    address = InetAddress.getByName(s);
                    sendbyte=address.getHostAddress().getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,port);
                    server.send(sender);
                }
                if(n == 2)
                {
                    InetAddress ia = InetAddress.getByName(s);    
                    sendbyte=ia.getHostName().getBytes();
                    DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,port);
                    server.send(sender);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}