package sorting;
import java.util.*;

public class CountingSort {
    public static void countingSort(int arr[]){
        if(arr.length == 0) return;

        int max = arr[0];
        for(int num : arr){
            if(num > max) max = num;
        }

        //count frequency of the elements
        int count[] = new int[max+1];
        int output[] = new int[arr.length];
        for(int num : arr){
            count[num]++;
        }

        //number of elements less than equal to index i
        for(int i = 1; i<=max; i++){
            count[i] += count[i-1];
        }

        //placing the numbers on the right index value

        for(int i = arr.length -1; i>=0; i--){
            int num = arr[i];
            output[count[num] -1] = num;
            count[num]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    public static void main(String args[]){
        int arr[] = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Original Array");
        for(int num : arr){
            System.out.print(num + " ");
        }
        countingSort(arr);
        System.out.println("\nSorted array using counting sort");
        for(int num: arr){
            System.out.print(num + " ");
        }
    }
}