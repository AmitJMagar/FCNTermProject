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
		try {
	        while(true){
	        Socket clientSocket = new Socket(  "127.0.0.1", PORT);
	        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
	     
	        Packet pkt=new Packet("This is Message From Client");
	        outToServer.writeObject(pkt);
	        clientSocket.close();
	        }
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
}
