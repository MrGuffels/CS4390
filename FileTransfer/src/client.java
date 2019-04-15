import java.net.Socket;					//Connects Server and Client
import java.io.InputStream;				//Receives Data from Server
import java.io.FileOutputStream;		//Exports Data to a file
import java.io.BufferedOutputStream;	//Stores Data as it comes in

public class client{
	public static void main(String[] args) {
		boolean ACK = false;
		boolean NACK = false;
		boolean EOF = false;
		boolean RDY = false;
		boolean ASK = false;
		boolean FIN = false;
		
		ACK = true;
		RDY = true;
		System.out.print(helper.buildHeader(ACK,NACK,EOF,RDY,ASK,FIN));
	}
}

