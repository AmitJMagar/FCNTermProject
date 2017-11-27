/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */


import java.net.*;
import java.io.*;


/*
 * This class Represents Client of Reliable Transfer Protocal 3.0 over 
 * unreliable transfer network
 */
public class Client {
	public static void main(String args[]){
		final int PORT =5555;
		int counter=0;
		try {
	        while(true){
	        Socket clientSocket = new Socket(  "127.0.0.1", PORT);
	        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
	     
	        Packet pkt=new Packet("This is Message From Client"+counter++);
	        
	        if(counter%3==0)
	        	pkt.add_Error();
	        
	        outToServer.writeObject(pkt);
	        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
	        ack_packet ack=(ack_packet)in.readObject();
	        
	        if(ack.getSEQ_NO()==pkt.getSEQ_NO())
	        {
	        	System.out.println("Positive Acknowledgement by Server");
	        }
	        else
	        {	
	        		while(ack.getSEQ_NO()!=pkt.getSEQ_NO()){
	        			System.out.println("Sending Packet Again");
	        			pkt=new Packet("This is Message From Client"+(counter-1));
	        			outToServer.writeObject(pkt);
	        			ack=(ack_packet)in.readObject();
	        		}
	        		System.out.println("Positive Acknowledgement by Server");
	        }
	        clientSocket.close();
	        if(counter==4)
	        	break;
	        }
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
}
