package p2p_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class servrethread extends Thread {
private ServerSocket serversocket;
private Set<serverthreadthread> stt=new HashSet<serverthreadthread>();
public servrethread(String portnum) throws NumberFormatException, IOException {
	serversocket = new ServerSocket(Integer.valueOf(portnum));	
}
public void run() 
   {
	 try {
		while(true)
		{
			serverthreadthread stt1 =new serverthreadthread(serversocket.accept(), this);
			stt.add(stt1);
			stt1.start();
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
   }

void sendmessage(String message)
 {
	try {
		stt.forEach(t->t.getprintwrikter().println(message));
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
public Set<serverthreadthread> getseverthreadthread(){return stt;}

}
