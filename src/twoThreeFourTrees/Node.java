package twoThreeFourTrees;

public class Node {
	private Integer[] values = new Integer[3];
	private Node[] sons = new Node[4];
	private int nbKey = 0;

	public String toString() {
		String node = "[";
		for (int i = 0; i < this.nbKey; i++) {
			node += this.values[i];
			if (i + 1 < this.nbKey)
				node += ",";
		}
		node += "]";
		return node;
	}
	
	public String exportDot() {
		String node = this.hashCode() + " [label=\"{";
		for (int i = 0; i < this.nbKey; i++) {
			node += this.values[i];
			if (i + 1 < this.nbKey)
				node += "|";
		}
		node += "}\"]";
		return node;
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
