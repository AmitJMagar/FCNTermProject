/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */


import java.net.*;
import java.util.Random;
import java.io.*;


/*
 * This class to represent Receiver in Reliable Transfer Protocal 
 * over Unreliable Channel
 */


public class Receiver {
	
	private static ServerSocket ssocket;

	/**
	 * This function check if passed packet is corrupted during transmission 
	 * or not.
	 * @param p is Packet object passed to function
	 * @return Return True if packet is corrupted else false
	 */
	
	public static boolean  isCorrupt(Packet p)
	{
		int temp=p.getCHECK_SUM();
		String s=p.getCONTENT();
		int sum=0;
		
		for(int i=0;i<s.length();i++)
				sum+=s.charAt(i);
		
		return temp!=sum;
	}
	
	public static void main(String args[]) throws ClassNotFoundException
	{
		final int  PORT=5555;
		Packet pkt = null;
		ack_packet ack;
		int previous=-1;
		boolean success=true;
		Random rand = new Random();
		
		try {
			ssocket = new ServerSocket(PORT);
			while (true) {
				
				System.out.println("\n------------------------------------------------------------------\n");
				Socket csocket = ssocket.accept();
				
				//Input Stream
	            ObjectInputStream in = new ObjectInputStream
	            		(csocket.getInputStream());
	            
	            // Receive Packet
	            try{
	            pkt=(Packet) in.readObject();
	            }catch(Exception e)
	            {
	            	System.out.println("Packet time out");
	            	csocket.close();
	            	continue;
	            }
	            
	           
	            System.out.println("Received Packet from sender");
	            System.out.print(pkt);
	            
	            success=isCorrupt(pkt);
	            
	           
	            
	            int action=rand.nextInt(4);
	           
	            if(pkt.getSEQ_NO()==previous)
	            {
	            	System.out.println("Expecting Packet with sequence"+
	            			(pkt.getSEQ_NO()==0?1:0)+
	            			"sending acknowledgement of previous packet");
	            	ack=new ack_packet(previous);
	            }
	            else if(success){
	            	ack=new ack_packet(previous);
	            	System.out.println("Received corrupted packet sending "
	            			+ "acknowledgement of previous packet");
	            }
	            else{
	            	
	            	ack=new ack_packet(pkt.getSEQ_NO());
	            	System.out.println("Sending positive acknowledgement");
	            	previous=pkt.getSEQ_NO();
	            }
	            
	            ObjectOutputStream out = new ObjectOutputStream
	            		(csocket.getOutputStream());
	            
	            if(action ==0){
	            	out.writeObject(ack);
	            }	
            	else if(action ==1)
            	{
            		System.out.println("Simulating Acknoledgement Drop");
            		success=false;
            		continue;
            	}
            	else if(action ==2)
            	{
            		System.out.println("Simulating Corrupted Acknoledgement");
            		ack.add_error();
            		out.writeObject(ack);
            	}else
            	{
            		System.out.println("Simulating Acknoledgement Timeout");
            		try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						System.out.print("Thread Interrupted");
					}
            		out.writeObject(ack);
            	}
	            csocket.close();
			}

			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
