package twoTreeFourTrees;

public class Main {

	public static void main(String[] args) {
		TwoTreeFourTrees twoTreeFourTrees = new TwoTreeFourTrees();
		Integer[] array = {60, 40, 20, 10, 27, 30, 33, 36, 61, 41, 21, 11, 28, 31, 34, 37};
		//twoTreeFourTrees.add(60);
		twoTreeFourTrees.add(array);
		twoTreeFourTrees.remove(7);
		//twoTreeFourTrees.remove(34);
		twoTreeFourTrees.display();
		
		NodeSearch search = twoTreeFourTrees.search(31);
		System.out.println("31 dans" + search.getNode() + " pos:" + search.getPos());
	}

}
