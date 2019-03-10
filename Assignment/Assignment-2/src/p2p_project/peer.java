package p2p_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;

import javax.json.Json;

public class peer {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Enter 'Device-Name' and 'Port#' for this peer:");
		String[] values = br.readLine().split(" "); 
		servrethread sTh = new servrethread(values[1]);
		sTh.start();
		
        new peer().update_Listener(br, values[0], sTh);}
	
	public void update_Listener(BufferedReader br, String deviceName, servrethread sTh) throws IOException
	{
		System.out.println("Enter (Slash separated'/') Host-Name:Port# to recieve data from ('s' to stop):");
		String input =br.readLine();
		String[] value = input.split("/");
		if(!input.equals("s"))
			for(int i = 0; i < value.length; i++) {
				String[] address = value[i].split(":");
				Socket s = null;
				try {
					s = new Socket(address[0],Integer.valueOf(address[1]));
					new peerthread(s).start();
				}catch (Exception e) {
					if(s != null) 
						s.close();}}
		store(deviceName, sTh);}
	
	public void store(String deviceName , servrethread sTh) throws IOException
	{
		try {
		    System.out.println("Storing data Now");
		    while(true){
		    	FakeGenerator obj = new FakeGenerator ();
		    	String data = Integer.toString(obj.generate_data(),10);
		    	StringWriter sWr = new StringWriter();
		    	Json.createWriter(sWr).writeObject(Json.createObjectBuilder().add("deviceName", deviceName).add("data", data).build());
		    	String temp = "["+deviceName+']'+data;
				System.out.println(temp);
		    	sTh.sendData(sWr.toString());
		    	// generate random sleeping intervals form 0 to 10 seconds
	            Thread.sleep((long)(Math.random() *10000));}
		} catch (Exception e) {
			System.out.println("Error happened");}
	}
}
