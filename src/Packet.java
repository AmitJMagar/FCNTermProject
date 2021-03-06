
/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */


public class Packet extends Abstract_Packet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5908158653886180155L;
	private int seq_no;
	/**
	 * Constructor for Packet Object for setting initialize instance
	 * variable/
	 */
	Packet(Packet p)
	{
		this.seq_no=p.seq_no;
		this.CONTENT=p.CONTENT;
		this.setCHECK_SUM(this.CONTENT);
	}
	
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
		seq_no=SEQ_NO==0?1:0;
		SEQ_NO=seq_no;
	} 
	
	/**
	 * Getter method for SEQ_NO 
	 */
	public int getSEQ_NO() {
		return seq_no;
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
		this.CHECK_SUM= sum;		
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
	
	protected void rem_Error()
	{
		CHECK_SUM=CHECK_SUM-1;
	}
	
	@Override
	public String toString()
	{
		return "Sequence Number of Packet :"+this.seq_no+"\n"+
				"Contents of Packet are :"+this.getCONTENT()+"\n"
				+"Check Sum is :"+this.getCHECK_SUM()+"\n";
	}
	
}
