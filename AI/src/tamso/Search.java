package tamso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Search {

	public static void main(String[] args) {
		new Search();
	}
	
	Random rand = new Random();
	
	public Search() {
		ArrayList<State> open = new ArrayList<>();
		Map<String, State> closed = new HashMap<>();
		State o = null;
		State goal = new State();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				goal.a[y][x] = (x + y * 3 + 1) % 9;
		State start = goal.clone();
		for (int i = 0; i < 1000; i++) {
			State tmp = (new Operator(rand.nextInt(4))).move(start);
			if (tmp != null) start = tmp;
		}
		
		boolean ok;
		
		goal.print();
		start.print();
		
		//1
		open.add(start);
		closed.put(start.key(), start);
		
		//2-6
		while(open.size() != 0) {
			//3
			o = open.remove(0);
			//4
			if (equal(o, goal)) {
				ok = true;
				break;
			}
			//5
			for (int i = 0; i < 4; i++) {
				Operator op = new Operator(i);
				State child = op.move(o);
				if (child == null) continue;
				if (in(child, closed)) continue;
				open.add(child);
				closed.put(child.key(), child);
				child.father = o;
				child.mom = op;
			}
		}
		
		print(o);
	}

	private void print(State o) {
		if (o.father != null) {
			print(o.father);
			System.out.println(o.mom.i);
		}
		o.print();
	}

	private boolean in(State child, Map<String, State> list) {
		State s = list.get(child.key());
		if (s == null) return false;
		return true;
	}

	private boolean equal(State o, State goal) {
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++)
				if (o.a[y][x] != goal.a[y][x]) return false;
		return true;
	}

}
