package arrays;

public class buyandsell {
    public int maxProfit(int[] prices) {
        int val=0;
        for(int i=0;i<prices.length;i++){
            for(int j =i;j< prices.length;j++){
                if(val<0)val=0;
                if(j-i>val)val=j-i;
            }
        }return val;
    }
}
