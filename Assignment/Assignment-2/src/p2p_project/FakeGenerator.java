package p2p_project;

import java.util.Random;

public class FakeGenerator{
	private Random rand_value;
	public FakeGenerator () {
		rand_value = new Random();
	}
    public int generate_data() {
    	// Generate random integers in range 0 to 99999
    	return rand_value.nextInt(100000);
    }
}
