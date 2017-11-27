
public class ack_packet extends Abstract_Packet{
	
	private int seq_no;
	ack_packet(int seq_no){
		this.seq_no=seq_no;
	}

	@Override
	public int getSEQ_NO() {
		// TODO Auto-generated method stub
		return seq_no;
	}

	@Override
	public void setSEQ_NO() {
		this.seq_no=seq_no;
	}

	@Override
	public int getCHECK_SUM() {
		// TODO Auto-generated method stub
		return CHECK_SUM;
	}

	@Override
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
	
	public void add_error()
	{
		CHECK_SUM+=1;
	}
	
}
