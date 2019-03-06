import java.io.*;
public class data_store implements Runnable  {
    private String data;
    private String [] data_details; //separted data

    public  data_store(String d) throws IOException { //send string with Username:message format
        this.data = d;
        print_data(data);

    }

    private void seprate_data() //sepreate process
    {
         data_details =data.split(":");
    }

    public void print_data(String d) throws IOException {  //write on data file
        this.data = d;
        seprate_data();
        run();
    }


    @Override
    public void run() {
        try {
                FileWriter w = new FileWriter("datafile.txt",true);
                w.write("["+data_details[0]+"] : "+data_details[1]+"\n");
                w.close();
                Thread.sleep((long)(3000));


        }
        catch (Exception e){}
    }//end of run().

    }

