package twoTreeFourTrees;

public class Node {
	private Node father = null;
	private Integer[] values = new Integer[3];
	private Node[] sons = new Node[4];
	private int nbKey = 0;
	
	public String print() {
		String node = "[";
		for (int i = 0; i < this.values.length; i++) {
			node += this.values[i];
			if (i + 1 < this.values.length)
				node += ",";
		}
		node += "]" + "nbKey = " + this.nbKey;
		return node;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Integer[] getValues() {
		return values;
	}

	public Node[] getSons() {
		return sons;
	}

	public int getNbKey() {
		return nbKey;
	}

	public void setNbKey(int nbKey) {
		this.nbKey = nbKey;
	}
}
