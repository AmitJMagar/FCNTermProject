/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */


import java.net.*;
import java.io.*;


/*
 * Server Class to represent Server in Reliable Transfer Protocal 
 * over Unreliable Channel
 * 
 */


public class Server {
	
	public static boolean  isCorrupt(Packet p)
	{
		int temp=p.getCHECK_SUM();
		p.setCHECK_SUM(p.getCONTENT());
		int temp2=p.getCHECK_SUM();
		if(temp!=temp2){
			p.add_Error();
			return true;
		}
			return false;
	}
	
	public static void main(String args[]) throws ClassNotFoundException
	{
		final int  PORT=5555;
		int previous=-1;
		try {
			ServerSocket ssocket = new ServerSocket(PORT);
			int counter=0;
			while (true) {    
				
				Socket csocket = ssocket.accept();
	            ObjectInputStream in = new ObjectInputStream
	            		(csocket.getInputStream());
	        
	            Packet pkt=(Packet) in.readObject();
	            
	            
	            System.out.println("Received Packet From Client "+pkt.CONTENT);
	            ObjectOutputStream out = new ObjectOutputStream
	            		(csocket.getOutputStream());
	           
	            if(isCorrupt(pkt))
	            {
	            	
	            		System.out.println("Received Corrupted Packet Sending "
	            			+ "Acknowledgement of Previous Packet");
	            		ack_packet ack=new ack_packet(previous);
	            		out.writeObject(ack);
	            		
	            	while(isCorrupt(pkt)){	
	            		
	            		pkt=(Packet)in.readObject();
	            		System.out.print(pkt.getCHECK_SUM());
	            		ack=new ack_packet(previous);
		            	out.writeObject(ack);
	            	}
	            	ack=new ack_packet(pkt.getSEQ_NO());
	            	out.writeObject(ack);
	            	System.out.println("Received Packet From Client "+pkt.CONTENT);
	            }else{
	            	
	            	System.out.println("Received Non Corrupt Packet Sending "
	            			+ "Acknowledgement");
	            	ack_packet ack=new ack_packet(pkt.getSEQ_NO());
	            	out.writeObject(ack);
	            	
	            }
	            	
	            
	            previous=pkt.getSEQ_NO();
	            csocket.close();
	           

	        }
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
