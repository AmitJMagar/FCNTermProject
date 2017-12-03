/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */


import java.net.*;
import java.util.Random;
import java.io.*;


/*
 * This class Represents Sender of Reliable Transfer Protocal 3.0 over 
 * unreliable transfer network
 */
public class Sender {
	
	public static boolean  isCorrupt(ack_packet p)
	{
		int temp=p.getCHECK_SUM();
		String s=p.getCONTENT();
		int sum=0;
		for(int i=0;i<s.length();i++)
				sum+=s.charAt(i);
		
		return temp!=sum;
		
	}
	public static void main(String args[]){
		final int PORT =5555;
		 Packet pkt=null;
		 //1: Send Packet Safely
		 //2: Send Corrupted Packet
		 //3: Packet Loss
		boolean success=true;
		long current,now;
		int counter=0,action;
		Random rand = new Random();
		try {
	        while(true){
	        	Socket clientSocket = new Socket("127.0.0.1", PORT);
	        	//
	        	
	        	System.out.println("\n------------------------------------------------------------------\n");
	        	ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
	        	
	        	if(success)
	        	{
	        		System.out.println("Sending new packet to receiver");
	        		pkt=new Packet("This is Message From Client"+counter++);
	        	}
	        	else{
	        		System.out.println("Resending earlier packet");
	        		pkt=new Packet(pkt);
	        	}
	        	
	        	action=rand.nextInt(3);
	        	
	        	
	        	if(action%3==0){
	        		outToServer.writeObject(pkt);
	        	}
	        	else if(action%3==1){
	        		System.out.println("Simulating corrupted packet ");
        			pkt.add_Error();
        			outToServer.writeObject(pkt);
        		}
        		else
        		{
        			System.out.println("Simulating packet drop");
        			success=false;
        		}
	        	
	        	clientSocket.setSoTimeout(2500);
	        	try{
	        		ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
	        		current=System.currentTimeMillis();
	        		ack_packet ack=(ack_packet)in.readObject();
	        		now=System.currentTimeMillis();
	        		
	        		if(now-current>1000)
	        		{
	        			System.out.println("Acknowledgement is timed out");
	        			success=false;
	        		}
	        		else if(isCorrupt(ack))
	        		{
	        			System.out.println("Received corrupt acknowledgement");
	        			success=false;
	        		}else if(pkt.getSEQ_NO()!=ack.getSEQ_NO())
	        		{
	        			System.out.println("Received previous acknowledgement");
	        			success=false;
	        		}
	        		else
	        		{
	        			success=true;
	        			System.out.println("Received acknowledgement successfully");
	        		}
	        	}
	        	catch(Exception e)
	        	{
	        		System.out.println("Aknowledgment lost in network packet");
	        		success=false;
	        	}
	        	clientSocket.close();
	        }
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
