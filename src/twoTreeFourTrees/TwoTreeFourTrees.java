package twoTreeFourTrees;

public class TwoTreeFourTrees {
	private Node root = new Node();
	
	private void split(Node node, int i) {
		Node newNode = new Node();
		newNode.getValues()[0] = node.getSons()[i].getValues()[2];
		newNode.getSons()[0] = node.getSons()[i].getSons()[2];
		newNode.getSons()[1] = node.getSons()[i].getSons()[3];
		 node.getSons()[i].getValues()[2] = null;
		node.getSons()[i].getSons()[2] = null;
		node.getSons()[i].getSons()[3] = null;
		newNode.setNbKey(1);
		node.getSons()[i].setNbKey(1);
		for (int j = node.getNbKey()  - 1; j >= i; --j) {
			node.getValues()[j + 1] = node.getValues()[j];
		}
		for (int j = node.getNbKey(); j > i; --j) {
			node.getSons()[j + 1] = node.getSons()[j];
		}
		node.getSons()[i + 1] = newNode;
		node.getValues()[i] = node.getSons()[i].getValues()[1];
		node.getSons()[i].getValues()[1] = null;
		node.setNbKey(node.getNbKey() + 1);
	}
	
	private boolean insert(Node node, Integer data) { // eclatement a la descente
		int i = 0;
		while ( (i < node.getNbKey()) && (data > node.getValues()[i]) ) {
			i = i + 1;
		}
		if ((i < node.getNbKey()) && (data == node.getValues()[i])) return false;
		
		if (node.getSons()[1] != null) {
			if (node.getSons()[i].getNbKey() == 3) {
				if (node.getSons()[i].getValues()[1] == data) return false;
				this.split(node, i);
				if (data > node.getValues()[i]) i = i + 1;
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

	public void remove(Integer data) {
		/*
		 * Find the element to be deleted.
		 * If the element is not in a leaf node, remember its location and continue searching until a leaf, which will contain the element’s successor, is reached. The successor can be either the largest key that is smaller than the one to be removed, or the smallest key that is larger than the one to be removed. It is simplest to make adjustments to the tree from the top down such that the leaf node found is not a 2-node. That way, after the swap, there will not be an empty leaf node.
		 * If the element is in a 2-node leaf, just make the adjustments below.
		 * Make the following adjustments when a 2-node – except the root node – is encountered on the way to the leaf we want to remove:
		 * 
		 * If a sibling on either side of this node is a 3-node or a 4-node (thus having more than 1 key), perform a rotation with that sibling:
		 * The key from the other sibling closest to this node moves up to the parent key that overlooks the two nodes.
		 * The parent key moves down to this node to form a 3-node.
		 * The child that was originally with the rotated sibling key is now this node's additional child.
		 * If the parent is a 2-node and the sibling is also a 2-node, combine all three elements to form a new 4-node and shorten the tree. (This rule can only trigger if the parent 2-node is the root, since all other 2-nodes along the way will have been modified to not be 2-nodes. This is why "shorten the tree" here preserves balance; this is also an important assumption for the fusion operation.)
		 * If the parent is a 3-node or a 4-node and all adjacent siblings are 2-nodes, do a fusion operation with the parent and an adjacent sibling:
		 * The adjacent sibling and the parent key overlooking the two sibling nodes come together to form a 4-node.
		 * Transfer the sibling's children to this node.
		 * Once the sought value is reached, it can now be placed at the removed entry's location without a problem because we have ensured that the leaf node has more than 1 key.
		 */
	}
	public void display() {
		this.print(this.root, "");
	}
	
	private void print(Node node, String indent) {
        if (node == null) return;
        System.out.println(indent + node.print());
        for (int i = 0; i < node.getSons().length; i++) {
			this.print(node.getSons()[i], indent + "  ");
		}
    }

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
}
