import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Subset {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> testQueue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()){
				if(k != 0 && testQueue.size() == k){
					testQueue.dequeue();
				}
				testQueue.enqueue(StdIn.readString());
		}
		for (int i = 0; i < k; i++){
			StdOut.println(testQueue.dequeue());
		}
	}

}
