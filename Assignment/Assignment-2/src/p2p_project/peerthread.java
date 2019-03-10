package p2p_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonObject;

public class peerthread extends Thread {
   private	BufferedReader br ;
   Queuing_module queue;
	public peerthread(Socket s) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));	
		queue = new Queuing_module();
	}
    
	public void run() 
	{
		boolean flag = true;
		while(flag) 
		{
			try {
				JsonObject jsonobject = (JsonObject) Json.createReader(br).readObject();
				if(jsonobject.containsKey("deviceName")) {
					String temp = "["+jsonobject.getString("deviceName")+']'+jsonobject.getString("data");
					System.out.println(temp);
					queue.Add_To_Queue(temp);
					queue.MoveToDataStore();
				    //CircuitBreaker circuit = new CircuitBreaker(temp, queue);	
				}
			} catch (Exception e) {
				flag=false;
				interrupt();
			}
		}
	}
}
