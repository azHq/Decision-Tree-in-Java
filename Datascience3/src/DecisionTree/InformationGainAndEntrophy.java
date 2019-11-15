package DecisionTree;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InformationGainAndEntrophy {

	public InformationGainAndEntrophy() {
		
		
		
	}
	
	public  String bestInfoGainAttribute(List<Map<String,Integer>> dataSet) {
		
		String bestAttribute = "";
		double initialEntropy = initialEntropy(dataSet);
		
		double maxInformationGain = 0;
		
		Set<String> attributesSet = new HashSet<>();
		for(String attr: dataSet.get(0).keySet()) {
			if(!attr.equals("Class")) {
				attributesSet.add(attr);
			}
		}
		
		for(String attribute: attributesSet) {
			
			double informationGain = informationGainForAttribute(dataSet, initialEntropy, attribute);
				
			if(informationGain>maxInformationGain) {
				maxInformationGain = informationGain;
				bestAttribute = attribute;
			}
		}

		return bestAttribute;
	}
	
	public double informationGainForAttribute(List<Map<String, Integer>> dataSet, double initialEntropy,String attribute) {
		
		int zeroZero = 0;
		int zeroOne = 0;
		int oneZero = 0;
		int oneOne = 0;
		
		for(Map<String, Integer> dataPoint: dataSet) {
			if(dataPoint.get(attribute) == 0) {
				if(dataPoint.get("Class") == 0) {
					zeroZero++;
				}
				else if(dataPoint.get("Class") == 1) {
					zeroOne++;
				}
			}
			else if(dataPoint.get(attribute) == 1) {
				if(dataPoint.get("Class") == 0) {
					oneZero++;
				}
				else if(dataPoint.get("Class") == 1) {
					oneOne++;
				}
			}
		}
		
		double entropyLeft = calculateEntropy(zeroZero, zeroOne);
		double entropyRight = calculateEntropy(oneZero, oneOne);
		double informationGain = informationGain(initialEntropy, zeroZero, zeroOne, oneZero, oneOne, entropyLeft,
				entropyRight);
		return informationGain;
	}

   public double informationGain(double initialEntropy, int zeroZero, int zeroOne, int oneZero, int oneOne,double entropyLeft, double entropyRight) {
		
		int total = zeroZero + zeroOne + oneZero + oneOne;
		double finalEntropy = ((double)((double)zeroZero + (double)zeroOne)/((double)total))*(entropyLeft) + ((double)((double)oneZero + (double)oneOne)/((double)total))*(entropyRight);
		double informationGain = initialEntropy - finalEntropy;
		return informationGain;
	}

	public  double initialEntropy(List<Map<String, Integer>> dataSet) {
		double initialEntropy = 0;
		int zeroClassCount = 0;
		int oneClassCount = 0;
		
		for(Map<String, Integer> dataPoint: dataSet) {
			if(dataPoint.get("Class") == 0) {
				zeroClassCount++;
			}
			else if(dataPoint.get("Class") == 1) {
				oneClassCount++;
			}
			else {
				System.out.println("anomaly: " + dataPoint.get("Class"));
			}
		}
		
		initialEntropy = calculateEntropy(zeroClassCount, oneClassCount);
		return initialEntropy;
		
	}
	
	public  double calculateEntropy(int zeroCount, int oneCount) {
		double entropy = 0;
		double zeroFraction = 0;
		double oneFraction = 0;
		double zeroFractionLog = 0;
		double oneFractionLog = 0;
		
		if(zeroCount != 0) {
			zeroFraction = (double)((double)(zeroCount))/((double)(zeroCount + oneCount));
			zeroFractionLog = Math.log(zeroFraction)/Math.log((double)2);
		}
		
		if(oneCount != 0) {
			oneFraction = (double)((double)(oneCount))/((double)(zeroCount + oneCount));
			oneFractionLog = Math.log(oneFraction)/Math.log((double)2);
		}
		
		entropy = -(zeroFraction*zeroFractionLog + oneFraction*oneFractionLog);
		return entropy;
	}

}
