package pck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.ObjectInputStream.GetField;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ServerThread implements Runnable {

	private Server server;
	private Socket socket;
	private Map<String, Socket> users;
	private String user;
	private PrintWriter novi;
	private NewMailView view;
	public ServerThread(Socket socket, Server server, Map<String, Socket> map) {
		this.server=server;
		this.socket=socket;
		this.users=map;
		view=new NewMailView();
	}

	@Override
	public void run() {
		
		try {
			PrintWriter out=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			while(true) {
				
				
				String command=in.readLine();
				if(command.equals("1")) {
					view.setVisible(true);
					out.println("Odabrali ste komandu za novi mejl");
					view.getButtonSend().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
						
							for(Map.Entry<String, Socket> entry: users.entrySet()) {
								if(entry.getKey().equals(view.getFieldTo().getText())) {
									try {
										novi=new PrintWriter(new OutputStreamWriter(entry.getValue().getOutputStream()));
										novi.println("Nova poruka od*"+view.getFieldFrom().getText()+"*"+view.getFieldSubject().getText()+"*"+view.getTextAreaMessage().getText());
										novi.flush();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							view.setVisible(false);
							view.getFieldTo().setText("");
							view.getFieldSubject().setText("");
							view.getTextAreaMessage().setText("");
							
						}
					});
					
					
				}
				else if(command.equals("2")) {
					out.println("Odabrali ste komandu za inbox");
				}
				else if(command.equals("0")) {
					for(Map.Entry<String, Socket> entry: users.entrySet()) {
						if(entry.getValue().equals(socket)) {
							System.out.println("Client "+entry.getKey()+" has disconnected.");
							users.remove(entry.getKey(), entry.getValue());
							
						}
					}
					
					socket.close();
					break;
				}else {
					user=command;
					System.out.println("Client "+user+" has connected successfully.");
					users.put(user, socket);
					System.out.println(users.size());
					out.println("Biraj komandu.");
				}
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
