package p2p_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;

import javax.json.Json;

public class peer {

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedreader = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Enter username & port for this peer : ");
		String[] setupvalues = bufferedreader.readLine().split(" "); 
		servrethread servert = new servrethread(setupvalues[1]);
		servert.start();
        new peer().updatelistener(bufferedreader, setupvalues[0], servert);
	}
public void updatelistener(BufferedReader bufferedreader,String username , servrethread servret) throws IOException
  {
	System.out.println("enter (space separated) HOSTNAME:PORT#");
	System.out.println("PEERS TO RECIEVE MESSAEG FROM (S TO SKIP) :");
	String input =bufferedreader.readLine();
	String[] inputvalue = input.split(" ");
	if(!input.equals("s"))
		for(int i=0;i<inputvalue.length;i++) 
		{
			String[] address =inputvalue[i].split(":");
			Socket socket = null;
			System.out.println(address[0]+"    "+address[1]);
			try {
				System.out.println(address[0]+"    "+address[1]);
				socket = new Socket(address[0],Integer.valueOf(address[1]));
				new peerthread(socket).start();
			} catch (Exception e) {
				if(socket != null) socket.close();
				else System.out.println("invalid input skipping to nest step ");
			}
		}
	communicate(bufferedreader, username, servret);
	
  }
public void communicate(BufferedReader bufferedreader,String username , servrethread servret) throws IOException
	{
	    try {
	    	System.out.println("> you can now communiacte (e to exit , c to change)");
	    	boolean flag = true;
	    	while(flag)
	    	{
	    		String message = bufferedreader.readLine();
	    		if(message.equals("e"))
	    		{
	    			flag = false;
	    			break;
	    		}
	    		else if (message.equals("c"))
	    		{
	    			updatelistener(bufferedreader, username, servret);
	    		}
	    		else
	    		{
	    			StringWriter stringwriter = new StringWriter();
	    			Json.createWriter(stringwriter).writeObject(Json.createObjectBuilder().add("username", username).add("message", message).build());
	    			servret.sendmessage(stringwriter.toString());
	    		}
	    	}
			
		} catch (Exception e) {
			// TODO: handle exception
		}   
	
	}
}
