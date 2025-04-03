package sorting;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] arr = {8,3,4,12,5,6};
        arr = mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }
        int mid = arr.length/2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

        int[] merge = merged(left,right);
        return merge;
    }

    private static int[] merged(int[] first, int[] second) {
        int[] ans = new int[first.length+second.length];
        int i = 0, j = 0;
        int k = 0;
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                ans[k] = first[i];
                i++;
            }
            else{
                ans[k] = second[j];
                j++;
            }
            k++;
        }
        while (i < first.length) {
            ans[k] = first[i];
            i++;
        }
        while (j < second.length) {
            ans[k] = second[j];
            j++;
        }

        return ans;
    }
}
