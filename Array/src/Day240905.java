import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day240905 {
    /**
     * letcode 88 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @return
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] newnum1 = new int[m];
        int[] newnum2 = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] != 0) {
                newnum1[i] = nums1[i];
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] != 0) {
                newnum2[i] = nums2[i];
            }
        }
        nums1 = newnum1;
        nums2 = newnum2;
        int[] sortedArr = new int[m + n];
        int idx = 0, idx1 = 0, idx2 = 0;
        while (idx1 < m && idx2 < n) {
            if (nums1[idx1] < nums2[idx2]) {
                sortedArr[idx] = nums1[idx1];
                idx1 += 1;
            } else {
                sortedArr[idx] = nums2[idx2];
                idx2 += 1;
            }
            idx += 1;
        }
        if (idx1 < m) {
            while (idx1 < m) {
                sortedArr[idx] = nums1[idx1];
                idx1 += 1;
                idx += 1;
            }
        } else {
            while (idx2 < n) {
                sortedArr[idx] = nums2[idx2];
                idx2 += 1;
                idx += 1;
            }
        }
        return sortedArr;

    }

    /**
     * 合并数组 直接排序
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @return
     */
    public static int[] mergeLeetCode1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
        return nums1;
    }

    /**
     * 双指针解法
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static int[] mergeLeetCode2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        return sorted;
    }


    /**
     * 14. 最长公共前缀
     * 简单
     * 相关标签
     * 相关企业
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *
     * @param
     */

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length<1){
            return "";
        }
        if (strs.length==1){
            return strs[0];
        }
        String commonPrefix = "";
        String first = strs[0];
        for (int j = 1; j < strs.length; j++) {
            String next = strs[j];
            //返回公共前缀字符最大下标
            String prefix = getcommonPrefixIndex(first, next);
            if (prefix == "") {
                return "";
            }else {
                if (commonPrefix==""){
                    commonPrefix = prefix;
                }else if (prefix.length()<commonPrefix.length()){
                    commonPrefix = prefix;
                }
            }
        }
        return commonPrefix;

    }

    private static String getcommonPrefixIndex(String current, String next) {
        String prefix = "";
        int length = Math.min(current.length(),next.length());
        for (int i = 0; i < length; i++) {
            if (current.charAt(i) == next.charAt(i)) {
                if (i+1>current.length()) {
                    prefix = current.substring(0, current.length());
                }else {
                    prefix = current.substring(0, i+1);
                }
            } else {
                //遇见一个字符不同则不用继续匹配直接返回
                break;
            }
        }
        return prefix;

    }

    /**
     * leetcode
     * @param strs
     * @return
     */
    public static String longestCommonPrefixLeetCode(String[] strs) {
        if (strs.length == 0)
            return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            if (ans.equals(""))
                return ans;
        }
        return ans;
    }

    /**
     *  leetcode 125. 验证回文串
     * 简单
     * 相关标签
     * 相关企业
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     *
     * 字母和数字都属于字母数字字符。
     *
     * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     * @param
     */
    /**
     * 小写字母 97 -122  大写字母 65-90
     * @param s api调用
     * @return
     */
    public  static  boolean isPalindrome(String s) {
        StringBuffer sgood = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sgood.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
        return sgood.toString().equals(sgood_rev.toString());
    }

    /**
     * 原字符比较
     * @param s
     * @return
     */
    public boolean isPalindromeLeetCode(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String []  arr = {"aaa","aa","aaa"};
        System.out.println(longestCommonPrefix(arr));

    }
}
