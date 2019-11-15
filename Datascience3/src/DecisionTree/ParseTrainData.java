package DecisionTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ParseTrainData {

	List<Map<String,Integer>> traindata=new ArrayList<Map<String,Integer>>();
	String filePtah;
	public ParseTrainData(List<Map<String,Integer>> traindata,String filePtah) throws FileNotFoundException {
		
		this.traindata=traindata;
		this.filePtah=filePtah;
		parseData();
	}
	
	public void parseData() throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new File(filePtah));
		List<String> attributes = Arrays.asList(scanner.nextLine().split(","));
		
		Map<String,Integer> map;
		
		while(scanner.hasNext()) {
			map = new HashMap<>();
			List<String> attributeValues = Arrays.asList(scanner.nextLine().split(","));
			int i = 0;
			String attributeName = null;
			for(String value: attributeValues) {
				attributeName = attributes.get(i);
				map.put(attributeName, Integer.parseInt(value));
				i++;
			}
			i=0;
			traindata.add(map);
		}
	}

}
