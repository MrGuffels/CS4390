import java.util.Scanner;				//Get data from user
import java.net.Socket;					//Connects Server and Client
import java.net.InetAddress;			//Class for IP Address Usage
import java.io.InputStream;				//Receives Data from Server
import java.io.OutputStream;
import java.io.FileOutputStream;		//Exports Data to a file
import java.io.BufferedOutputStream;	//Stores Data as it comes in

public class client{
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		boolean ACK = false;
		boolean NACK = false;
		boolean EOF = false;
		boolean RDY = false;
		boolean ASK = false;
		boolean FIN = false;
		
		//Open A Socket
		Socket socket = new Socket(InetAddress.getByName("localhost"),5000); //Address of 'localhost' on port 5000
		byte[] contents = new byte [1457];
		
		//User Enters File to Download
		String fileName = getString();
		OutputStream os = socket.getOutputStream();
		byte[] b = fileName.getBytes();
		os.write(b);
		
		//File Output to File Path
		FileOutputStream fos = new FileOutputStream("c:\\"+fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		InputStream is = socket.getInputStream();
		
		//Receive File
		int bytesRead = 0;
		
		while((bytesRead=is.read(contents))!=-1)
			bos.write(contents,0,bytesRead);
		
		bos.flush();
		socket.close();
	}
	
	public static String getString() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the file name you want to get from the server: ");
		String input = scanner.nextLine();
		scanner.close();
		return input;
	}
}

