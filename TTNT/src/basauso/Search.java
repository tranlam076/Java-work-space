package basauso;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class Search {

	public static void main(String[] args) {
		new Search();
	}

	Random rand = new Random();

	public Search() {
		PriorityQueue<State> ListStateOpen = new PriorityQueue<State>(1000000, new Comparator<State>() {
			@Override
			public int compare(State state1, State state2) {
				return (state1.g + state1.h) - (state2.g + state2.h);
			}
		});
		Map<String, State> ListStateClose = new HashMap<>();
		State currentState = null;
		State goalState = new State();
		for (int y = 0; y < 6; y++)
			for (int x = 0; x < 6; x++)
				goalState.a[y][x] = (x + y * 6 + 1) % 36;
		State startState = goalState.clone();
		for (int i = 0; i < 1000; i++) {
			State tmp = (new Operator(rand.nextInt(4))).move(startState);
			if (tmp != null)
				startState = tmp;
		}

		System.out.println("goal");
		goalState.print();
		System.out.println("start");
		startState.print();

		int count = 0;
		int mem = 0;

		// 1.
		startState.g = 0;
		startState.h = Distance(startState, goalState);

		ListStateOpen.add(startState);
		ListStateClose.put(startState.key(), startState);
		// 2 - 6
		while (ListStateOpen.size() != 0) {
			count++;
			// 3.
			currentState = ListStateOpen.remove();
			// 4.
			if (equal(currentState, goalState)) {
				break;
			}
			// 6.
			for (int i = 0; i < 4; i++) {
				Operator operator = new Operator(i);
				State childState = operator.move(currentState);
				if (childState == null)
					continue;
				if (isInListClosedState(childState, ListStateClose))
					continue;
//				childState.g = currentState.g + 1;
				childState.h = Distance(childState, goalState);
				ListStateOpen.add(childState);
				if (mem < ListStateOpen.size())
					mem = ListStateOpen.size();
				ListStateClose.put(childState.key(), childState);
				childState.fatherState = currentState;
				childState.operator = operator;
			}
		}
		print(currentState);
		System.out.println(count);
		System.out.println(mem);
	}

	private void print(State currentState) {
		if (currentState.fatherState != null) {
			print(currentState.fatherState);
			System.out.println(currentState.operator.i);
		}
		currentState.print();
	}

	private boolean isInListClosedState(State childState, Map<String, State> listState) {
		State state = listState.get(childState.key());
		if (state == null)
			return false;
		return true;
	}

	private boolean equal(State currentState, State goalState) {
		for (int y = 0; y < 6; y++)
			for (int x = 0; x < 6; x++)
				if (currentState.a[y][x] != goalState.a[y][x])
					return false;
		return true;
	}

	private int Distance(State currentState, State goalstate) {
		int distance = 0;
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 6; i++) {
				int x = (currentState.a[j][i] - 1) % 6;
				int y = (currentState.a[j][i] - 1) / 6;
				if (currentState.a[j][i] != 0) {	
					distance += Math.abs(i - x) + Math.abs(j - y);
				}
			}
		}
		return distance*35;
	}
}
