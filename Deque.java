import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{

    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    public Deque()
    {

    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return head == null;
    }

    // return the number of items on the deque
    public int size()
    {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        Node<Item> newNode = new Node<>(item);
        if (head == null)
        {
            tail = newNode;
        }
        Node<Item> currentNode = this.head;
        if (currentNode != null)
        {
            currentNode.prev = newNode;
        }
        newNode.next = currentNode;
        this.head = newNode;
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        Node<Item> newNode = new Node<>(item);
        if (tail == null)
        {
            head = newNode;
        }
        Node<Item> currentNode = this.tail;
        if (currentNode != null)
        {
            currentNode.next = newNode;
        }
        newNode.prev = currentNode;
        this.tail = newNode;
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (this.head == null)
        {
            throw new NoSuchElementException();
        }

        if (head.equals(tail))
        {
            Node<Item> currentTail = tail;
            head = tail = null;
            this.size--;
            return currentTail.value;
        }

        Node<Item> currentHead = this.head;
        if (currentHead.next != null)
        {
            currentHead.next.prev = null;
        }
        this.head = currentHead.next;
        this.size--;
        return currentHead.value;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (this.tail == null)
        {
            throw new NoSuchElementException();
        }

        if (tail.equals(head))
        {
            Node<Item> currentHead = tail;
            head = tail = null;
            this.size--;
            return currentHead.value;
        }

        Node<Item> currentTail = this.tail;
        if (currentTail.prev != null)
        {
            currentTail.prev.next = null;
        }
        this.tail = currentTail.prev;
        this.size--;
        return currentTail.value;
    }


    private static class Node<Item>
    {
        Item value;
        private Node<Item> next;
        private Node<Item> prev;

        public Node(Item value)
        {
            this.value = value;
        }
    }


    @Override
    public Iterator<Item> iterator()
    {
        return new Iterator<>()
        {
            Node<Item> currentHead = head;

            @Override
            public boolean hasNext()
            {
                return currentHead != null;
            }

            @Override
            public Item next()
            {
                if (currentHead == null)
                {
                    throw new NoSuchElementException();
                }
                Node<Item> current = currentHead;
                currentHead = currentHead.next;
                return current.value;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> queue = new Deque<>();
        for (int i = 0; i < 10; i++)
        {
            queue.addFirst(i);
            queue.addLast(i);
        }
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext())
        {
            iterator.next();
            iterator.next();
        }
        iterator.next();
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeFirst());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println(queue.removeLast());
        StdOut.println("Size: " + queue.size());
        StdOut.println("isEmpty: " + queue.isEmpty());
    }
}
