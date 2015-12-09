package twoThreeFourTrees;

/**
 * Classe représentant le nœud d'un arbre, contient une liste de valeur, une
 * liste de fils et un nombre de valeur présente.
 */
public class Node {
	private Integer[] values = new Integer[3];
	private Node[] sons = new Node[4];
	private int nbKey = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
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

	/**
	 * Fonction d'export au format DOTfile
	 * 
	 * @return String
	 */
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
