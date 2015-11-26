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
		for (int i = 1; i <= n; i++) {
			Integer number = rand.nextInt(n);
			boolean search = tree.search(number);
			System.out.println(((search) ? "[REMOVE] " : "[ADD] ") + "Number : " + number);
			if (search)
				tree.remove(number);
			else
				tree.add(number);
			tree.display();
			if (i < n - 1)
				System.out.println("--------------- NEXT ---------------");
		}
	}

	private static void test() {
		TwoThreeFourTrees tree = new TwoThreeFourTrees();
		int compteur = 0;
		Random rand = new Random();
		while (true) {
			compteur++;
			Integer number = rand.nextInt(200);
			boolean search = tree.search(number);
			System.out.println(((search) ? "[REMOVE] " : "[ADD] ") + "Number : " + number + " Etape : " + compteur);
			if (search)
				tree.remove(number);
			else
				tree.add(number);
			tree.display();
			System.out.println("--------------- NEXT ---------------");
		}
	}

	public static void main(String[] args) {
		Main.test();
		// Main.run();
	}

}
