package twoTreeFourTrees;

import java.util.ArrayList;
import java.util.Collections;

public class Node {
	public static final int ROOT = 0b001;
	public static final int LEAF = 0b010;
	public static final int INTERNAL = 0b100;
	
	private int status = Node.LEAF;
	private Node father = null;
	private ArrayList<Node> sons = new ArrayList<Node>();
	private ArrayList<Integer> elements = new ArrayList<Integer>();
	
	public boolean ajouter(Integer data) {
		if (this.elements.size() <= 3) {
			this.elements.add(data);
			Collections.sort(this.elements);
			return true;
		} else return false;
	}
	
	public Integer enlever(Integer data) {
		Integer pos = rechercher(data);
		if (pos != null) {
			Integer tmp = this.elements.get(pos);
			this.elements.remove(pos);
			return tmp;
		} else return null;
	}
	
	public boolean isElementsEmpty() {
		return (this.elements.size() == 0) ? true : false;
	}
	
	public boolean isElementsFull() {
		return (this.elements.size() == 3) ? true : false;
	}
	
	public Integer rechercher(Integer data) {
		for (int i = 0; i < this.elements.size(); i++) {
			if (this.elements.get(i) == data ) return i;
		}
		return null;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public ArrayList<Node> getSons() {
		return sons;
	}

	public void setSons(ArrayList<Node> sons) {
		this.sons = sons;
	}

	public ArrayList<Integer> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Integer> elements) {
		this.elements = elements;
	}
}
