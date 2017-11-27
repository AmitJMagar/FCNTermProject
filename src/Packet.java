/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit j Magar
 * Revision 1.0
 */


public class Packet extends Abstract_Packet {
	
	
	/**
	 * Constructor for Packet Object for setting initialize instance
	 * variable/
	 */
	Packet(String content){
		
		setCONTENT(content);
		setSEQ_NO();
		setCHECK_SUM(content);
		
	}
	
	/**
	 * Setting Sequence Number to Packet, Each packet generated will have
	 * Alternate value of 0 or 1
	 */
	
	public void setSEQ_NO() {
		SEQ_NO=SEQ_NO==0?1:0;
	} 
	
	/**
	 * Getter method for SEQ_NO 
	 */
	public int getSEQ_NO() {
		return this.SEQ_NO;
	}
	
	
	/**
	 * Getter method for CHECK_SUM
	 */
	public int getCHECK_SUM() {
		return CHECK_SUM;
	}
	
	/**
	 * Setter method for CHECK_SUM variable 
	 */
	public void setCHECK_SUM(String content) {
		int sum=0;
		for(int i=0;i<content.length();i++)
			sum+=content.charAt(i);
		CHECK_SUM= sum;		
	}
	
	/**
	 * Getter method for CONTENT
	 */
	
	public String getCONTENT() {
		return CONTENT;
	}
	
	/**
	 * Setter method for CONTENT
	 */
	public void setCONTENT(String content) {
		CONTENT = content;
	}
	
	
	/**
	 * Adding CHECK SUM error to packet
	 */
	
	protected void add_Error()
	{
		CHECK_SUM=CHECK_SUM+1;
	}
	
}
