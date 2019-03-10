package p2p_project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class serverthreadthread extends Thread {
	private  servrethread sTh;
	private Socket s;
	private PrintWriter pWr;
	public serverthreadthread(Socket socket,servrethread servreThread){
		sTh = servreThread;
		s = socket;
	}
	public void run(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
			pWr = new PrintWriter(s.getOutputStream(),true);
			while(true) sTh.sendData(br.readLine());
		} catch (IOException e) {
			sTh.getseverthreadthread().remove(this);
		}
	}
	public PrintWriter getPrintWriter () {return pWr;}
}
