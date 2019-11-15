package DecisionTree;

import java.util.Map;

public class Node {

	String attributeName;
	int leftAttributeValue,rightAttributeValue;
	Node leftNode;
	Node rightNode;
	boolean isLeaf;
	int category;
	
	public Node(String attributeName,boolean isLeaf) {
		
		this.attributeName=attributeName;
		this.isLeaf=isLeaf;
		
	}
	
	public Node(boolean isLeaf,int category) {
		
		this.isLeaf=isLeaf;
		this.category=category;
	}
	
	public void setLeftChild(Node node,int attributeValue) {
		
		this.leftNode=node;
		this.leftAttributeValue=attributeValue;
	}
    public void setRightChild(Node node,int attributeValue) {
		
		this.rightNode=node;
		this.rightAttributeValue=attributeValue;
	}

}
