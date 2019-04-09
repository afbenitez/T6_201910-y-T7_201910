package model.data_structures;

import java.util.NoSuchElementException;

public class RedBlackTree<K extends Comparable<K>, V> {

	private static final boolean RED   = true;
	private static final boolean BLACK = false;

	private Node root;    

	private class Node 
	{
		private K key;           
		private V val;         
		private Node left, right;  
		private boolean color;     
		private int size;          

		public Node(K key, V val, boolean color, int size) 
		{
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}

	public RedBlackTree() 
	{
	}

	public int size() 
	{
		return size(root);
	}

	private int size(Node x) 
	{
		if (x == null) return 0;
		return x.size;
	} 

	public boolean isEmpty() 
	{
		return root == null;
	}

	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	private V get(Node x, K key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.val;
		}
		return null;
	}

	private Node getNode(Node x, K key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x;
		}
		return null;
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	public int getHeight(K key)
	{
		if(getNode(root,key)!=null)
			return height(root)-height(getNode(root,key));
		return -1;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	public void put(K key, V val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}

		root = put(root, key, val);
		root.color = BLACK;
	}

	private Node put(Node h, K key, V val) { 
		if (h == null) return new Node(key, val, RED, 1);

		int cmp = key.compareTo(h.key);
		if      (cmp < 0) h.left  = put(h.left,  key, val); 
		else if (cmp > 0) h.right = put(h.right, key, val); 
		else              h.val   = val;

		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	public K min() {
		if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
		return min(root).key;
	} 



	private Node min(Node x) { 
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 

	public K max() 
	{
		if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
		return max(root).key;
	} 

	private Node max(Node x) 
	{ 
		// assert x != null;
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 

	public boolean check() 
	{
		return isBST() && is23() && isBalanced();
	}

	private boolean isBST() {
		return isBST(root, null, null);
	}

	//Verifica que la llave mayor del subArbol izquierdo sea menor que el de la llave actual y que la llave mayor del subArbol derecho sea mayor que la llave actual
	private boolean isBST(Node x, K min, K max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	} 

	//Verifica que no hayan dos enlaces rojos a la izquierda consecutivos ni que haya uno rojo a la derecha
	private boolean is23() { return is23(root); }
	private boolean is23(Node x) {
		if (x == null) return true;
		if (isRed(x.right)) return false;
		if (x != root && isRed(x) && isRed(x.left))
			return false;
		return is23(x.left) && is23(x.right);
	} 

	// Verifica si todas las ramas tienen misma cantidad de enlaces negros
	private boolean isBalanced() { 
		int enlacesNegros = 0; 
		Node x = root;
		while (x != null) {
			if (!isRed(x)) enlacesNegros++;
			x = x.left;
		}
		return isBalanced(root, enlacesNegros);
	}

	// Verifica si todas las ramas tienen misma cantidad de enlaces negros
	private boolean isBalanced(Node x, int enlacesNegros) {
		if (x == null) return enlacesNegros == 0;
		if (!isRed(x)) enlacesNegros--;
		return isBalanced(x.left, enlacesNegros) && isBalanced(x.right, enlacesNegros);
	} 

	public Iterable<K> iKeys() {
		if (isEmpty()) return new Queue<K>();
		return keys(min(), max());
	}

	public Iterable<K> keys(K lo, K hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

		Queue<K> queue = new Queue<K>();
		// if (isEmpty() || lo.compareTo(hi) > 0) return queue;
		keys(root, queue, lo, hi);
		return queue;
	} 

	private void keys(Node x, Queue<K> queue, K lo, K hi) { 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0) keys(x.left, queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
		if (cmphi > 0) keys(x.right, queue, lo, hi); 
	} 

	public IteradorLista<K> keys()
	{
		return (IteradorLista<K>) iKeys().iterator();
	}

	public Iterable<V> values(K lo, K hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to values() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to values() is null");

		Queue<V> queue = new Queue<V>();
		// if (isEmpty() || lo.compareTo(hi) > 0) return queue;
		values(root, queue, lo, hi);
		return queue;
	} 

	private void values(Node x, Queue<V> queue, K lo, K hi) { 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0) values(x.left, queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.val); 
		if (cmphi > 0) values(x.right, queue, lo, hi); 
	} 

	public IteradorLista<V> valuesInRange(K init, K end)
	{
		return (IteradorLista<V>) values(init,end).iterator();
	}

	public IteradorLista<K> keysInRange(K init, K end)
	{
		return (IteradorLista<K>) keys(init,end).iterator();

	}

	private boolean isRed(Node x) 
	{
		if (x == null) return false;
		return x.color == RED;
	}

	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
	}

	private Node deleteMin(Node h) { 
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}

	public void deleteMax() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMax(root);
		if (!isEmpty()) root.color = BLACK;
	}

	private Node deleteMax(Node h) { 
		if (isRed(h.left))
			h = rotateRight(h);

		if (h.right == null)
			return null;

		if (!isRed(h.right) && !isRed(h.right.left))
			h = moveRedRight(h);

		h.right = deleteMax(h.right);

		return balance(h);
	}

	public void delete(K key) { 
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;

		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
	}

	private Node delete(Node h, K key) { 

		if (key.compareTo(h.key) < 0)  {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else {
			if (isRed(h.left))
				h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;

				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right, key);
		}
		return balance(h);
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private void flipColors(Node h) {

		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	private Node moveRedLeft(Node h) {

		flipColors(h);
		if (isRed(h.right.left)) { 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	private Node moveRedRight(Node h) 
	{

		flipColors(h);
		if (isRed(h.left.left)) { 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	private Node balance(Node h) {

		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}
}