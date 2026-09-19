package arrays;

public class parityarray {
    public int[] sortArrayByParity(int[] nums) {
        int arr[] = new int[nums.length];
        int eve=0;
        for(int i = 0;i< nums.length;i++){
            if(nums[i]%2==0){
                eve++;
            }
        }
        int odd=0;
        for(int i = 0;i< nums.length;i++){
            if(nums[i]%2!=0){
                odd++;
            }
        }
        int even[] = new int[eve];
        int oddd[] = new int[odd];
        int temp =0;
        for(int i = 0;i< nums.length;i++){

            if(nums[i]%2==0){
                even[temp]=nums[i];
                temp++;
            }
        }
        int temp1 =0;
        for(int i=0;i< nums.length ;i++){

            if(nums[i]%2!=0){
                oddd[temp]=nums[i];
                temp1++;
            }
        }
        int e = even.length;
        System.arraycopy(even, 0, arr,0,e);
        System.arraycopy(oddd, 0,arr,e,nums.length );
        return arr;
    }
}
