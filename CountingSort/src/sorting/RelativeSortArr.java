package sorting;

public class RelativeSortArr {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n=arr1.length;
        //find max value in arr1
        int max = arr1[0];
        for(int num : arr1){
            if(num>max)max=num;
        }

        //count freq array
        int[] count = new int[max +1];
        for(int num: arr1){
            count[num]++;
        }

        //place elements in arr1 based on values in arr2
        int[] output = new int[n];
        int index = 0;
        for (int num : arr2){
            while(count[num]>0){
                output[index++] = num;
                count[num]--;
            }
        }

        //place remaining elements in ascending order
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                output[index++] = i;
                count[i]--;
            }
        }
        return output;
    }
    public static void main(String[] args){
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};

        int[] result = relativeSortArray(arr1, arr2);

        for(int ele : result){
            System.out.println(ele + " ");
        }
    }

}
