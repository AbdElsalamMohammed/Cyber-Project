import java.io.*;
public class data_store {
    private String data;
    private String [] data_details; //separted data
    public  data_store(String d) throws IOException { //send string with Username:message format
        this.data = d;
        seprate_data();
        print_data();
    }
    private void seprate_data() //sepreate process
    {
         data_details =data.split(":");
    }
    private void print_data() throws IOException {  //write on data file
        FileWriter w = new FileWriter("datafile.txt",true);
        w.write("["+data_details[0]+"] : "+data_details[1]+"\n");
        w.close();

    }


}
