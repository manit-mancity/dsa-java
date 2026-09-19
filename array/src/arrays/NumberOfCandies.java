package arrays;
import java.util.*;

public class NumberOfCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();

        int max = 0;
        int arr[] = new int[candies.length];
        for(int num : candies){
            if(num>max)max=num;
        }
        for(int i = 0; i < candies.length; i++){
            int temp = extraCandies + candies[i];
            if(temp>max)result.add(true);
            if(temp<=max)result.add(false);

        }return result;
    }
}
