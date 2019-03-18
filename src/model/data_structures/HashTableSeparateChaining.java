package model.data_structures;

import java.util.ArrayList;




public class HashTableSeparateChaining {

	// Nodo de cadenas 
	class HashNode<K, V> 
	{ 
		K key; 
		V value; 

		// Referencia al siguiente nodo 
		HashNode<K, V> next; 

		// Constructor 
		public HashNode(K key, V value) 
		{ 
			this.key = key; 
			this.value = value; 
		} 
	} 

	// Clase para representar la tabla de hash
	class Map<K, V> 
	{ 
		// Arreglo de cadenas
		private ArrayList<HashNode<K, V>> arreglo; 

		// Capacidad actual del ArrayList
		private int capacidad; 

		// Tamaño actual del ArrayList 
		private int size; 

		// Constructor (Inicializa capacidad, tamaño y 
		// cadenas vacías. 
		public Map() 
		{ 
			arreglo = new ArrayList<>(); 
			capacidad = 10; 
			size = 0; 

			// Crea cadenas vacías
			for (int i = 0; i < capacidad; i++) 
				arreglo.add(null); 
		} 

		public int size() { return size; } 
		public boolean isEmpty() { return size() == 0; } 

		// Implementa la función de hash para encontrar los índices 
		// para una llave
		private int getBucketIndex(K key) 
		{ 
			int hashCode = key.hashCode(); 
			int index = hashCode % capacidad; 
			return index; 
		} 

		// Método para eliminar una llave dada
		public V remove(K key) 
		{ 
			// Applicar función de hash para encontrar el índice para una llave dada
			int bucketIndex = getBucketIndex(key); 

			// Obtener la cabeza de una cadena 
			HashNode<K, V> head = arreglo.get(bucketIndex); 

			// Buscar una llave en su cadena
			HashNode<K, V> prev = null; 
			while (head != null) 
			{ 
				 
				if (head.key.equals(key)) 
					break; 
				
				prev = head; 
				head = head.next; 
			} 

			if (head == null) 
				return null; 
			
			size--; 

			 
			if (prev != null) 
				prev.next = head.next; 
			else
				arreglo.set(bucketIndex, head.next); 

			return head.value; 
		} 

		// Retorna el valor para una llave
		public V get(K key) 
		{ 
			// Encuentra la cabeza de una cadena para una llave dada
			int bucketIndex = getBucketIndex(key); 
			HashNode<K, V> head = arreglo.get(bucketIndex); 

			// Busca la llave en la cadena
			while (head != null) 
			{ 
				if (head.key.equals(key)) 
					return head.value; 
				head = head.next; 
			} 

		
			return null; 
		} 

	
		public void add(K key, V value) 
		{ 
			
			int bucketIndex = getBucketIndex(key); 
			HashNode<K, V> head = arreglo.get(bucketIndex); 

	
			while (head != null) 
			{ 
				if (head.key.equals(key)) 
				{ 
					head.value = value; 
					return; 
				} 
				head = head.next; 
			} 

		
			size++; 
			head = arreglo.get(bucketIndex); 
			HashNode<K, V> newNode = new HashNode<K, V>(key, value); 
			newNode.next = head; 
			arreglo.set(bucketIndex, newNode); 

			// Si el factor de carga supera 5.0 entonces se
			// duplica el tamaño de la tabla de hash (rehash).
			if ((1.0*size)/capacidad >= 5.0) 
			{ 
				ArrayList<HashNode<K, V>> temp = arreglo; 
				arreglo = new ArrayList<>(); 
				capacidad = 2 * capacidad; 
				size = 0; 
				for (int i = 0; i < capacidad; i++) 
					arreglo.add(null); 

				for (HashNode<K, V> headNode : temp) 
				{ 
					while (headNode != null) 
					{ 
						add(headNode.key, headNode.value); 
						headNode = headNode.next; 
					} 
				} 
			} 
		} 

		// Driver method to test Map class 
//		public static void main(String[] args) 
//		{ 
//			Map<String, Integer>map = new Map<>(); 
//			map.add("this",1 ); 
//			map.add("coder",2 ); 
//			map.add("this",4 ); 
//			map.add("hi",5 ); 
//			System.out.println(map.size()); 
//			System.out.println(map.remove("this")); 
//			System.out.println(map.remove("this")); 
//			System.out.println(map.size()); 
//			System.out.println(map.isEmpty()); 
//		} 
	} 

}

