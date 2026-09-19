package priorityQueues;

import java.util.*;

class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone :stones){
            pq.add(stone);
        }
        while(pq.size() > 1){
            int stone1 = pq.poll(); //heaviest
            int stone2 = pq.poll(); // second heaviest

            if(stone1 != stone2) {
                pq.add(stone1 - stone2);
            }
        }
        return pq.size() == 0 ? 0 : pq.poll();
    }
}