package sorting;

import java.util.Arrays;

import static java.util.Collections.swap;

public class Selection {
    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3};
        int[] ans = selectionSort(arr);
        System.out.println(Arrays.toString(ans));
    }

    private static int[] selectionSort(int[] arr) {
        int n = arr.length;
        for(int i=0; i<n; i++) {
            int last = n-i-1;
            int maxIndex = getMaxIndex(arr,0,last);
            swapped(arr,maxIndex,last);
        }
        return arr;
    }

    private static void swapped(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static int getMaxIndex(int[] arr, int start, int end) {
        int max = start;
        for(int i=start; i<=end; i++) {
            if(arr[i] > arr[max]) {
                max = i;
            }
        }
        return max;
    }
}
