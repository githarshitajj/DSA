package sorting;

public class Quick1 {
    public static void main(String[] args) {

    }
    static int partition(int[] arr, int low, int hi){
        //choosing the pivot as the corner element
        int pivot = arr[hi];

        //index of smaller element and indicates
        //the right position of the pivot so far
        int i = low-1;
        for (int j = low; j < hi; j++){
            if (arr[j] < pivot){
                i++;
                swap(arr,i,j);
            }
        }
        return pivot;
    }
    static void swap(int[] arr, int i, int j){

    }
}
