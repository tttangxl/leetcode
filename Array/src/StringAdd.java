import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringAdd {

    public static void main(String[] args) {
        StringAdd sa = new StringAdd();

        //转换成了ascill码相减
        System.out.println('j'-'a');
//        System.out.println(sa.removeDuplicateLetters("1234512"));
        //
//        System.out.println(addString3("1","111"));
    }

    /**
     * 字符串类型整数相加求和 不能变换成整型 (leetcode 415)
     * @param num1
     * @param num2
     * @return
     * 官方解法
     */
    public static String addString(String num1,String num2){

        /**
         *i 0  j 1
         * x= '1'-'0'
         * y ='0'-'0'
         */
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }

    /**
     * 评论区解法
     * @param num1
     * @param num2
     * @return
     */
    public static String addStringS(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length()-1, j = num2.length()-1;
        while(i >= 0 || j >= 0 || carry != 0){
            if(i>=0) carry += num1.charAt(i--)-'0';
            if(j>=0) carry += num2.charAt(j--)-'0';
            sb.append(carry%10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }


    public static String addString3(String num1,String num2 ){
        StringBuilder sb = new StringBuilder();
        int index1 = num1.length()-1;
        int index2 = num2.length()-1;
        int jinwei=0;
        while (index1>=0||index2>=0||jinwei!=0){
            int x;
            int y;
            if (index1>=0){
                x=num1.charAt(index1)-'0';
            }else {
                x= 0;
            }
            if (index2>=0){
                y=num2.charAt(index2)-'0';
            }else {
                y=0;
            }
            int result = x+y+jinwei;
            jinwei = result/10;
            sb.append(result%10);
            index1--;
            index2--;
        }
        return sb.reverse().toString();
    }

    /***
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     *
     * 不能破坏字符相对位置
     */
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        //统计每个字符出现次数
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            //下标为i的字符
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                //按照字典排序?
                //判断相同字符是否被访问过 判断自建字符串最后一个字符是否比当前字符大 需要重新排序
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    //如果自建字符串最后一个字符比当前字符大 取出最后一个字符 查看统计次数 如果大于0
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        //设置最后一个字符的 为未被访问过
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        //删除最后一个字符
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                //设置当前字符已被访问
                vis[ch - 'a'] = true;
                //追加到自建字符串内
                sb.append(ch);
            }
            //标记字符被取出一次
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }
}
