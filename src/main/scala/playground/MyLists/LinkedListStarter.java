package playground.MyLists;


public class LinkedListStarter {
    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.add( new LinkedList.Node("1"));
        linkedList.add( new LinkedList.Node("2"));
        linkedList.add( new LinkedList.Node("3"));
        linkedList.add( new LinkedList.Node("4"));
        linkedList.add( new LinkedList.Node("5"));
        linkedList.add( new LinkedList.Node("6"));
        linkedList.add( new LinkedList.Node("7"));
        linkedList.add( new LinkedList.Node("8"));
        linkedList.add( new LinkedList.Node("9"));
        linkedList.add( new LinkedList.Node("10"));


        LinkedList.Node head    = linkedList.head();
        LinkedList.Node current = head;
        LinkedList.Node middle  = head;

        int length = 0;


        while(current.next() != null){

            length++;

            if(length % 5 == 0){
                middle = current;
            }

            current = current.next();

            if (current == middle) throw new Exception("Infinite recursive");
        }

        if (length % 2 == 1){
            middle = middle.next();
        }

        System.out.println("length of LinkedList: " + length);
        System.out.println("middle element of LinkedList : " + middle);
    }




    public void findMiddle(int counter, LinkedList linkedList, LinkedList.Node middle, LinkedList.Node elem) {
        if (elem.next() == null) System.out.println(middle);
        else if (counter % 2 == 0) System.out.println();
    }
}

class LinkedList {
    private Node head;
    private Node tail;

    public LinkedList() {
        this.head = new Node("head");
        tail = head;
    }

    public Node head() {
        return head;
    }

    public void add(Node node) {
        tail.next = node;
        tail = node;
    }

    public Node tail() {
        return tail;
    }

    public static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        public String data() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String toString() {
            return this.data;
        }
    }
}

