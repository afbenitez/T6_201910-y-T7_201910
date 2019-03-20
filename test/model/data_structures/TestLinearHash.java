package model.data_structures;

import org.junit.Test;

import java.util.ArrayList;



import junit.framework.TestCase;

public class TestLinearHash extends TestCase
{
	LinearHash<String, String> tablaLinear;

	public void setUp()
	{
		tabla = new LinearHash<String, String>(0);
	
		for(int i = 0; i < 10000; i++)
		{
			tabla.put("Prueba"+i,"Hola"+i*10);
		}
		assertEquals(10000, tabla.size());
	
	}

	public void testGetLinear()
	{
		long startTime = System.nanoTime();
		for(int i = 0; i < tabla.size(); i++)
		{
			tabla.get("Prueba"+i); 
		}
		assertEquals("Hola100",tabla.get("Prueba10"));
		long endTime = System.nanoTime() - startTime;
		long promedio = endTime/tabla.size();
		System.out.println("Tiempo promedio: "+ promedio+"ns");
	}
}