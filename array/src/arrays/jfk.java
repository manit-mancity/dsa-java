package arrays;

public class jfk {
    private static int add(int m, int p){
        int t = m+p;
        return t;
    }
    private static int add(int a, int b, int c, int d){
        int gau = a+b+c+d;
        return gau;
    }
    public static void main(String args[]){
        System.out.println(add(1,2));
        System.out.println(add(1, 2, 3, 4));
    }
}
