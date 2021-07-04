import java.util.*;
import java.math.BigInteger;

/*
https://www.hackerearth.com/practice/algorithms/dynamic-programming/introduction-to-dynamic-programming-1/practice-problems/algorithm/jump-k-forward-250d464b/

Bottom up approach

O(N) time complexity and O(N) space complexity

*/

/*This solution is for Jumping stones problem on HackerEarth,
If we want to find solution for NkLadders problem, then we need to take
into account, arr[0] = 1 while summing values.
This is because, in jumping stones problem we start from the first stone, 
while in NkLadders problem, we start off the ladder i.e. we stand at the zeroth step.
So we need to take into account ways of reaching from zeroth step as well in  NkLadders problem


Recursive approach would have taken O(k^n)
*/
public class NkLadders {
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int k = in.nextInt();
	BigInteger ways = computeWaysBigInt(n,k); 
	System.out.println(ways.mod(new BigInteger("1000000007")));
    }

    // Recurrence formula :
    // If i > k
    // f(i) = f(i-1) + f(i-1) - f(i-k-1)
    // f(i) = 2 * f(i-1) - f(i-k-1)
    public static int computeWays(int n, int k){
	int[] arr = new int[n+1];
	arr[0] = 0;
	arr[1] = 1;
	if(n >= 2){
	   arr[2] = 1;
	}
	for(int i = 3;i<=n;i++) {
	    if(i <= k+1){
		arr[i] = arr[i-1] * 2;	
	    }
	    else{
		arr[i] = 2*arr[i-1] - arr[i-k-1];
	    }
	}
	return arr[n];
    }

    // Recurrence formula :
    // If i > k
    // f(i) = f(i-1) + f(i-1) - f(i-k-1)
    // f(i) = 2 * f(i-1) - f(i-k-1)
    public static BigInteger computeWaysBigInt(int n, int k){
	List<BigInteger> arr = new ArrayList<>(n+1);
	BigInteger two = BigInteger.valueOf(2);
	arr.add(BigInteger.ZERO);
	arr.add(BigInteger.ONE);
	if(n >= 2){
	   arr.add(BigInteger.ONE);
	}
	for(int i = 3;i<=n;i++) {
	    if(i <= k+1) {
		// arr[i] = arr[i-1] * 2;	
		arr.add(two.multiply(arr.get(i-1)));
	    }
	    else{
		// arr[i] = 2*arr[i-1] - arr[i-k-1];
		arr.add(two.multiply(arr.get(i-1)).subtract(arr.get(i-k-1)));
	    }
	}
	return arr.get(n);
    }

    /*
       Top Down Approach
       With O(kN) time complexity and O(N) space complexity
    */
    public static int computeWaysTopDown(int n, int k){
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        return computeWaysTopDown(n,k,arr);
    }

    public static int computeWaysTopDown(int n, int k,int[] arr) {
        if(arr[n] != 0) {
            return arr[n];
        }
        for(int i = 1;i<=k && n-i >= 1;i++) {
            arr[n] = arr[n] + computeWaysTopDown(n-i,k,arr);
        }
        return arr[n];
    }
}
