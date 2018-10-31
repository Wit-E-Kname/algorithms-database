import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.Math.*;

public class modP {

	public static float computeMeanofModes(ArrayList<Double> arr) {
		int maxCount = 0;
		float modP = 0;
		int countP = 0;
		int[] count = new int[100];
		Arrays.fill(count, 0);

		for(int i = 0; i < /*arr.length*/arr.size(); i++){
			int val = (int) round(/*arr[i]*/arr.get(i) * 100);
			count[val]++;
			maxCount = max(maxCount, count[val]);
		}
		// how to deal with multiple modes
		for(int i = 0; i < count.length; i++) {
			if(count[i]!=0) {
				System.out.println(i + ": " + count[i]);
			}
			if(count[i]==maxCount) {
				modP += i * 0.01;
				countP++;
			}
		}
		modP = modP / countP;
		System.out.println("mod pressure: "+ modP + " count: " + countP);
		return modP;
	}

	public static void main(String args[]){
		
		ArrayList<Double> arr = new ArrayList<Double>();
		arr.add(0.4251);
		arr.add(0.2912);
		arr.add(0.451);
		arr.add(0.431);
		arr.add(0.411);
		arr.add(0.412);
		
		// double arr[] = {0.4251, 0.2912, 0.451, 0.431, 0.411, 0.412};
		/*
		double arr[] = new double[6];
		arr[0] = 0.4251;
		arr[1] = 0.2912;
		arr[2] = 0.451;
		arr[3] = 0.431;
		arr[4] = 0.411;
		arr[5] = 0.412;
		*/
		float modP = computeMeanofModes(arr);
		System.out.println("Received mean of modes: " + modP);
	}
}
