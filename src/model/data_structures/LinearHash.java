package model.data_structures;
import java.util.Iterator;

public class LinearHash<K extends Comparable <K>, V> implements ITablaHash<K,V>
{
	public final static double FACTOR_CARGA=0.75;
	private int capacidad;
	private int parejas;
	private K[] keys;
	private V[] values;
	private int contadorReHash;

	public LinearHash(int m)
	{
		contadorReHash = 0;
		parejas = 0;
		capacidad = m;
		keys = (K[])new Comparable[capacidad];
		values = (V[])new Object[capacidad];
	}

	public int size()
	{
		return parejas;
	}

	@Override
	public void put(K key, V value) 
	{
		if(capacidad==0)
		{
			reHash();
		}
		if (key == null) throw new IllegalArgumentException("La llave es null");

		if (value == null)
		{
			delete(key);
			return;
		}

		int i;
		for ( i = hash(key); keys[i] != null; i = (i+1) % capacidad) 
		{
			if (keys[i].equals(key)) 
			{
				values[i] = value;
				return;
			}
		}
		keys[i] = key;
		values[i] = value;
		parejas++;

		if (darFactorDeCargaActual()>FACTOR_CARGA)
		{
			reHash();
		}
	}

	public int darRehashes()
	{
		return contadorReHash;
	}
	public double darFactorDeCargaActual()
	{
		if(capacidad!=0)
			return (double) parejas/ (double) capacidad;
		else
			return (double)0;
	}

	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % capacidad;
	}

	@Override
	public V get(K key) 
	{
		if (key == null) throw new IllegalArgumentException("La llave a obtener es nula");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % capacidad)
			if (keys[i].equals(key))
				return values[i];
		return null;
	}

	@Override
	public V delete(K key) {
		V retorno=null;
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		int i = hash(key);
		while (!key.equals(keys[i])) 
		{
			i = (i + 1) % capacidad;
		}

		// delete key and associated value
		keys[i] = null;
		retorno=values[i];
		values[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % capacidad;
		while (keys[i] != null) 
		{
			// delete keys[i] an vals[i] and reinsert
			K   keyToRehash = keys[i];
			V valToRehash = values[i];
			keys[i] = null;
			values[i] = null;
			parejas--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % capacidad;
		}
		parejas--;
		return retorno;
	}

	@Override
	public Iterable<K> keys() {
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < capacidad; i++)
			if (keys[i] != null) queue.enqueue(keys[i]);
		return queue;
	}

	public int darParejas()
	{
		return parejas;
	}

	@Override
	public void reHash() 
	{
		LinearHash<K, V> temp = new LinearHash<K, V>((2*capacidad)+1);
		for (int i = 0; i < capacidad; i++) 
		{
			if (keys[i] != null) 
			{
				temp.put(keys[i], values[i]);
			}
		}
		keys = temp.keys;
		values = temp.values;
		capacidad    = temp.capacidad;
		contadorReHash++;
	}
}