import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        int [] sort = {9,6,8,5,7,8};
        quickSort(sort,3,4);
        System.out.println(Arrays.toString(sort));
    }
    //常见排序算法

    //冒泡排序
    public  int[] bubbleSort(int[] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    //选择排序
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //找到最小元素下标和 i下标的元素互换 使i下标成为最小元素下标 倒序则换成找最大元素即可
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr
     * @return arr
     */
    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i - 1;
            int current = arr[i];
            while (preIndex >= 0 && current < arr[preIndex]) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex -= 1;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }
    /**
     * 希尔排序
     *
     * @param arr
     * @return arr
     */
    public static int[] shellSort(int[] arr) {
        int n = arr.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int current = arr[i];
                int preIndex = i - gap;
                // Insertion sort
                while (preIndex >= 0 && arr[preIndex] > current) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = current;

            }
            gap /= 2;
        }
        return arr;
    }
    /**
     * 归并排序
     *
     * @param arr
     * @return arr
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int middle = arr.length / 2;
        int[] arr_1 = Arrays.copyOfRange(arr, 0, middle);
        int[] arr_2 = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(arr_1), mergeSort(arr_2));
    }

    /**
     * Merge two sorted arrays
     *
     * @param arr_1
     * @param arr_2
     * @return sorted_arr
     */
    public static int[] merge(int[] arr_1, int[] arr_2) {
        int[] sorted_arr = new int[arr_1.length + arr_2.length];
        int idx = 0, idx_1 = 0, idx_2 = 0;
        while (idx_1 < arr_1.length && idx_2 < arr_2.length) {
            if (arr_1[idx_1] < arr_2[idx_2]) {
                sorted_arr[idx] = arr_1[idx_1];
                idx_1 += 1;
            } else {
                sorted_arr[idx] = arr_2[idx_2];
                idx_2 += 1;
            }
            idx += 1;
        }
        if (idx_1 < arr_1.length) {
            while (idx_1 < arr_1.length) {
                sorted_arr[idx] = arr_1[idx_1];
                idx_1 += 1;
                idx += 1;
            }
        } else {
            while (idx_2 < arr_2.length) {
                sorted_arr[idx] = arr_2[idx_2];
                idx_2 += 1;
                idx += 1;
            }
        }
        return sorted_arr;
    }

    public static void quickSort(int arr[],int startIndex,int endIndex){
        //递归结束条件为startIndex大于或等于endIndex
        if(startIndex>=endIndex){
            return;
        }

        //得到基准元素位置
        int pIndex=partition(arr,startIndex,endIndex);

        //根据基准元素分两部分进行递归排序
        quickSort(arr,startIndex,pIndex-1);
        quickSort(arr,pIndex+1,endIndex);
    }
    /*
     * 分治法（双边循环法）
     * arr  待排序数组
     * startIndex  起始下标
     * endIndex  结束下标
     * */
    public static int partition(int arr[],int startIndex,int endIndex)
    {
        int p=arr[startIndex];//基准元素(可取随机位置)
        int l=startIndex;//左指针
        int r=endIndex;//右指针

        while(l!=r){
            //控制右指针向左移动，找到小于基准元素的那个数
            while((l<r)&&(arr[r]>p)){
                r--;
            }
            //控制左指针向右移动，找到大于基准元素的那个数
            while((l<r)&&(arr[l]<=p)){
                l++;
            }
            //交换l指针和r指针所指的元素
            if(l<r){
                int tmp=arr[l];
                arr[l]=arr[r];
                arr[r]=tmp;
            }
        }

        //交换基准元素和重合点的元素
        arr[startIndex]=arr[l];
        arr[l]=p;
        return l;
    }

    // Global variable that records the length of an array;
    static int heapLen;

    /**
     * Swap the two elements of an array
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Build Max Heap
     * @param arr
     */
    private static void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
    }

    /**
     * Adjust it to the maximum heap
     * @param arr
     * @param i
     */
    private static void heapify(int[] arr, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (right < heapLen && arr[right] > arr[largest]) {
            largest = right;
        }
        if (left < heapLen && arr[left] > arr[largest]) {
            largest = left;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, largest);
        }
    }

    /**
     * Heap Sort
     * @param arr
     * @return
     */
    public static int[] heapSort(int[] arr) {
        // index at the end of the heap
        heapLen = arr.length;
        // build MaxHeap
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            // Move the top of the heap to the tail of the heap in turn
            swap(arr, 0, i);
            heapLen -= 1;
            heapify(arr, 0);
        }
        return arr;
    }

    /**
     * Gets the maximum and minimum values in the array
     *
     * @param arr
     * @return
     */
    private static int[] getMinAndMax(int[] arr) {
        int maxValue = arr[0];
        int minValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            } else if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return new int[] { minValue, maxValue };
    }

    /**
     * Counting Sort
     *
     * @param arr
     * @return
     */
    public static int[] countingSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        int[] countArr = new int[maxValue - minValue + 1];
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - minValue] += 1;
        }
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = countArr[arr[i] - minValue] - 1;
            result[idx] = arr[i];
            countArr[arr[i] - minValue] -= 1;
        }
        return result;
    }

    /**
     * Gets the maximum and minimum values in the array
     * @param arr
     * @return
     */
    private static int[] getMinAndMax(List<Integer> arr) {
        int maxValue = arr.get(0);
        int minValue = arr.get(0);
        for (int i : arr) {
            if (i > maxValue) {
                maxValue = i;
            } else if (i < minValue) {
                minValue = i;
            }
        }
        return new int[] { minValue, maxValue };
    }

    /**
     * Bucket Sort
     * @param arr
     * @return
     */
    public static List<Integer> bucketSort(List<Integer> arr, int bucket_size) {
        if (arr.size() < 2 || bucket_size == 0) {
            return arr;
        }
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        int bucket_cnt = (maxValue - minValue) / bucket_size + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucket_cnt; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int element : arr) {
            int idx = (element - minValue) / bucket_size;
            buckets.get(idx).add(element);
        }
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i).size() > 1) {
//                buckets.set(i, sort(buckets.get(i), bucket_size / 2));
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            for (int element : bucket) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Radix Sort
     *
     * @param arr
     * @return
     */
    public static int[] radixSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int N = 1;
        int maxValue = arr[0];
        for (int element : arr) {
            if (element > maxValue) {
                maxValue = element;
            }
        }
        while (maxValue / 10 != 0) {
            maxValue = maxValue / 10;
            N += 1;
        }
        for (int i = 0; i < N; i++) {
            List<List<Integer>> radix = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                radix.add(new ArrayList<Integer>());
            }
            for (int element : arr) {
                int idx = (element / (int) Math.pow(10, i)) % 10;
                radix.get(idx).add(element);
            }
            int idx = 0;
            for (List<Integer> l : radix) {
                for (int n : l) {
                    arr[idx++] = n;
                }
            }
        }
        return arr;
    }


}
