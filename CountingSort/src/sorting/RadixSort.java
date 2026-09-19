package sorting;

public class RadixSort {

    public static void radixSort(int arr[]){
        int max = arr[0];
        for(int val : arr){
            if(val > max ) max = val;
        }
        for(int exp = 1; max/exp > 0; exp*=10){
            countingSort(arr, exp);
        }
    }
    public static void countingSort(int arr[], int exp){
        int n = arr.length;
        int output[] = new int[n];
        int count[] = new int[10];
        //tallying the frequency
        for(int num: arr){
            count[(num / exp) % 10]++;
        }
        //number of elements less than equal to index i
        for(int i = 1; i <10; i++){
            count[i] += count[i-1];
        }
        //placing
        for(int i = n-1; i >= 0; i--){
            int index = (arr[i] / exp) % 10;
            output[count[index] -1] = arr[i];
            count[index]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String args[]){
        int arr[] = {170, 45, 75, 90, 802, 24,2, 66};
        System.out.println("Original Array");
        for(int num : arr)
            System.out.print(num+" ");
        System.out.println();
        radixSort(arr);
        for(int num : arr){
            System.out.print(num+" ");

        }
    }
}
