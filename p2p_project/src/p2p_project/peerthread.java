package p2p_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonObject;

public class peerthread extends Thread {
   private	BufferedReader bufferedreader ;
	public peerthread(Socket socket) throws IOException
	{
	bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()) );	
	}
    
	public void run() 
	{
		boolean flag =true;
		while(flag) 
		{
		
			try {
				JsonObject jsonobject = (JsonObject) Json.createReader(bufferedreader).readObject();
				if(jsonobject.containsKey("username"))
					System.out.println("["+jsonobject.getString("username")+']'+jsonobject.getString("message"));
			} catch (Exception e) {
				flag=false;
				interrupt();
			}
			
		}
		
	}
}
