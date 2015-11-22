package twoThreeFourTrees;

public class Main {

	public static void main(String[] args) {
		TwoThreeFourTrees twoThreeFourTrees = new TwoThreeFourTrees();
		Integer[] array = {60, 40, 20, 10, 27, 30, 33, 36, 61, 41, 21, 11, 28, 31, 34, 37};
		twoThreeFourTrees.add(array);
		twoThreeFourTrees.remove(20);
		//twoTreeFourTrees.remove(34);
		twoThreeFourTrees.display();
		
		NodeSearch search = twoThreeFourTrees.search(31);
		System.out.println("31 dans " + search.getNode() + " pos:" + search.getPos());
	}

}
