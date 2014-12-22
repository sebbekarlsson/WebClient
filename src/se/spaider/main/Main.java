package se.spaider.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Main implements Runnable {

	Socket socket;
	public static String ip = "localhost";
	public static int port = 3264;
	public static BufferedReader input;
	public static PrintWriter output;
	public Thread thread = new Thread(this);
	public static String id = "";
	static BrowserWindow window;
	
	public Main(){
		try {
			socket = new Socket(ip,port);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(),true);
			window = new BrowserWindow();
			thread.start();
			
			new LoadingWindow(){
				@Override
				void onDone() {
					output.println("httpquery,"+"home"+","+Main.id);
					
				}	
			};
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {

		while(true){
			String s;
			try {
				while((s = input.readLine()) != null){
					System.out.println("Got message from server"+s);
					
					if(s.startsWith("yourid")){
						id = s.split(",")[1];
						window.label_bottom.setText("ID: "+id);
					}
					else if(s.startsWith("httpresponse")){
						String page = s.split(",")[1];
						window.editor_browser.setText(page);
					}

				}
			} catch (IOException e) {
				thread.interrupt();
				thread.stop();
				e.printStackTrace();
			}
		}
		
	}
	
	public static void sendMessage(String message){
		output.println(message+","+id);
	}
	
	public static void main(String[] args){
		new Main();
	}
}
