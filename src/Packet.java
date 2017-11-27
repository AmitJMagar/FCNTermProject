
public class Packet extends Abstract_Packet {
	
	Packet(String content){
		
	}
	
	
	public void setSEQ_NO() {
		SEQ_NO=SEQ_NO==0?1:0;
	} 
	
	public int getSEQ_NO() {
		return this.SEQ_NO;
	}
	
	public int getCHECK_SUM() {
		return CHECK_SUM;
	}
	
	public void setCHECK_SUM(String content) {
		int sum=0;
		for(int i=0;i<content.length();i++)
			sum+=content.charAt(i);
		CHECK_SUM= sum;		
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	
	
}
