import java.util.*;

public class StringCase {

    /**
     * leetcode hot100 3
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */

    public int lengthOfString(String str){
        Set<Character> set = new HashSet<>();
        int length = 0;
        int rk = -1;
        for (int i=0;i<str.length();i++){
            if (i!=0){
                set.remove(str.charAt(i-1));
            }
            while (rk+1<str.length()&&!set.contains(str.charAt(rk+1))){
                set.add(str.charAt(rk+1));
                rk++;
            }
            length = Math.max(length,rk-i+1);

        }
        return length;
    }

    public int lengthOfLongestSubstring(String s) {
        List<String> subStringList= new ArrayList<>();
        for (int i=0;i<s.length();i++){
            String subString = s.charAt(i)+"";
            if (i==0){
                subStringList.add(subString);
            }
            for (int j =i+1;j<s.length();j++){
                subString = subString+s.charAt(j);
                subStringList.add(subString);
            }
        }
        int count=0;
        for (String substr:subStringList){
            System.out.println("substr:"+substr);
            Set<Character> set = new HashSet<>();
            char [] chaArray = substr.toCharArray();
            for (char c:chaArray){
                set.add(c);
            }
            //存在重复
            if (set.size()!=chaArray.length){
                continue;
            }else {
                count = Math.max(count,chaArray.length);
            }
        }
        return count;
    }

    public int lengthOfLongestSubstringLeetCode(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 反转字符串
     * @param
     */

    public void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }

    public String reverse(String str) {
       StringBuilder stringBuilder = new StringBuilder(str);
       return stringBuilder.reverse().toString();
    }


    /**
     * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的
     * 回文串的长度。
     *
     * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
     * @param args
     */

    /**
     * 思路 回文需要 统计有多少个重复字符
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        char[] occurs = new char[128];

        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            occurs[c]++;
            //当累计到字符出现次数为2时 则代表可以使用2次 然后归零 继续统计
            if (occurs[c] == 2) {
                ans += 2;
                occurs[c] = 0;
            }
        }
        //ans为偶数对字符出现的次数 如果比原字符小则可以选一个出来做中心值  一次循环完成计算
        if (ans < s.length()) ans++;
        return ans;
    }

    public static void main(String[] args) {
        StringCase caseStr = new StringCase();
        int [] arary = new int[5];


        System.out.println(caseStr.longestPalindrome("abacbdabc"));
    }
}
