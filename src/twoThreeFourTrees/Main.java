package twoThreeFourTrees;

import java.util.Random;
import java.util.Scanner;

public class Main {
	private static void run() {
		Random rand = new Random();
		System.out.print("Please enter the number of random generation : ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		TwoThreeFourTrees tree = new TwoThreeFourTrees();
		for (int i = 1; i <= n; i++) {
			Integer number = rand.nextInt(n);
			boolean search = tree.search(number);
			System.out.println(((search) ? "[REMOVE] " : "[ADD] ") + "Number : " + number);
			if (search)
				tree.remove(number);
			else
				tree.add(number);
			System.out.println(tree);
			if (i < n - 1)
				System.out.println("--------------- NEXT ---------------");
		}
		System.out.print("Did you have graphviz installed ? (y/n) :");
		String res = scanner.next();
		switch (res) {
		case "y":
			tree.exportToGraphiviz();
			System.out.println("Tree exported at tree.jpg");
			break;
		default:
			System.out.println("Arch Linux based install : pacman -S graphviz");
			System.out.println("Debian based install : apt-get install graphviz");
			System.out.println("Os X install: brew install graphviz");
			break;
		}
		scanner.close();
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
			System.out.println(tree);
			System.out.println("--------------- NEXT ---------------");
		}
	}

	private static void test2() {
		Random rand = new Random();
		System.out.print("Please enter the number of random generation : ");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		TwoThreeFourTrees tree = new TwoThreeFourTrees();
		for (int i = 1; i <= n; i++) {
			Integer number = rand.nextInt(n);
			boolean search = tree.search(number);
			if (search)
				tree.remove(number);
			else
				tree.add(number);
		}
		tree.exportToGraphiviz();
	}

	public static void main(String[] args) {
		// Main.test2();
		Main.run();
	}
}
