package twoThreeFourTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class TwoThreeFourTrees {
	private Node root = new Node();

	private void split(Node node, int i) {
		Node newNode = new Node();
		newNode.getValues()[0] = node.getSons()[i].getValues()[2];
		newNode.getSons()[0] = node.getSons()[i].getSons()[2];
		newNode.getSons()[1] = node.getSons()[i].getSons()[3];
		node.getSons()[i].getSons()[2] = null;
		node.getSons()[i].getSons()[3] = null;
		newNode.setNbKey(1);
		node.getSons()[i].setNbKey(1);
		for (int j = node.getNbKey() - 1; j >= i; --j) {
			node.getValues()[j + 1] = node.getValues()[j];
		}
		for (int j = node.getNbKey(); j > i; --j) {
			node.getSons()[j + 1] = node.getSons()[j];
		}
		node.getSons()[i + 1] = newNode;
		node.getValues()[i] = node.getSons()[i].getValues()[1];
		node.setNbKey(node.getNbKey() + 1);
	}

	private boolean insert(Node node, Integer data) { // eclatement a la
														// descente
		int i = 0;
		while ((i < node.getNbKey()) && (data > node.getValues()[i])) {
			i = i + 1;
		}
		if ((i < node.getNbKey()) && (node.getValues()[i].equals(data)))
			return false;

		if (node.getSons()[1] != null) {
			if (node.getSons()[i].getNbKey() == 3) {
				if (node.getSons()[i].getValues()[1].equals(data))
					return false;
				this.split(node, i);
				if (data > node.getValues()[i])
					i = i + 1;
			}
			return this.insert(node.getSons()[i], data);
		} else {
			for (int j = node.getNbKey() - 1; j >= i; --j)
				node.getValues()[j + 1] = node.getValues()[j];
			node.getValues()[i] = data;
			node.setNbKey(node.getNbKey() + 1);
			node.getSons()[node.getNbKey()] = null;
			return true;
		}
	}

	public boolean add(Integer data) {
		if (this.root.getNbKey() == 3) {
			Node newRoot = new Node();
			newRoot.getSons()[0] = this.root;
			this.root = newRoot;
			split(this.root, 0);
		}
		return this.insert(this.root, data);
	}

	public void add(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			this.add(array[i]);
		}
	}

	private void merge(Node node, int i) {
		Node gauche = node.getSons()[i];
		Node droite = node.getSons()[i + 1];
		gauche.getValues()[1] = node.getValues()[i];
		gauche.getValues()[2] = droite.getValues()[0];
		gauche.getSons()[2] = droite.getSons()[0];
		gauche.getSons()[3] = droite.getSons()[1];
		gauche.setNbKey(3);
		for (int j = i; j < node.getNbKey() - 1; ++j) {
			node.getValues()[j] = node.getValues()[j + 1];
			node.getSons()[j + 1] = node.getSons()[j + 2];
		}
		node.setNbKey(node.getNbKey() - 1);
	}

	private void spinRight(Node node, int i) {
		Node gauche = node.getSons()[i - 1];
		Node droite = node.getSons()[i];
		droite.getValues()[1] = droite.getValues()[0];
		droite.getSons()[2] = droite.getSons()[1];
		droite.getSons()[1] = droite.getSons()[0];
		droite.getValues()[0] = node.getValues()[i - 1];
		droite.getSons()[0] = gauche.getSons()[gauche.getNbKey()];
		droite.setNbKey(2);
		node.getValues()[i - 1] = gauche.getValues()[gauche.getNbKey() - 1];
		gauche.setNbKey(gauche.getNbKey() - 1);
	}

	private void spinLeft(Node node, int i) {
		Node gauche = node.getSons()[i];
		Node droite = node.getSons()[i + 1];
		gauche.getValues()[1] = node.getValues()[i];
		gauche.getSons()[2] = droite.getSons()[0];
		gauche.setNbKey(2);
		node.getValues()[i] = droite.getValues()[0];
		for (int j = 0; j < droite.getNbKey() - 1; ++j) {
			droite.getValues()[j] = droite.getValues()[j + 1];
		}
		if (droite.getSons()[0] != null) {
			for (int j = 0; j < droite.getNbKey(); ++j) {
				droite.getSons()[j] = droite.getSons()[j + 1];
			}
		}
		droite.setNbKey(droite.getNbKey() - 1);
	}

	private Integer getMaxValue(Node node) {
		while (node.getSons()[0] != null) {
			node = node.getSons()[node.getNbKey()];
		}
		return node.getValues()[node.getNbKey() - 1];
	}

	private Integer getMinValue(Node node) {
		while (node.getSons()[0] != null) {
			node = node.getSons()[0];
		}
		return node.getValues()[0];
	}

	private void delete(Node node, Integer data) {
		int i = 0;
		while (i < node.getNbKey() && data > node.getValues()[i])
			i++;

		if (node.getSons()[0] != null) {

			if (i < node.getNbKey() && node.getValues()[i].equals(data)) {
				if (node.getSons()[i].getNbKey() > 1) {
					node.getValues()[i] = this.getMaxValue(node.getSons()[i]);
					this.delete(node.getSons()[i], node.getValues()[i]);
				} else {
					if (node.getSons()[i + 1].getNbKey() > 1) {
						node.getValues()[i] = this.getMinValue(node.getSons()[i + 1]);
						this.delete(node.getSons()[i + 1], node.getValues()[i]);
					} else {
						this.merge(node, i);
						this.delete(node.getSons()[i], data);
					}
				}
			} else {
				if (node.getSons()[i].getNbKey() == 1) {
					if (i > 0 && node.getSons()[i - 1].getNbKey() > 1) {
						this.spinRight(node, i);
					} else {
						if (i < node.getNbKey() && node.getSons()[i + 1].getNbKey() > 1) {
							this.spinLeft(node, i);
						} else {
							if (i > 0) {
								i--;
							}
							this.merge(node, i);
						}
					}
				}
				this.delete(node.getSons()[i], data);
			}
		} else {
			if (i < node.getNbKey() && node.getValues()[i].equals(data)) {
				for (int j = i; j < node.getNbKey() - 1; ++j) {
					node.getValues()[j] = node.getValues()[j + 1];
				}
				node.setNbKey(node.getNbKey() - 1);
			}
		}
	}

	public void remove(Integer data) {
		this.delete(this.root, data);
		if (this.root.getNbKey() == 0) {
			Node tmp = this.root;
			if (tmp.getSons()[0] != null)
				this.root = tmp.getSons()[0];
		}
	}

	public boolean search(Integer data) {
		Node node = this.root;
		while (true) {
			int i = 0;
			while ((i < node.getNbKey()) && (data > node.getValues()[i]))
				i++;
			if (i < node.getNbKey() && node.getValues()[i].equals(data))
				return true;
			else if (node.getSons()[0] == null)
				return false;
			else if (i < node.getNbKey() && data > node.getValues()[i])
				node = node.getSons()[node.getNbKey()];
			else
				node = node.getSons()[i];
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		this.print(this.root, stringBuilder, "");
		return stringBuilder.toString();
	}

	private void print(Node node, StringBuilder stringBuilder, String indent) {
		stringBuilder.append(node);
		stringBuilder.append("\n");
		if (node.getSons()[0] == null)
			return;
		for (int i = 0; i < node.getNbKey(); ++i) {
			stringBuilder.append(indent);
			stringBuilder.append("├─");
			this.print(node.getSons()[i], stringBuilder, indent + "│ ");
		}
		stringBuilder.append(indent);
		stringBuilder.append("└─");
		this.print(node.getSons()[node.getNbKey()], stringBuilder, indent + "  ");
	}

	private void printDOTLabels(Node node, StringBuilder stringBuilder) {
		stringBuilder.append("    " + node.exportDot());
		stringBuilder.append("\n");
		if (node.getSons()[0] == null)
			return;
		for (int i = 0; i < node.getNbKey(); ++i) {
			this.printDOTLabels(node.getSons()[i], stringBuilder);
		}
		this.printDOTLabels(node.getSons()[node.getNbKey()], stringBuilder);
	}

	private void exportDotLinks(Node node, StringBuilder builder) {
		if (node.getSons()[0] == null) {
			builder.append("    " + node.hashCode());
			builder.append(";\n");
			return;
		}
		for (int i = 0; i < node.getNbKey(); ++i) {
			builder.append("    " + node.hashCode());
			builder.append(" -> ");
			builder.append(node.getSons()[i].hashCode());
			builder.append(";\n");
			if (node.getSons()[i].getSons()[0] != null)
				this.exportDotLinks(node.getSons()[i], builder);
		}
		builder.append("    " + node.hashCode());
		builder.append(" -> ");
		builder.append(node.getSons()[node.getNbKey()].hashCode());
		builder.append(";\n");
		if (node.getSons()[node.getNbKey()].getSons()[0] != null)
			this.exportDotLinks(node.getSons()[node.getNbKey()], builder);
	}

	public void exportToGraphiviz() {
		StringBuilder builder = new StringBuilder();
		builder.append("digraph {\n");
		builder.append("    node [shape = record];\n");
		this.printDOTLabels(this.root, builder);
		this.exportDotLinks(this.root, builder);
		builder.append("}\n");
		// File part
		try {
			File file = new File("graph.dot");
			if (!file.exists())
				file.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(file);
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print(builder.toString());
			printStream.close();
			Process process = Runtime.getRuntime().exec("dot -Tjpg -ograph.jpg graph.dot");
			process.waitFor();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
