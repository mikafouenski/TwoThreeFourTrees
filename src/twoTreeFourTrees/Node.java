package twoTreeFourTrees;

import java.util.ArrayList;
import java.util.Collections;

public class Node {
	private Node father = null;
	private ArrayList<Integer> values = new ArrayList<>();
	private ArrayList<Node> sons = new ArrayList<>();

	public void add(Integer data) {
		if (!this.is4Node()) {
			this.values.add(data);
			Collections.sort(this.values);
		}
	}

	public void remove(Integer data) {
		for (int i = 0; i < this.size(); i++) {
			if (this.values.get(i) == data) {
				this.values.remove(i);
				break;
			}
		}
	}
	
	public boolean is2Node() {
		return (this.size() == 1);
	}

	public boolean is3Node() {
		return (this.size() == 2);
	}

	public boolean is4Node() {
		return (this.size() == 3);
	}

	public boolean isLeaf() {
		return this.sons.size() == 0;
	}

	public boolean isRoot() {
		return this.father == null;
	}

	public boolean isInternal() {
		return (!this.isRoot() && !this.isLeaf());
	}

	public int size() {
		return this.values.size();
	}

	public void addSon(Node son) {
		this.sons.add(son);
	}

	public Node getSon(int i) {
		return this.sons.get(i);
	}

	public ArrayList<Node> getSons() {
		return this.sons;
	}

	public Integer getValue(int i) {
		return this.values.get(i);
	}

	public Node getFather() {
		return this.father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public void print() {
		System.out.print("[");
		for (int i = 0; i < this.size(); i++) {
			System.out.print(this.getValue(i));
			if (i + 1 < this.size())
				System.out.print(",");
		}
		System.out.print("]");
	}
}
