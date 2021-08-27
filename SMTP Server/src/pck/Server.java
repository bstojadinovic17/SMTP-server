package pck;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
	
	private String from;
	private String to;
	private String subject;
	private String message;
	
	public Server() throws IOException {
		
		ServerSocket ss=new ServerSocket(5517);
		System.out.println("SMTP Server is running.");
		
		Map<String, Socket> users=new ConcurrentHashMap<>();

		while(true) {
			Socket socket=ss.accept();
			ServerThread server_thread=new ServerThread(socket,this,users);
			Thread thread=new Thread(server_thread);
			thread.start();
		}
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
