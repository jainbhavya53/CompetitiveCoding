import java.util.*;

public class Pairs{
  public static void main(String args[]){
     List<Integer> out = new ArrayList<>(Arrays.asList(20,5,2,3,-6,9,11,20,20));
     System.out.println(pairSum2(out,40));
  }

  public static List<Integer> pairSum(List<Integer> input, int target){
     List<Integer> output = new ArrayList<>(2);
     Map<Integer,Integer> map = new HashMap<>();
     for(Integer i: input){
        int value = 0;
        if(map.containsKey(i)){
           value = map.get(i);
        }
        value = value + 1;
	map.put(i,value);
     }
     Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
     for(Map.Entry<Integer,Integer> entry:entrySet){
     	int key = entry.getKey();
  	int diff = target - key;
        int value = entry.getValue();
	if((diff == key && value > 1) || map.containsKey(diff)) {	
	   output.add(key);
	   output.add(diff);
	   return output;
        }
     }
     return output;
  }

  // Better implementation than pairSum
  public static List<Integer> pairSum2(List<Integer> input, int target){
     List<Integer> output = new ArrayList<>(2);
     Set<Integer> set = new HashSet<>();
     for(Integer i: input){
	int diff = target - i;
        if(set.contains(diff)){
	   output.add(i);
	   output.add(diff);
	   return output;
        }
	set.add(i);
     }
     return output;
  }
}
