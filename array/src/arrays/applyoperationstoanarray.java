package arrays;

public class applyoperationstoanarray {
    public int[] applyOperations(int[] nums) {
        int end = nums.length-1;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] == nums[i + 1]){
                nums[i]*=2;
                nums[i+1]=0;
            }
            if(nums[i]==0){
                end = nums[i];
                end--;
                nums[i]=nums[i+1];
            }
        }return nums;
        
    }
}
