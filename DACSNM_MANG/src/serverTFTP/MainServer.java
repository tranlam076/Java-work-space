
package serverTFTP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import UI.UIServer;

public class MainServer {
	private static final int PACKET_SIZE = 65468;
    public static void main(String args[]) throws SocketException, IOException{  
         System.out.println("started server");
         int socketNo = 69;
         UIServer uiServer = new UIServer();
         while(true){
             DatagramSocket socket = new DatagramSocket(69);
             byte[] recBuf = new byte[PACKET_SIZE];
             DatagramPacket packet = new DatagramPacket(recBuf, recBuf.length);
             socket.receive(packet);
             System.out.println("Received first Req Packet");
             socketNo += 1000;
             ServerThread server = new ServerThread(socketNo, packet, uiServer);
             System.out.println("new Server Thread");
             server.start();
             socket.close();
         }
     }  
}
