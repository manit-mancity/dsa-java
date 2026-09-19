package BackTracking;

public class RabinKarp {

    public static void rabinKarp(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        int h = 1;
        int q = 101;
        int d = 256;

        for(int i = 0; i < m-1; i++){
            h = (h * d) % q;
        }

        int p = 0;
        int t = 0;
        for(int i = 0; i < m; i++){
            p = (p *d + pattern.charAt(i)) %q;
            t = (t*d + text.charAt(i)) %q;
        }

        for(int i = 0; i <= n-m; i++){
            if(t == p){
                int j;
                for(j = 0; j < m; j++){
                    if(pattern.charAt(j) != text.charAt(i+j))
                        break;
                }
                if(j == m) System.out.println("Match at index = "+i);

            }
            if( i < n-m){
                t = (d * (t - text.charAt(i) *h) + text.charAt(i+m))%q;

                if(t < 0)
                    t+= q;
            }
        }

    }
    public static void main(String args[]){
        String text = "aaaab";
        String pattern = "aab";
        rabinKarp(text, pattern);
    }
}