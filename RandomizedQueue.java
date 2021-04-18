import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item>
{

    private Item[] items;
    private static final int head = 0;
    private int tail = 0;
    private static final int INITIAL_SIZE = 5;

    public RandomizedQueue()
    {
        this.items = (Item[]) new Object[INITIAL_SIZE];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return head == tail;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return tail - head;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new NoSuchElementException();
        }
        if (head == tail && this.size() == 0)
        {
            if (this.items[head] == null)
            {
                this.items[tail] = item;
            }
        }
        else
        {
            reorderQueue();
            this.items[head] = item;
        }
        this.tail++;
    }

    private void reorderQueue()
    {
        for (int current = tail - 1; current >= head; current--)
        {
            if (current + 1 == this.items.length)
            {
                this.resizeQueue();
            }
            this.items[current + 1] = this.items[current];
        }
    }


    private void resizeQueue()
    {
        Item[] newArray = (Item[]) new Object[this.items.length * 2];
        System.arraycopy(this.items, 0, newArray, 0, this.items.length);
        this.items = newArray;
    }


    // remove and return a random item
    public Item dequeue()
    {
        if (this.isEmpty())
        {
            throw new NoSuchElementException("Queue is empty");
        }
        Item random = this.sample();
        if (this.items[head] == random)
        {
            Item[] newArray = (Item[]) new Object[this.size() - 1];
            System.arraycopy(this.items, 1, newArray, head, --tail);
            this.items = newArray;
        }
        else if (this.items[tail - 1] == random)
        {
            this.items[--tail] = null;
        }
        else
        {
            this.removeRandomItem(random);
        }

        return random;
    }

    private void removeRandomItem(Item random)
    {
        for (int i = 0; i < tail; i++)
        {
            if (this.items[i] == random)
            {
                Item[] newArray = (Item[]) new Object[this.size() - 1];
                System.arraycopy(this.items, 0, newArray, 0, i);
                System.arraycopy(this.items, i + 1, newArray, i, --tail - i);
                this.items = newArray;
                return;
            }
        }
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (this.isEmpty())
        {
            throw new NoSuchElementException("Queue is empty");
        }
        return this.items[StdRandom.uniform(this.size())];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        RandomizedQueue<Item> queue = new RandomizedQueue<>();
        for (Item item : items)
        {
            if (item != null)
            {
                queue.enqueue(item);
            }
        }
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return !queue.isEmpty();
            }

            @Override
            public Item next()
            {
                return queue.dequeue();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args)
    {

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();




        for (String s : queue)
        {
            for (String s1 : queue)
            {
                StdOut.println(s + "-" + s1);
            }

        }
    }
}
