import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server { 
    
    @SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
    	//Header Bits
    	boolean ACK = false;
		boolean NACK = false;
		boolean EOF = false;
		boolean RDY = false;
		boolean ASK = false;
		boolean FIN = false;    	
    	
    	//Initialize Sockets
        ServerSocket ssock = new ServerSocket(5000);
        Socket socket = ssock.accept();
        
        //The InetAddress specification
        InetAddress IA = InetAddress.getByName("localhost"); 
        
        //Get File Name
        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            buf.write((byte) result);
            result = bis.read();
        }
        String fileName = buf.toString("UTF-8");
        
        //Specify the file
        File file = new File("c:\\"+fileName);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis2 = new BufferedInputStream(fis); 
          
        //Get socket's output stream
        OutputStream os = socket.getOutputStream();
                
        //Read File Contents into contents array 
        byte[] contents;
        long fileLength = file.length(); 
        long current = 0;
         
        long start = System.nanoTime();
        while(current!=fileLength){ 
            int size = 1457;
            if(fileLength - current >= size)
                current += size;    
            else{ 
                size = (int)(fileLength - current); 
                current = fileLength;
            } 
            contents = new byte[size]; 
            bis2.read(contents, 0, size); 
            os.write(contents);
            System.out.print("Sending file ... "+(current*100)/fileLength+"% complete!");
        }   
        
        os.flush(); 
        //File transfer done. Close the socket connection!
        socket.close();
        ssock.close();
        System.out.println("File sent succesfully!");
    }
}