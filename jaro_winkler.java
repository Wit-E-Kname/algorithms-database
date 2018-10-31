import java.util.Arrays;
import java.util.ArrayList;
import static java.lang.Math.*;

public class jaro_winkler {

	public static double JW(String original, String selected) {

		int PREFIX_LENGTH_LIMIT = 4;
    	int INDEX_NOT_FOUND = -1;
    	double defaultScalingFactor = 0.1;
    	double BOOST_THRESHOLD = 0.7;

		if (original == null || selected == null) {
            return 0;
        }
		String max=selected, min=original;
		// source: https://commons.apache.org/sandbox/commons-text/jacoco/org.apache.commons.text.similarity/JaroWinklerDistance.java.html
		/*
		 * Dj = 0 if m = 0
		 	  = 1/3 { m / s1 + m / s2 + (m - t) / m}
		 * Dw = Dj + (lp(1 - Dj))
		 * l = prefix length, 4
		 * p = scaling factor, 0.1
		 */
		if (original.length() > selected.length()) {
            max = original;
            min = selected;
        }

        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int m = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            char c1 = min.charAt(mi);
            for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
                if (!matchFlags[xi] && c1 == max.charAt(xi)) {
                    matchIndexes[mi] = xi;
                    matchFlags[xi] = true;
                    m++;
                    break;
                }
            }
        }
        char[] ms1 = new char[m];
        char[] ms2 = new char[m];
        for (int i = 0, si = 0; i < min.length(); i++) {
            if (matchIndexes[i] != -1) {
                ms1[si] = min.charAt(i);
                si++;
            }
        }
        for (int i = 0, si = 0; i < max.length(); i++) {
            if (matchFlags[i]) {
                ms2[si] = max.charAt(i);
                si++;
            }
        }
        int t = 0;
        for (int mi = 0; mi < ms1.length; mi++) {
            if (ms1[mi] != ms2[mi]) {
                t++;
            }
        }
        int prefix = 0;
        for (int mi = 0; mi < min.length(); mi++) {
            if (original.charAt(mi) == selected.charAt(mi)) {
                prefix++;
            } else {
                break;
            }
        }
        t = t/2; // half the number of transpositions
        System.out.println("M: " + m + " T: " + t/2 + " P: " + prefix);
        System.out.println("O: " + original.length() + " S: " + selected.length());
        // return new int[] { m, t / 2, prefix, max.length() };
        if (m == 0) {
        	return 0;
        }
        double j = (m*1.0 / original.length() + m*1.0 / selected.length() + (m - t)*1.0 / m) / 3;
        System.out.println("J: " + j);
        double jw = j < BOOST_THRESHOLD ? j : j + Math.min(defaultScalingFactor, 1D / max.length()) * prefix * (1D - j);
		jw = ((int) Math.round(jw*1000) * 1.0) / 1000;
        System.out.println(" JW: " + jw);
        return jw;
	}

	public static void main(String args[]){

    	/*
double m = mtp[0];
        if (m == 0) {
            return 0D;
        }
        double j = ((m / left.length() + m / right.length() + (m - mtp[1]) / m)) / 3;
        double jw = j < 0.7D ? j : j + Math.min(defaultScalingFactor, 1D / mtp[3]) * mtp[2] * (1D - j);
    	*/
		double mtp = JW("hot", "cold");
	}
}
