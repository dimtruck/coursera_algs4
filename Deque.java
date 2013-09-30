import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    
   public Deque()                     // construct an empty deque
   {
       first = null;
       last = null;
   }
   public boolean isEmpty()           // is the deque empty?
   {
       return first == null;
   }
   public int size()                  // return the number of items on the deque
   {
       return 0;
   }
   public void addFirst(Item item)    // insert the item at the front
   {
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.next = oldfirst;
   }
   
   public void addLast(Item item)     // insert the item at the end
   {
       Node oldlast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       if(isEmpty()) first = last;
       else oldlast.next = last;
   }
   
   public Item removeFirst()          // delete and return the item at the front
   {
       Item item = first.item;
       first = first.next;
       if(isEmpty()) last = null;
       return item;
   }
   public Item removeLast()           // delete and return the item at the end
   {
       Item item = first.item;
       first = first.next;
       return item;
   }
   public Iterator<Item> iterator()   // return an iterator over items in order from front to end
   {
       return new DequeIterator();
   }
   
   private class DequeIterator implements Iterator<Item>{
       private Node current = first;
       
       public boolean hasNext() { return current != null; }
       public void remove() { throw new UnsupportedOperationException();}
       public Item next(){
           Item item = current.item;
           current = current.next;
           return item;
       }
       
   }
   
   private class Node {
       Item item;
       Node next;
       Node prev;
   }
       
}