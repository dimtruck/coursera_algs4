import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private Item[] queue;
    private int size;
    
   public RandomizedQueue()           // construct an empty randomized queue
   {
       queue = (Item[]) new Object[1];
   }
   public boolean isEmpty()           // is the queue empty?
   {
       return queue.length == 0;
   }
   public int size()                  // return the number of items on the queue
   {
       return size;
   }
   public void enqueue(Item item)     // add the item
   {
       if (item == null) throw new java.lang.NullPointerException();
       if (size + 1 > queue.length) {
           resizeArray(2 * queue.length);
       }
       queue[size++] = item;
   }
   public Item dequeue()              // delete and return a random item
   {
       if (isEmpty()) throw new java.util.NoSuchElementException();
       int i = StdRandom.uniform(size);
       Item item = getSample(i);
       queue[i] = queue[--size];
       queue[size] = null;
       if (size > 0 && queue.length / 4 == size) {
           resizeArray(queue.length / 2);
       }
       
       return item;
   }
   public Item sample()               // return (but do not delete) a random item
   {
       if(isEmpty())  throw new java.util.NoSuchElementException();
       int i = StdRandom.uniform(size);
       return getSample(i);
   }
   
   private Item getSample(int i)
   {
       
       return queue[i];
   }
   
   public Iterator<Item> iterator()   // return an iterator in random order
   {
       return new QueueIterator();
   }
   
   private class QueueIterator implements Iterator<Item>
   {
       private int i = 0;
       private Item[] queue = (Item[]) new Object[size];
       
       public QueueIterator()
       {
           StdRandom.shuffle(queue);
       }
       
       public boolean hasNext() 
       { 
           if (i >= queue.length) throw new java.util.NoSuchElementException();
           return i < queue.length; 
       }
       public void remove() { throw new UnsupportedOperationException(); }
       public Item next()
       {
           return queue[i++];
       }
       
   }
   
   private void resizeArray(int newSize)
   {
       Item[] copy = (Item[]) new Object[newSize];
       for (int i = 0; i < size; i++) {
           copy[i] = queue[i];
       }
       queue = copy;
   }
}
