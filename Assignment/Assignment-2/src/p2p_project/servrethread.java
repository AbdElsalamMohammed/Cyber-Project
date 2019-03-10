package p2p_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class servrethread extends Thread{
	private ServerSocket ss;
	private Set<serverthreadthread> sThTh=new HashSet<serverthreadthread>();
	public servrethread(String portNum) throws NumberFormatException, IOException {
		ss = new ServerSocket(Integer.valueOf(portNum));	
	}
	public void run(){
		try {
			while(true){
				serverthreadthread sThTh1 =new serverthreadthread(ss.accept(), this);
				sThTh.add(sThTh1);
				sThTh1.start();
			}
		} catch (Exception e) {
			e.printStackTrace();}
	}
	void sendData(String data){
		try {
			sThTh.forEach(t->t.getPrintWriter().println(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	public Set<serverthreadthread> getseverthreadthread(){return sThTh;}

}
