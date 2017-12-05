/**
 * Implementation of Reliable Transfer Protocol 3.0 in Java.
 * @author Amit J Magar
 * Revision 1.0
 */

/*
 * This class represents Acknowledgement packets
 *
 */
public class ack_packet extends Abstract_Packet{
	
	private static final long serialVersionUID = -3461912705780599419L;
	
	private int seq_no;
	/**
	 * Constructor for Acknowledgement Packet Object for setting initialize instance
	 * variable/
	 */
	ack_packet(int seq_no){
		CONTENT="ACK";
		setCHECK_SUM(this.CONTENT);
		this.seq_no=seq_no;
	}

	@Override
	public int getSEQ_NO() {
		// TODO Auto-generated method stub
		return seq_no;
	}

	@Override
	public void setSEQ_NO() {
		// TODO Auto-generated method stub
	}

	@Override
	public int getCHECK_SUM() {
		// TODO Auto-generated method stub
		return CHECK_SUM;
	}
	

	@Override
	/**
	 * This function calculates check_sum of packet based on contents of packet
	 * (Summation of ascii values of all content)
	 */
	public void setCHECK_SUM(String content) {
		for(int i=0;i<content.length();i++)
			CHECK_SUM+=content.charAt(i);
	}

	@Override
	public String getCONTENT() {
		// TODO Auto-generated method stub
		return CONTENT;
	}

	@Override
	public void setCONTENT(String CONTENT) {
		// TODO Auto-generated method stub
		 this.CONTENT= CONTENT;
	}
	/**
	 * This method add error to check sum of packet
	 */
	public void add_error()
	{
		CHECK_SUM+=1;
	}
	
}
