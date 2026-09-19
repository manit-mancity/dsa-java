package AdvancedTopics;

public class SegmentTree {
    int tree[];
    int n;

    SegmentTree(int arr[]){
        n=arr.length;
        tree=new int[4*n];
        build(arr, 0, 0, n-1);
    }

    private void build(int arr[],int node, int start, int end){
        if(start==end){
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end)/2;
        build(arr, 2*node+1, start, mid);
        build(arr, 2*node+1, mid+1, end);
        tree[node]=tree[2*node+1]+tree[2*node+2];
    }
    public int query(int left, int right){
        return query(0,0, n-1,left, right);
    }
    private int query(int node, int start, int end, int left, int right){
        if(end<left || start>right){
            return 0;
        }
        if(left<=start&&end<=right){
            return tree[node];
        }
        int mid = (start+end)/2;
        int leftSum = query(2*node+1, start, mid, left, right);
        int rightSum = query(2*node+1, mid+1, end , left, right);
        return leftSum+rightSum;
    }
    public static void main(String[] args){
        int arr[] = {10, 12, 13, 15, 1, 5, 7};
        SegmentTree st = new SegmentTree(arr);
            System.out.println(st.query(12,5));
    }
}
