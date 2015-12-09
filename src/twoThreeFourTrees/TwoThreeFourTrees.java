package twoThreeFourTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Classe représentant un arbre 2 3 4, contient la racine.
 */
public class TwoThreeFourTrees {
	private Node root = new Node();

	/**
	 * Fonction divisant un nœeud en 2 et fait remonter la valeur du milleu au
	 * dessus.
	 * 
	 * @param node
	 * @param i
	 */
	private void split(Node node, int i) {
		Node newNode = new Node();
		newNode.getValues()[0] = node.getSons()[i].getValues()[2];
		newNode.getSons()[0] = node.getSons()[i].getSons()[2];
		newNode.getSons()[1] = node.getSons()[i].getSons()[3];
		node.getSons()[i].getSons()[2] = null;
		node.getSons()[i].getSons()[3] = null;
		newNode.setNbKey(1);
		node.getSons()[i].setNbKey(1);
		// On pouse les valeur et les fils pour faire de la place
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

	/**
	 * Fonction recursive d'insertion dans un arbre234, en utilisant la methode
	 * d'éclatement à la descente.
	 * 
	 * @param node
	 * @param data
	 * @return boolean
	 */
	private boolean insert(Node node, Integer data) { // eclatement a la
														// descente
		int i = 0;
		while ((i < node.getNbKey()) && (data > node.getValues()[i]))
			i++;
		if ((i < node.getNbKey()) && (node.getValues()[i].equals(data)))
			return false;

		if (node.getSons()[0] != null) {
			if (node.getSons()[i].getNbKey() == 3) {
				if (node.getSons()[i].getValues()[1].equals(data))
					return false;
				this.split(node, i);
				if (data > node.getValues()[i])
					i++;
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

	/**
	 * Fonction initiale d'ajout d'une valeur, traite la racine a part.
	 * 
	 * @param data
	 * @return boolean
	 */
	public boolean add(Integer data) {
		if (this.root.getNbKey() == 3) {
			Node newRoot = new Node();
			newRoot.getSons()[0] = this.root;
			this.root = newRoot;
			split(this.root, 0);
		}
		return this.insert(this.root, data);
	}

	/**
	 * Fontion d'ajout d'un tableau (utile pour degguger ;).
	 * 
	 * @param array
	 */
	public void add(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			this.add(array[i]);
		}
	}

	/**
	 * Fusionne les nœeud fils[i] et fils[i + 1].
	 * 
	 * @param node
	 * @param i
	 */
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

	/**
	 * Fait pivoter les nœeud fils[i] et fils[i - 1], plus simple que de
	 * remonter les valeurs /!\ faire attention aux nœeud vide ! you fools !
	 * 
	 * @param node
	 * @param i
	 */
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

	/**
	 * Pareil avec fils[i] et fils[i - 1].
	 * 
	 * @param node
	 * @param i
	 */
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

	/**
	 * Recherche de la valeur maximale de l'arbre, peux etre la mettre public, a
	 * voir.
	 * 
	 * @param node
	 * @return Integer
	 */
	private Integer getMaxValue(Node node) {
		while (node.getSons()[0] != null) {
			node = node.getSons()[node.getNbKey()];
		}
		return node.getValues()[node.getNbKey() - 1];
	}

	/**
	 * Recherche du min, Idem
	 * 
	 * @param node
	 * @return Integer
	 */
	private Integer getMinValue(Node node) {
		while (node.getSons()[0] != null) {
			node = node.getSons()[0];
		}
		return node.getValues()[0];
	}

	/**
	 * Fonction recursive de suppresion d'un element dans l'arbre. Elle traite
	 * tous les cas un par un... 5 niveaux de if...
	 * 
	 * @param node
	 * @param data
	 */
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

	/**
	 * Fonction initiale de suppression, traite la racine a part. Pas de
	 * boolean, inutile.
	 * 
	 * @param data
	 */
	public void remove(Integer data) {
		this.delete(this.root, data);
		if (this.root.getNbKey() == 0) {
			Node tmp = this.root;
			if (tmp.getSons()[0] != null)
				this.root = tmp.getSons()[0];
		}
	}

	/**
	 * Fonction de test si un element est dans l'arbre.
	 * 
	 * @param data
	 * @return boolean
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		this.print(this.root, stringBuilder, "");
		return stringBuilder.toString();
	}

	/**
	 * Fonction recursive de construction du string console plus ou moins joli,
	 * voir la commande tree de Linux.
	 * 
	 * @param node
	 * @param stringBuilder
	 * @param indent
	 */
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

	/**
	 * Fonction recursive d'export des labels vers du DotFile
	 * 
	 * @param node
	 * @param stringBuilder
	 */
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

	/**
	 * Fonction recursive d'export des liens vers du DotFile
	 * 
	 * @param node
	 * @param stringBuilder
	 */
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

	/**
	 * Fonction d'export de l'arbre vers le fichier tree.dot et execute dot
	 * dessus.
	 * 
	 * @param node
	 * @param stringBuilder
	 */
	public void exportToGraphiviz() {
		StringBuilder builder = new StringBuilder();
		builder.append("digraph {\n");
		builder.append("    node [shape = record];\n");
		this.printDOTLabels(this.root, builder);
		this.exportDotLinks(this.root, builder);
		builder.append("}\n");
		// File part
		try {
			File file = new File("tree.dot");
			if (!file.exists())
				file.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(file);
			PrintStream printStream = new PrintStream(outputStream);
			printStream.print(builder.toString());
			printStream.close();
			Process process = Runtime.getRuntime().exec("dot -Tjpg -otree.jpg tree.dot");
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
