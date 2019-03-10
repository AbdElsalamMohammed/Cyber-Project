package p2p_project;

import java.io.IOException;
import java.util.PriorityQueue;

public class Queuing_module {
    private PriorityQueue<String> queue =  new PriorityQueue<String>();
    private boolean queue_status;
    private int queue_size;   //Default size

    public Queuing_module(int queue_size){
        this.queue_size = queue_size;
        queue_status = true;}

    public Queuing_module(){
    	queue_size=10;}

    public void Remove_the_top(){
        queue.poll();
        set_status(true);}

    public int queue_size(){
        return queue_size;}
    public int queue_input_size(){
        return queue.size();}
    public void Add_To_Queue(String s){
        if(queue.size()==queue_size)
            set_status(false);
        if(queue.size()<=queue_size)
            queue.add(s);}

    public String Return_top(){
        return queue.peek();}

    public void set_status(boolean state){
        this.queue_status = state;}

    public boolean Return_status(){
        return queue_status;}

    public void MoveToDataStore() throws IOException {
    	Data_store data_store = new Data_store(Return_top());
        Remove_the_top();
        data_store.store_data();}
}
