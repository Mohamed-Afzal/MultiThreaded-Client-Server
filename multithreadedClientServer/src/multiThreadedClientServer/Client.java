package multiThreadedClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		try(Socket socket=new Socket("localhost", 8080)) {
//			DataInputStream din=new DataInputStream(socket.getInputStream());
//			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out=new PrintStream(socket.getOutputStream());
			String str,str1;
			do {
				System.out.println("Enter the message : ");
				str=s.nextLine();
				out.println(str);
				String fromserver=(String)in.readLine();
				System.out.println("From Server...: "+fromserver);
				if(str.equals("exit")) {
					str1=(String)in.readLine();
					System.out.println(str1);
				}
			}while(true);
		}catch(Exception e) {
			System.out.println("Client Error... : "+e.getMessage());
		}
		finally {
			s.close();
		}
	}
}
