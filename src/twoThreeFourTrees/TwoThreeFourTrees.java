package twoThreeFourTrees;

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
		if ((i < node.getNbKey()) && (data == node.getValues()[i]))
			return false;

		if (node.getSons()[1] != null) {
			if (node.getSons()[i].getNbKey() == 3) {
				if (node.getSons()[i].getValues()[1] == data)
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
		gauche.getValues()[2] = node.getValues()[0];
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
		for (int j = node.getNbKey() - 1; j >= i; --j) {
			node.getValues()[j + 1] = node.getValues()[j];
		}
		for (int j = node.getNbKey(); j > i; --j) {
			node.getSons()[j + 1] = node.getSons()[j];
		}
		droite.getSons()[1] = droite.getSons()[0];
		droite.getValues()[0] = node.getValues()[i - 1];
		droite.setNbKey(droite.getNbKey() + 1);
		node.getValues()[i - 1] = gauche.getValues()[gauche.getNbKey() - 1];
		droite.getSons()[0] = gauche.getSons()[gauche.getNbKey()];
		gauche.setNbKey(gauche.getNbKey() - 1);
	}

	private void spinLeft(Node node, int i) {
		Node gauche, droite;
		gauche = node.getSons()[i];
		droite = node.getSons()[i + 1];
		gauche.setNbKey(gauche.getNbKey() + 1);
		gauche.getValues()[gauche.getNbKey() - 1] = node.getValues()[i];
		gauche.getSons()[gauche.getNbKey()] = droite.getSons()[0];
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

	/*
	 * private Integer getMinValue(Node node) { while (node.getSons()[0] !=
	 * null) { node = node.getSons()[0]; } return node.getValues()[0]; }
	 */

	private boolean delete(Node node, Integer data) { // suppresion a la
														// remontée
		int i = 0;
		while (i < node.getNbKey() && data > node.getValues()[i]) {
			i = i + 1;
		}
		if (node.getSons()[0] == null) {
			if (i < node.getNbKey() && data == node.getValues()[i]) {
				for (int j = i; j < node.getNbKey() - 1; ++j) {
					node.getValues()[j] = node.getValues()[j + 1];
				}
				node.setNbKey(node.getNbKey() - 1);
				return node.getNbKey() == 0;
			} else {
				return false;
			}
		} else {
			if (i < node.getNbKey() && data == node.getValues()[i]) {
				node.getValues()[i] = this.getMaxValue(node.getSons()[i]);
				data = node.getValues()[i];
			}
			if (this.delete(node.getSons()[i], data)) {
				if (i < node.getNbKey() && node.getSons()[i + 1].getNbKey() > 1) {
					this.spinLeft(node, i);
					return false;
				} else {
					if (i > 0 && node.getSons()[i - 1].getNbKey() > 1) {
						this.spinRight(node, i);
						return false;
					} else {
						if (i > node.getNbKey() - 1)
							i--;
						this.merge(node, i);
						return node.getNbKey() == 0;
					}
				}
			} else {
				return false;
			}
		}
	}

	public void remove(Integer data) {
		if (this.delete(this.root, data)) {
			Node tmp = this.root;
			if (tmp.getSons()[0] != null)
				this.root = tmp.getSons()[0];
		}
	}

	private int searchPos(Node node, Integer data) {
		int gauche = 0, droite = node.getNbKey() - 1, millieu = (gauche + droite) / 2;
		while (gauche <= droite) {
			if (data == node.getValues()[millieu]) {
				return millieu;
			}
			if (data < node.getValues()[millieu]) {
				droite = millieu - 1;
			} else {
				gauche = millieu + 1;
			}
			millieu = (gauche + droite) / 2;
		}
		return gauche;
	}

	private NodeSearch find(Node node, Integer data) {
		if (node == null)
			return new NodeSearch();
		else {
			int i = this.searchPos(node, data);
			if (i < node.getNbKey() && node.getValues()[i] == data)
				return new NodeSearch(node, i);
			else
				return this.find(node.getSons()[i], data);
		}
	}

	public NodeSearch search(Integer data) {
		return this.find(this.root, data);
	}

	public void display() {
		StringBuilder stringBuilder = new StringBuilder();
		this.print(this.root, stringBuilder, "");
		System.out.println(stringBuilder);
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
}
