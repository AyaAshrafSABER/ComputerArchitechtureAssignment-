import java.util.ArrayList;
import java.util.Random;

/*
 * this is an implementation of Correlating branch predictor according to 1 
 * level prediction based on the states of the last branch if it taken or not
 * if Taken => predict from the branch Target Buffer "Taken"
 * if NotTaken => predict from the branch Target Buffer "NotTaken"
 *  1-bit predictor with 1-bit correlation 
 *  condition true => branch NotTaken
 *  condition false => branch taken*/
public class CorrelatingBranches {
	private final int NUMBER_OF_BRANCHES = 2;
	private final int  NUMBER_OF_EXECUTION = 10;
	private ArrayList<Boolean> branchNotTaken; 
	private ArrayList<Boolean> branchTaken;
	private boolean lastState = false; 
	private ArrayList<Boolean> correctAction;
	private int rightprediction;
	private Random random;
	
	public CorrelatingBranches() {
		random = new Random();
		branchNotTaken = generator();
		System.out.println("predict Not Taken : " + branchNotTaken.toString());
		branchTaken = generator();
		System.out.println("predict Taken     : " + branchTaken.toString());
		correctAction = generator();
		System.out.println("True Value        : " + correctAction.toString());

		rightprediction = 0;
	}
	void Correlarte() {
		for(int i = 0 ; i < NUMBER_OF_EXECUTION; i++) {
			int randomBranch = random.nextInt(NUMBER_OF_BRANCHES);
			boolean trueValue = correctAction.get(randomBranch);
			boolean predictedValue;
			if(lastState) {
			    predictedValue = branchNotTaken.get(randomBranch);
				if (predictedValue == trueValue) {
					rightprediction ++;
				}else {
					branchNotTaken.remove(randomBranch);
					branchNotTaken.add(randomBranch, trueValue);
					lastState = !lastState;
				}
				
			}else {
			    predictedValue = branchTaken.get(randomBranch);
				if (predictedValue == trueValue) {
					rightprediction ++;
				}else {
					branchTaken.remove(randomBranch);
					branchTaken.add(randomBranch, trueValue);
					lastState = !lastState;
				}
			}
			System.out.println("Number Of the Branch (" + randomBranch + ")" + " TValue (" + trueValue + ") PValue (" + predictedValue + ")");
			System.out.println("predict Not Taken :" + branchNotTaken.toString());
			System.out.println("predict     Taken :" + branchTaken.toString());

		}
	}
	private ArrayList<Boolean> generator() {
		ArrayList<Boolean> arr = new ArrayList<Boolean>();
		for(int i = 0 ; i < NUMBER_OF_BRANCHES ; i++) {
			arr.add(random.nextBoolean());	
		}	
		return arr;
	}
	public float getAccuracy() {
		return (float)rightprediction /(float) NUMBER_OF_EXECUTION;
	}

}
