package com.song.javatests.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.song.javatests.demo1.socket.server.Server;

public class App {
	private static ExecutorService es = Executors.newFixedThreadPool(20);
	public static void main(String[] args)  {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(8088);
			while (true) {
				socket = server.accept();
				es.execute(new Server(socket));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		

	}
	
}
