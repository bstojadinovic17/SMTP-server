package pck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	private ArrayList<String> inbox;
	public Client() throws Exception {
		
		inbox=new ArrayList<>();
		
		Socket socket=new Socket("localhost", 5517);
		PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		Scanner keyboard=new Scanner(System.in);
		
		String email="";
		System.out.println("Unesite vasu email adresu: ");
		email=keyboard.nextLine();
		out.println(email);
		String command="";
		while(true) {
			
			String poruka=in.readLine();	
			
			
			if(poruka.startsWith("Nova poruka")) {
				String[] msg=poruka.split("\\*");
				StringBuilder sb=new StringBuilder();
				sb.append("Nova poruka od: "+msg[1]);
				sb.append(" , Naslov: "+msg[2]);
				sb.append(" , Poruka: "+msg[3]);
				sb.append("\n");
				inbox.add(sb.toString());
				System.out.println("Imate novu poruku od: "+msg[1]);
				System.out.println("1 - New email");
				System.out.println("2 - Check inbox");
				System.out.println("0 - Disconnect this client");
				
				command=keyboard.nextLine();
				
				if(command.equals("0")) {
					out.println(command);
					System.out.println("Disconnecting..");
					break;
				}else if(command.equals("2")){
					for(String s: inbox) {
						System.out.println(s);
					}
					out.println(command);
				}else {
					out.println(command);
				}
				
			}else {
				System.out.println(poruka);
				System.out.println("1 - New email");
				System.out.println("2 - Check inbox");
				System.out.println("0 - Disconnect this client");
				
				command=keyboard.nextLine();
				
				if(command.equals("0")) {
					out.println(command);
					System.out.println("Disconnecting..");
					break;
				}else if(command.equals("2")){
					for(String s: inbox) {
						System.out.println(s);
					}
					out.println(command);
				}else {
					out.println(command);
				}
				
				
			}
			
			
			
			
			
			
			
		}
		
		socket.close();
		
	}
	
	
	public static void main(String[] args) {
		try {
			new Client();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
