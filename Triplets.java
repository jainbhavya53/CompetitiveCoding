import java.util.*;

public class Triplets{
    public static void main(String args[]){
	int targetSum = Integer.parseInt(args[0].trim());
	int[] out = {20,5,2,3,-6,9,11,4,12};
        System.out.println(triplets(out,targetSum));	
    }

    public static List<List<Integer>> triplets(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> output = new ArrayList<>();
        for(int i = 0;i<arr.length-1;i++){
            // This is to avoid duplication of triplets
            if(i > 0 && arr[i] == arr[i-1]){
                continue;
            }
            List<List<Integer>> listOfPairs = pairs(arr,i+1,arr.length-1,target-arr[i]);
            for(List<Integer> pair : listOfPairs){
                output.add(pair);
            }
        }
        return output;
    }

    /* We can also find pairs using a HashMap, but it would take O(N) space.
       The following method uses a two pointer approach and finds the pairs in O(N) time with O(1) space.
       But it requires the array to be sorted, which takes another O(NlogN) time. Due to this additional time,
       we did not use the two pointer approach to solve the Pairs problem. */
    public static List<List<Integer>> pairs(int[] arr, int start, int end, int target) {
        List<List<Integer>> output = new ArrayList<>();
        //Set<Integer> seenNums = new HashSet<>();
        int left = start;
        int right = end;
        int lCounter = 0;
        int rCounter = 0;
        while(left < right) {
            // This is to avoid duplication of pairs for a given pair
            if(lCounter > 0 && arr[left] == arr[left-1]){
                left = left + 1;
                continue;
            }
            // This is to avoid duplication of pairs for a given pair
            if(rCounter > 0 && arr[right] == arr[right+1]){
                right = right - 1;
                continue;
            }
            int sum = arr[left] + arr[right];
            if(sum == target){
                //if(!seenNums.contains(arr[left]) && !seenNums.contains(arr[right])){
                    List<Integer> temp = new ArrayList<>(3);
                    temp.add(arr[start-1]);
                    temp.add(arr[left]);
                    temp.add(arr[right]);
                    output.add(temp);
                    //seenNums.add(arr[left]);
                    //seenNums.add(arr[right]);
                //}
                left = left + 1;
                lCounter = lCounter + 1;
            }
            else if(sum < target){
                left = left + 1;
                lCounter = lCounter + 1;
            }
            else{
                right = right - 1;
                rCounter = rCounter + 1;
            }
        }
        return output;
    }

}
