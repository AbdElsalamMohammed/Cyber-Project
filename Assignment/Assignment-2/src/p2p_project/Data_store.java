package p2p_project;

import java.io.FileWriter;
import java.io.IOException;

public class Data_store {
	private String data;

	public Data_store (String d) throws IOException { 
		//send string with Username:message format
	    data = d;}

	public void store_data() {
		try {
	    	FileWriter w = new FileWriter("D:\\datafile.txt",true);
	        w.write(data+"\n");
	        w.close();
	        Thread.sleep((long)(3000));}
	    catch (Exception e){
	    	System.out.println("Error happened");}}
}
