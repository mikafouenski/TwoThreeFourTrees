package twoThreeFourTrees;

import java.util.Random;

public class Main {
	private static void run() {
		Random rand = new Random();
		int n = 25;
		
		TwoThreeFourTrees tree = new TwoThreeFourTrees();
		for (int i = 0; i < n; i++) {
			Integer number = rand.nextInt(n);
			NodeSearch search = tree.search(number);
			if (search.getNode() != null) {
				tree.remove(number);
				System.out.println("Le nombre " + number + " est dans l'arbre, on l'ENLEVE");
			} else {
				tree.add(number);
				System.out.println("Le nombre " + number + " n'est pas dans l'arbre, on l'AJOUTE");
			}
			tree.display();
			System.out.println("----------- PROCHAIN -----------");
		}
	}
	
	private static void test() {
		TwoThreeFourTrees twoThreeFourTrees = new TwoThreeFourTrees();
		//Integer[] array = {60, 40, 20, 10, 27, 30, 33, 36, 61, 41, 21, 11, 28, 31, 34, 37};
		twoThreeFourTrees.add(12);
		twoThreeFourTrees.remove(12);
		twoThreeFourTrees.add(32);
		twoThreeFourTrees.display();
	}

	public static void main(String[] args) {
		Main.run();
	}

}
