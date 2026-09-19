package arrays;

public class HighestAltitude {
    public int largestAltitude(int[] gain) {
        int temp =0;
        int max =0;
        for(int i =0;i< gain.length;i++){
            temp=temp+gain[i];
            if(temp>max)max=temp;
        }return max;
    }
}
