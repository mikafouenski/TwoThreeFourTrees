package twoThreeFourTrees;

import java.util.Random;
import java.util.Scanner;

public class Main {
	private static void run() {
		Random rand = new Random();
		System.out.print("Please enter the number of random generation : ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		TwoThreeFourTrees tree = new TwoThreeFourTrees();
		for (int i = 0; i < n; i++) {
			Integer number = rand.nextInt(n);
			NodeSearch search = tree.search(number);
			if (search.getNode() != null) {
				tree.remove(number);
				System.out.println("The number " + number + " is in the tree, we REMOVE it");
			} else {
				tree.add(number);
				System.out.println("The number  " + number + " is not in the tree, we ADD it");
			}
			tree.display();
			System.out.println("--------------- NEXT ---------------");
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
