import java.io.Serializable;

public abstract class Abstract_Packet implements Serializable {
	
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
