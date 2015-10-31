package twoTreeFourTrees;

public class Node {
	private int status = R.LEAF;
	private Node[] sons = new Node[4];
	private int[] elements = new int[3];
	
	public NodeshearchResult shearch(int data) {  // recursif
		for (int i = 0; i < this.elements.length; i++) {
			if (this.elements[i] == data) {
				return new NodeshearchResult(this, i);
			}
			if (this.status != R.LEAF && this.elements[i] > data) {
				return this.sons[i].shearch(data);
			}
		}
		return null;
	}
	public void addSon(Node son) {
		
	}
	public void removeSon(Node son) {
		
	}
	public void addElement(int data) {
		
	}
	public void removeElement(int data) {
		
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
