package model.data_structures;

import java.util.Iterator;

public class Queue <T> implements IQueue<T>
{

	// Nodo anterior 
	Nodo<T> nodoAnterior;

	// Nodo siguiente
	Nodo <T> nodoSiguiente;

	// Primer nodo
	Nodo<T> primero;

	// Último nodo
	Nodo<T> ultimo;

	private int tamaño;



	public Queue() 
	{
		// TODO Auto-generated constructor stub
		tamaño = 0;
		primero = null;
		ultimo = null;
	}

	@Override
	public Iterator <T> iterator() 
	{
		// TODO Auto-generated method stub
		Iterator <T> i = null;

		Nodo<T> act = primero;

		while (act != null){
			act = (Nodo<T>) primero.darSiguiente();
			i = (  (Iterator<T>) act );
		}
		return i;
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		return primero == null;
	}


	public int size() 
	{
		return tamaño;
	}



	public void enqueue(T t)
	{
		if(primero == null)
		{
			primero = new Nodo<T>(t);
			ultimo = primero;

		}
		else
		{
			ultimo.cambiarSiguiente(new Nodo<T>(t)); 
			ultimo = ultimo.darSiguiente();
		}
		tamaño++;
	
	}

	public T darUltimo()
	{
		return ultimo.darElemento();
	}


	public T dequeue()
	{
		Nodo<T> sacada = primero;

		primero = primero.darSiguiente();
		tamaño--;
		return  sacada.darElemento();
	}
}