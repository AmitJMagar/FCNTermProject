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
	
	public static void main(String args[]) throws ClassNotFoundException
	{
		final int  PORT=5555;
		try {
			ServerSocket ssocket = new ServerSocket(PORT);
			int counter=0;
			while (true) {    

				Socket csocket = ssocket.accept();
	          
	            ObjectInputStream in = new ObjectInputStream(csocket.getInputStream());
	            
	            Packet pkt=(Packet) in.readObject();
	            System.out.println(pkt.getCONTENT());
	            System.out.println(pkt.getSEQ_NO());

	            
	            csocket.close();

	        }
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
