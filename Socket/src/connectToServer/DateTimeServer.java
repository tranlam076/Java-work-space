package connectToServer;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTimeServer {
	public static void main(String[] args) {
		List<MyThread> list = new ArrayList<>();
		try {
			ServerSocket server = new ServerSocket(2000);
			System.out.println("created server");
			Socket soc = server.accept();
//			while () {
				MyThread a = new MyThread(soc);
				a.start();
				list.add(a);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MyThread extends Thread {
	Socket soc;
	public MyThread(Socket soc) {
		this.soc = soc;
	}

	public void run() {
		DataOutputStream dos;
		try {
			dos = new DataOutputStream(soc.getOutputStream());
			dos.writeUTF(new Date().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}