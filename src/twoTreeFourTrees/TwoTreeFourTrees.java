package twoTreeFourTrees;

public class TwoTreeFourTrees {
	private Node root = new Node();
	private int height = 1;
		
	public void add(Integer data) {
		Node current = this.root;
		while (true) {
			//1. If the current node is a 4-node:
			if (current.is4Node()) {
				System.out.println("Current is a 4Node");
				//Remove and save the middle value to get a 3-node.
				Integer middleValue = current.getValue(1);
				current.remove(middleValue);
				//Split the remaining 3-node up into a pair of 2-nodes (the now missing middle value is handled in the next step).
				System.out.println("Split current");
				Node newNode = new Node();
				Integer tmp = current.getValue(1);
				current.remove(tmp);
				newNode.add(tmp);
				//If this is the root node (which thus has no parent):
				if (current.isRoot()) {
					System.out.println("New root");
					//the middle value becomes the new root 2-node and the tree height increases by 1.
					Node newRoot = new Node();
					newRoot.add(middleValue);
					this.root = newRoot;
					this.height++;
					current.setFather(newRoot);
					newNode.setFather(newRoot);
					newRoot.addSon(current);
					newRoot.addSon(newNode);
					// Ascend into the root.
					current = this.root;
				} else {
					//Otherwise, push the middle value up into the parent node
					newNode.setFather(current.getFather());
					current.getFather().add(middleValue);
					if (current.getFather().is3Node()) current.getFather().getSons().add(1, newNode);
					if (current.getFather().is4Node()) current.getFather().getSons().add(2, newNode);
					//Ascend into the parent node.
					current = current.getFather();
				}
			}
			//2. Find the child whose interval contains the value to be inserted.
			//If that child is a leaf, insert the value into the child node and finish
			//Otherwise, descend into the child and repeat from step 1
			if (current.isLeaf()) {
				System.out.println("Adding data !");
				current.add(data);
				break;
			} else {
				current = this.getChildByInterval(current, data);
			}
		}
	}
	
	private Node getChildByInterval(Node node, Integer data) {
		for (int i = 0; i < node.size(); i++) {
			if (data < node.getValue(i)) return node.getSon(i);
		}
		return node.getSon(node.size());
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
		this.display_rec(this.root);
	}
	private void display_rec(Node node) {
		node.print();
		for (int i = 0; i < node.getSons().size(); i++) {
			this.display_rec(node.getSon(i));
		}
	}

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
}
