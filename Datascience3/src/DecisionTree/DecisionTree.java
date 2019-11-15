package DecisionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class DecisionTree {

	public static final int Class1=0;
	public static final int Class2=1;
	InformationGainAndEntrophy informationGainAndEntrophy;
	List<Map<String,Integer>> traindata=new ArrayList<Map<String,Integer>>();
	ArrayList<String>[] tree=new ArrayList[100];
	public DecisionTree(List<Map<String,Integer>> traindata) {
		
		this.traindata=traindata;
		informationGainAndEntrophy=new InformationGainAndEntrophy();
		for(int i=0;i<14;i++) {
			
			tree[i]=new ArrayList<String>();
		}
		
	}
	public void buildTree() {
		
		List<Map<String,Integer>> traindata2=traindata;
		Node headNodeInfoGain = growTree(traindata2);
		createTree(headNodeInfoGain,0);
		printTree();
		
	}
	
	public Node growTree(List<Map<String,Integer>> traindata2) {
		
		Node current;
		int size = traindata2.size();
		
		int zeroClassCount = 0;
		int oneClassCount = 0;
		for(Map<String,Integer> dataPoint: traindata2) {
			if(dataPoint.get("Class") == Class1) {
				zeroClassCount++;
			}
			else if(dataPoint.get("Class") == Class2) {
				oneClassCount++;
			}
			
		}
		
		if(zeroClassCount == size) {
			current = new Node(true,Class1);
		}
		
		else if(oneClassCount == size) {
			current = new Node(true,Class2);
		}
		
		else {
			
			
			String bestAttribute =informationGainAndEntrophy.bestInfoGainAttribute(traindata2);
			List<Map<String, Integer>> left = new ArrayList<>(), right = new ArrayList<>();
			
			for(Map<String, Integer> dataPoint: traindata2) {
				if(dataPoint.get(bestAttribute) == Class1) {
					left.add(dataPoint);
				}
				else if(dataPoint.get(bestAttribute) ==Class2) {
					right.add(dataPoint);
				}
				
			}
			if(left.size() == traindata2.size()) {
				System.out.println("dds");
			}
			if(right.size() == traindata2.size()) {
				System.out.println("ddss");
			}
			Node leftNode = growTree(left);
			Node rightNode = growTree(right);
			
			current = new Node(bestAttribute,false);
			current.setLeftChild(leftNode,0);
			current.setRightChild(rightNode,1);
			
			
		}
		
		return current;
	}
	
	public void createTree(Node node, int level) {
		
		
		if(node.isLeaf) {
			
			
			
			tree[level].add("[Cl="+node.category+"]");
		}
		else{
			
			/*for(int i=0;i<14-level;i++) {
				
				
				tree[level].add(" ");
			}*/
			tree[level].add("["+node.attributeName + "=0]");
			createTree(node.leftNode, level + 1);
			
			/*for(int i=0;i<14+level;i++) {
				
				
				tree[level].add(" ");
			}*/
			
			tree[level].add("["+node.attributeName + "=1]");
			createTree(node.rightNode, level + 1);
		}
		
	}
	
	public void printTree() {
		
		int label = 0;
		for(ArrayList<String> list:tree) {
			
			if(list!=null) {
				System.out.print("Label:"+label+"-->");
				for(int i=0;i<14-label;i++) {
					
					System.out.print("\t\t");
				}
				for(String str:list) {
					
					System.out.print(str+"   ");
				}
				label++;
				System.out.println();
			}
			
		}
		
	}
	
	

}
