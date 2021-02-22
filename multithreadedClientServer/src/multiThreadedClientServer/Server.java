package multiThreadedClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args)  {
		int i=0;
		try(ServerSocket ss=new ServerSocket(8080)) {
			System.out.println("Server running....");
			while(true) {
				i++;
				new ClientHandler(ss.accept()).start();
				System.out.println("Client... "+i+" connected ");
			}
		}catch(Exception e) {
			System.out.println("Server Exception : "+e.getMessage());
		}
	}
}
class ClientHandler extends Thread{
	private Socket socket;
	public ClientHandler(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		int i=0;
		Scanner s=new Scanner(System.in);
		try {
//			DataInputStream din=new DataInputStream(socket.getInputStream());
//			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out=new PrintStream(socket.getOutputStream(),true);
			while(true) {
				i++;
				String str=(String)in.readLine();
				System.out.println("Received from client... "+i+" : "+str);
				System.out.println("Enter Message to reply : ");
				String str1=s.nextLine();
				out.println(str1);
				if(str.equals("exit")) {
					break;
				}
			}
		}catch(Exception e) {
			System.out.println("Error message...:"+e.getMessage());
		}
		finally {
			try {
				socket.close();
				s.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
