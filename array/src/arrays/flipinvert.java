package arrays;

public class flipinvert {
    public int[][] flipAndInvertImage(int[][] image) {
        for(int i =0;i<image.length;i++){
            int temp=image[i].length-1;
            for(int j=0;j<=(image[i].length-1)/2;j++){
                if(j!=temp){
                    int temp1= image[i][j];
                    image[i][j]=image[i][temp];
                    image[i][temp]=temp1;
                    if(image[i][j]==0)image[i][j]=1;
                    else {
                        image[i][j] = 0;
                    }
                    if(image[i][temp]==0)image[i][temp]=1;
                    else{
                        image[i][temp]=0;
                    }
                }else{
                    if(image[i][j]==0)image[i][j]=1;
                    else {
                        image[i][j] = 0;
                    }
                }
                temp--;
            }
        }return image;
    }
}
