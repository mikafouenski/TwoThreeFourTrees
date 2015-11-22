package twoTreeFourTrees;

public class NodeSearch {
	Node node;
	Integer pos;
	
	public NodeSearch(Node node, Integer pos) {
		super();
		this.node = node;
		this.pos = pos;
	}
	public NodeSearch() {
		super();
		this.node = null;
		this.pos = null;
	}

	public Node getNode() {
		return node;
	}

	public Integer getPos() {
		return pos;
	}
}
