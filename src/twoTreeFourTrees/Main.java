package twoTreeFourTrees;

public class Main {

	public static void main(String[] args) {
		TwoTreeFourTrees twoTreeFourTrees = new TwoTreeFourTrees();
		twoTreeFourTrees.add(60);
		twoTreeFourTrees.add(40);
		twoTreeFourTrees.add(20);
		twoTreeFourTrees.add(10);
		twoTreeFourTrees.add(27);
		twoTreeFourTrees.add(30);
		twoTreeFourTrees.add(33);
		twoTreeFourTrees.add(36);
		twoTreeFourTrees.add(61);
		twoTreeFourTrees.add(41);
		twoTreeFourTrees.add(21);
		twoTreeFourTrees.add(11);
		twoTreeFourTrees.add(28);
		twoTreeFourTrees.add(31);
		twoTreeFourTrees.add(34);
		twoTreeFourTrees.add(37);
		twoTreeFourTrees.remove(31);
		twoTreeFourTrees.remove(30);
		twoTreeFourTrees.display();
		
		NodeSearch search = twoTreeFourTrees.search(31);
		System.out.println("31 dans" + search.getNode() + " pos:" + search.getPos());
	}

}
