import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 求两个数组的交集
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 */
public class Case1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数组：");
        while (scanner.hasNext()) {
            int[] array1 = string2IntArray(scanner.next());
            int[] array2 = string2IntArray(scanner.next());
            System.out.println(Arrays.toString(intersectionArray(array1, array2)));
            System.exit(1);
        }
    }

    /**
     * 求两个数组交集统计次数
     *
     * @param array1
     * @param array2
     * @return
     */
    private static Integer[] intersectionArray(int[] array1, int[] array2) {
        Map<Integer, Integer> countMap = new ConcurrentHashMap<>();
        for (int i = 0; i < array1.length; i++) {
            countMap.put(array1[i], countMap.get(array1[i]) == null ? 1 : countMap.get(array1[i]).intValue() + 1);
        }
        for (int i = 0; i < array2.length; i++) {
            countMap.put(array2[i], countMap.get(array1[i]) == null ? 1 : countMap.get(array2[i]).intValue() + 1);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue().intValue() < 2) {
                countMap.remove(entry.getKey());
            }
        }
        return countMap.keySet().toArray(new Integer[countMap.keySet().size()]);
    }


    /**
     * 将字符串转换成int数组
     *
     * @param inputStr
     * @return
     */
    public static int[] string2IntArray(String inputStr) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputStr, ",");
        int[] temp = new int[stringTokenizer.countTokens()];
        int index = 0;
        while (stringTokenizer.hasMoreElements()) {
            temp[index++] = Integer.parseInt(stringTokenizer.nextToken(","));
        }
        return temp;
    }
}
