package p2p_project;

import java.io.IOException;

public class CircuitBreaker {

public CircuitBreaker(String temp,Queuing_module q) throws IOException {
	if(q.Return_status())
	{
		q.Add_To_Queue(temp);
		if(q.queue_size()==q.queue_input_size())
		{
			System.out.println("the Queue is full please wait few secound and send your data");
		}
		q.MoveToDataStore();
		
	}
	else
	{
		System.out.println("Error your Data did`t sent plesase try after few secounds");
	}
		
	
}

}
