import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Leetcode 253. Meeting Rooms II
 * Link: https://leetcode.com/problems/meeting-rooms-ii/description/
 */
public class MeetingRoomsII {
    /**
     * We want to accommodate as many meetings in the same room if possible otherwise
     * we use a new room. To efficiently achieve this we need our intervals sorted on its start time
     * for below solution so that we dont mark the room unavailable from 0-30 where in fact it was
     * available from for example 15-20 because our start time were not sorted. Once intervals are sorted
     * based on start time we want to always find the meeting room which has oldest/earliest end time. To
     * achieve this we can use Heap/PriorityQueue with each element representing a meeting room with its
     * endtime. We need min Heap for this. After processing all intervals, size is the heap is our min number
     * of meeting rooms
     *
     * TC: O(nlogn)
     * SC: O(n) worst case n meeting rooms in heap
     */
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); //TC O(nlogn)

        for (int[] meeting: intervals) {
            Integer end = pq.peek(); //TC O(1)

            if (end != null && end <= meeting[0]) {
                pq.poll(); //TC O(logn)
            }
            pq.add(meeting[1]); //TC O(logn)
        }
        return pq.size();
    }
}
