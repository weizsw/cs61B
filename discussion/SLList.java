public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }
        IntNode currentNode = first;
        while (position > 1 && currentNode.next != null) {
            position--;
            currentNode = currentNode.next;
        }

        IntNode newNode = new IntNode(item, currentNode.next);
        currentNode.next = newNode;
    }

    public void reverse() {
        IntNode prev = null;
        IntNode cur = first;

        while (cur != null) {
            IntNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        first = prev;

    }
    public static void main(String[] args) {
        SLList L = new SLList();
        L.addFirst(2);
        L.addFirst(6);
        L.addFirst(5);
        L.insert(10,1);
        L.reverse();
        while(L.first != null) {
            System.out.println(L.first.item);
            L.first = L.first.next;
        }

    }
}