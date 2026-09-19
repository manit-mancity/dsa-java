package arrays;

public class RichstcustomerWealth {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for(int i =0;i< accounts.length;i++){
            int temp =0;
            for(int j = 0;j<accounts[i].length;j++){
                temp = temp + accounts[i][j];
                if(temp>max)max=temp;
            }
        }return max;
    }
}
