package ca.mcgill.ecse223.tileo.util;

import ca.mcgill.ecse223.tileo.model.Tile;

public class Node {
	private Tile tile;
	private Node parent;
	private int depth;
	
	public Node(Tile t, Node p, int d) {
		tile = t;
		parent = p;
		depth = d;
	}
	
	public Tile getTile() {
		return tile;
	}
	public Node getParent() {
		return parent;
	}
	public int getDepth() {
		return depth;
	}
}
