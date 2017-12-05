/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */

import java.io.Serializable;

public abstract class Abstract_Packet implements Serializable {
	
	/**
	 * As there are two kinds of packets, Acknowledgement and  is Normal packet.
	 * Created Abstract class Abstract_Packet which will have basic skeleton of Packet
	 */
	private static final long serialVersionUID = 1L; // for Serialization
	protected static int SEQ_NO=0; // Sequence Number of Packet
	protected int CHECK_SUM;		// Check_Sum of Packet
	protected String CONTENT;		// Contents of Packet 
	
	// Getters and Setters Method for Packet Instance Variables
	
	
	public abstract int getSEQ_NO() ;
	public abstract void setSEQ_NO() ;
	public abstract int   getCHECK_SUM() ;
	
	public abstract void setCHECK_SUM(String content) ;
	
	public abstract String getCONTENT() ;
	
	public abstract void setCONTENT(String CONTENT) ;
	
	
}
