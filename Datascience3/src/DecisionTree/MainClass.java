package DecisionTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainClass {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		List<Map<String,Integer>> traindata=new ArrayList<Map<String,Integer>>();
		String inputFilePath="E:\\5th Semister\\Database\\decision-tree-java-implementation-master\\src\\data_sets1\\training_set.csv";
		ParseTrainData parseTrainData=new ParseTrainData(traindata,inputFilePath);
		DecisionTree decisionTree=new DecisionTree(traindata);
		decisionTree.buildTree();
	}

}
