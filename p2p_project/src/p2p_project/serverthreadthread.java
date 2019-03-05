package p2p_project;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class serverthreadthread extends Thread {
	private  servrethread servret ;
	private Socket socket;
	private PrintWriter printwriter;
	public serverthreadthread(Socket socket,servrethread servrethread)
	{
		this.servret = servrethread;
		this.socket = socket;
		
	}
	public void run() 
	{
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printwriter = new PrintWriter(socket.getOutputStream(),true);
			while(true) servret.sendmessage(bufferedReader.readLine());
		} catch (IOException e) {
			servret.getseverthreadthread().remove(this);
		}
		
	}
	public PrintWriter getprintwrikter () {return printwriter;}
	
}
