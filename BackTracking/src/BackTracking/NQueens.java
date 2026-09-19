package BackTracking;
import java.util.*;

class NQueens{
    public static void queenUtil(int j, int n, List<Integer> board, boolean row[], boolean diag1[],
                                 boolean diag2[], List<List<Integer>> result){

        if(j > n) {
            result.add(new ArrayList<>(board));
            return;
        }

        for(int i = 1; i <= n; i++){
            if(!row[i] && !diag1[i+j] && !diag2[i-j+n]){
                board.add(i);
                row[i] = true;
                diag1[i+j] = true;
                diag2[i-j+n] = true;
                queenUtil(j+1, n, board, row, diag1, diag2, result);
                board.remove(board.size() -1);
                row[i] = false;
                diag1[i+j] = false;
                diag2[i-j+n] = false;
            }
        }
    }

    public static List<List<Integer>> queens(int n){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> board = new ArrayList<>();
        boolean row[] = new boolean[n+1];
        boolean diag1[] = new boolean[2*n+1];
        boolean diag2[] = new boolean[2*n +1];
        queenUtil(1, n, board, row, diag1, diag2, result);
        return result;

    }
    public static void main(String args[]){
        List<List<Integer>> ans = queens(4);
        System.out.println(ans);
    }
}