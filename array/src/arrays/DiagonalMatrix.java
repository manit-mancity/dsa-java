package arrays;

public class DiagonalMatrix {
    public int diagonalSum(int[][] mat) {
        int sum =0;
        for(int i =0;i<mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(j==i){
                    sum+=mat[i][j];
                }
                if(j==mat[i].length-1-i && i!=j){
                    sum+=mat[i][mat[i].length-1-i];
                }
            }
        }
        return sum;
    }
}
