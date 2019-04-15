public class helper{
	
	public static String buildHeader(boolean ACK, boolean NACK, boolean EOF, boolean RDY, boolean ASK, boolean FIN) {
		String header;
		header = boolToStr(ACK);
		header += boolToStr(NACK);
		header += boolToStr(EOF);
		header += boolToStr(RDY);
		header += boolToStr(ASK);
		header += boolToStr(FIN);
		header += "00";		
		return header;
	}
	
	 public static String boolToStr(boolean bool){
		String str = "0";
		if (bool) {str = Integer.toString(1);}
		return str;
	}
}