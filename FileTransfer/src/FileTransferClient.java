import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class FileTransferClient { 
    
    public static void main(String[] args) throws Exception{
        
    	//File Name Transfer
    	//String fileName = "turtwig.jpg";
        Scanner sc = new Scanner(System.in); 
        // String input
        System.out.println("Enter the file name: ");
        String fileName = sc.nextLine(); 
    	
    	ServerSocket ssock = new ServerSocket(6000);
        Socket socketF = ssock.accept();
        
        InetAddress IA = InetAddress.getByName("localhost"); 
        
        OutputStream outputStream = socketF.getOutputStream();
        // create a data output stream from the output stream so we can send data through it
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(fileName);
    	
        //Initialize socket
        Socket socket = new Socket(InetAddress.getByName("localhost"), 5000);
        byte[] contents = new byte[10000];
        
        //Initialize the FileOutputStream to the output file's full path.
        FileOutputStream fos = new FileOutputStream("d:\\Users\\Ben Guffey\\Desktop\\" + "2" + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        InputStream is = socket.getInputStream();
        
        //No of bytes read in one read() call
        int bytesRead = 0; 
        
        while((bytesRead=is.read(contents))!=-1)
            bos.write(contents, 0, bytesRead); 
        
        bos.flush(); 
        socket.close(); 
        
        System.out.println("File saved successfully!");
    }
}