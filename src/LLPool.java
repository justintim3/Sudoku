import java.util.LinkedList;
import java.util.Queue;

public class LLPool {
	private Queue<LinkedList<Integer>> pool;
	public LLPool() {
		pool = new LinkedList<LinkedList<Integer>>();
	}
	
	public void add(LinkedList<Integer> ll) {
		pool.add(ll);
	}
	
	public LinkedList<Integer> poll() {
		if(!pool.isEmpty()) {
			LinkedList<Integer> ll = pool.poll();
			ll.clear();
			return ll;
		}
		return new LinkedList<Integer>();
	}
}
