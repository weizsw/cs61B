import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> returnQueue = new Queue<>();

        for (Item item: items) {
            Queue<Item> itemQueue = new Queue<>();
            itemQueue.enqueue(item);
            returnQueue.enqueue(itemQueue);
        }

        return returnQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> sortedQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            sortedQueue.enqueue(getMin(q1, q2));
        }
        return sortedQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() == 0 || items.size() == 1) {
            return items;
        }
        Queue<Queue<Item>> workingQueue = makeSingleItemQueues(items);
        while (workingQueue.size() > 1) {
            Queue<Item> q1 = workingQueue.dequeue();
            Queue<Item> q2 = workingQueue.dequeue();
            workingQueue.enqueue(mergeSortedQueues(q1, q2));
        }

        return workingQueue.dequeue();
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("ALice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("SHaowe");
        students.enqueue("Shaoyang");
        students.enqueue("Guagua");

        for (String stu : students) {
            System.out.print(stu + " ");
        }
        System.out.println("");
        Queue<String> sortedStudents = mergeSort(students);
        for(String stu : sortedStudents) {
            System.out.print(stu + " ");
        }
        System.out.println("");
    }
}
