import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PMBPool {
	private HashMap<Integer, Queue<PotentialMovesBoard>> pool;
	public PMBPool() {
		pool = new HashMap<Integer, Queue<PotentialMovesBoard>>();
	}
	/*
	public add(PotentialMovesBoard pmb) {
		int key = pmb.getN();
		if(pool.get(key) == null) {
			pool.put(key, new LinkedList<PotentialMovesBoard>());
		}
		pool.get(key).add(pmb);
	}
	
	public PotentialMovesBoard createPMB() {
		int key = pmb.getN();
		
	}
	
	public PotentialMovesBoard peek() {
		
	}
	
	public PotentialMovesBoard poll() {
		
	}
	*/
}
