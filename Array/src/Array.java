import java.util.*;

public class Array {
    public static void main(String[] args) {
        int [] num  = {1,2,3};
        int k =3;
        System.out.println(subarray(num,k));

    }

    /** leetcode hot100 1
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
     *
     * 你可以按任意顺序返回答案。
     * @param nums
     * @param
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        for (int i=0;i<nums.length;i++){
            for (int j=nums.length-1;j>i;j--){
                 int sum = nums[i]+nums[j];
                 if (sum==target){
                     result[0] = i;
                     result[1] = j;
                 }
                }
            }
        return result;
        }

    /**
     * 官解两数相加
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumLeetCoode(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }



    public static int subarrayCount(int [] nums,int k){
        int count = 0;
        for (int i=0;i<nums.length;i++){
            int sum = 0;
            for (int j=i;j>=0;j--){
                sum= sum+nums[j];
                if (sum==k){
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarray(int [] nums,int k ){
        int count = 0;
        int sum = 0 ;
        Map<Integer,Integer> cache = new HashMap<>();
        cache.put(0,1);
        for (int num:nums){
            sum+=num;
            int diff = sum-k;
            if(cache.containsKey(diff)){
                count+= cache.get(diff);
            }
            cache.put(sum,cache.getOrDefault(sum,0)+1);
        }
        return count;
    }


    /** letcode 53
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 子数组
     * 是数组中的一个连续部分。

     * 示例 1：
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     *
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     *
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int maxAns = nums[0];
        for(int x:nums){
            pre = Math.max(pre+x,x);
            maxAns = Math.max(maxAns,pre);
        }
        return maxAns;

    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * leetcode 56 合并数组区间
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);

    }


}
