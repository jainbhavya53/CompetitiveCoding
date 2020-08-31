package com.guavus.jobs2.bhavya.leetcode;
import java.util.*;
/**
 * @author bhavya.jain
 */

// Solution 1 and Solution 3 have almost same runtime
// Solution 2 has slightly more runtime
// Solution 4 has average O(N) runtime complexity and worst case O(N^2)

public class TopKFrequentElements {
    Map<Integer, Integer> map;

    public class CustomIntComp implements Comparator<Integer> {
        public int compare(Integer k1, Integer k2) {
            return (-1 * Integer.compare(map.get(k1), map.get(k2)));
        }
    }

    // Solution 2
    public List<Integer> topKFrequent2(int[] nums, int k) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int value = map.get(nums[i]);
                map.put(nums[i], value + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Integer> temp = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            temp.add(entry.getKey());
        }
        List<Integer> output = new ArrayList<>();
        Collections.sort(temp, new CustomIntComp());
        for (int i = 0; i < k; i++) {
            output.add(temp.get(i));
        }
        return output;
    }

    // Solution 3
    public List<Integer> topKFrequent3(int[] nums, int k) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int value = map.get(nums[i]);
                map.put(nums[i], value + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new CustomIntComp());
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            queue.add(entry.getKey());
        }
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            output.add(queue.remove());
        }
        return output;
    }
}

// Solution1
class Solution {
    public class CustomIntComp implements Comparator<CustomInt>{
        public int compare(CustomInt c1,CustomInt c2){
            return (-1* Integer.compare(c1.value,c2.value));
        }
    }
    public class CustomInt{
        int key;
        int value;
        public CustomInt(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                int value = map.get(nums[i]);
                map.put(nums[i],value+1);
            }
            else{
                map.put(nums[i],1);
            }
        }

        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        List<CustomInt> temp = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry:entrySet){
            temp.add(new CustomInt(entry.getKey(),entry.getValue()));
        }
        Collections.sort(temp,new CustomIntComp());
        for(int i = 0;i<k;i++){
            output.add(temp.get(i).key);
        }
        return output;
    }
}

// Solution 4
class BestSolution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            int temp = 0;
            if(map.containsKey(nums[i])){
                temp = map.get(nums[i]);
            }
            temp = temp + 1 ;
            map.put(nums[i],temp);
        }
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        Point arr[] = new Point[entrySet.size()];
        int i = 0;
        for(Map.Entry<Integer,Integer> entry :entrySet){
            int key = entry.getKey();
            int value = entry.getValue();
            arr[i] = new Point(key,value);
            i++;
        }
        quickSelect(arr,0,arr.length-1,arr.length-k-1);
        int output[] = new int[k];
        for(int j = 0;j<k;j++){
            output[j] = arr[arr.length-1-j].key;
        }
        return output;
    }

    class Point{
        int key;
        int value;

        public Point(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public void quickSelect(Point[] arr, int low,int high,int k){
        if(k < 0){
            return ;
        }
        int min = low;
        int max = high;
        int index = partition(arr,min,max);
        while(index != k){
            if(index > k){
                max = index - 1;
                //index = partition(arr,low,index -1);
            }
            else{
                min = index + 1;
                //index = partition(arr,index+1,high);
            }
            index = partition(arr,min,max);
        }
    }

    public int partition(Point[] arr,int low,int high){
        int pivot = low + (int)(Math.random() * (high-low));
        swap(arr,low,pivot);
        int i = low;
        for(int j = low+1;j<=high;j++){
            if(arr[j].value < arr[low].value){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i,low);
        return i;
    }

    public void swap(Point[] arr,int first,int second){
        Point temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
