package com.evolve.datastruct;

/** An Abstract Data Type queue.
 * Stores items in the order that they were inserted.
 * */
public class Queue<T>
{
	private Node<T> head;
	private Node<T> tail;
	private int count;
	
	public int getCount() {
		return count;
	}

	/** Instantiates an empty queue.*/
	public Queue()
	{
		this.head = null;
		this.tail = null;
	}
	
	/** Adds an item to the queue
	 * @param item the item to be inserted*/
	public void enqueue(T item)
	{
		this.head = cons(item, head);
		if(tail == null)
		{
			this.tail = head;
		}
		count ++;
	}
	
	/** De-queues the next item in line. Note this operation is always O(n). If we're
	 * using this class a lot, we will want to consider a doubly linked list.
	 * @return the next item*/
	public T dequeue()
	{
		Node<T> prev = null;
		Node<T> current = head;
		while(current.next != null)
		{
			prev = current;
			current = current.next;
		}
		if(prev == null)
		{
			head = null;
		}
		tail = prev;
		if(tail != null)
			tail.next = null;
		count --;
		return current.item;
	}
	
	/** Peeks at the next item in the queue without removing it. Peek is O(1)*/
	public T peekNext()
	{
		return tail.item;
	}
	
	public T peekLast()
	{
		return head.item;
	}
	
	//makes a new node
	private Node<T> cons(T item, Node<T> next)
	{
		Node<T> newNode = new Node<T>(item, next);
		return newNode;
	}
	
}
