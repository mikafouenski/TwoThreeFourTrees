package twoTreeFourTrees;

public class TwoTreeFourTrees<T> {
	private Node root = new Node();
	

	public void add(T data) {
		/*
		 * If the current node is a 4-node:
 		 * Remove and save the middle value to get a 3-node.
 		 * Split the remaining 3-node up into a pair of 2-nodes (the now missing middle value is handled in the next step).
 		 * If this is the root node (which thus has no parent):
 		 * the middle value becomes the new root 2-node and the tree height increases by 1. Ascend into the root.
		 * Otherwise, push the middle value up into the parent node. Ascend into the parent node.
		 * Find the child whose interval contains the value to be inserted.
		 * If that child is a leaf, insert the value into the child node and finish.
		 * Otherwise, descend into the child and repeat from step 1.[3][4]
		 */
	}
	public void remove(T data) {
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
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
}
