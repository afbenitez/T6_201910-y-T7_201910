package test;

import junit.framework.TestCase;
import model.data_structures.RedBlackTree;

public class TestRedBlackTree extends TestCase
{
	private RedBlackTree<String,Integer> prueba;

	public void setUp()
	{
		prueba=new RedBlackTree<String,Integer>();

		for(int i=2;i<10001;i++)
			prueba.put("b"+i, i-1);
		prueba.put("a", 0);
		 

	}

	public void testEmpty()
	{
		assertEquals(false,prueba.isEmpty());
	}
	
	public void testGetHeight()
	{
		assertEquals(15,prueba.getHeight("b2"));
		assertEquals(-1,prueba.getHeight("d5"));
	}
	
	public void testContains()
	{
		assertEquals(false,prueba.contains("C999"));
		assertEquals(true,prueba.contains("b999"));
	}
	
	public void testGet()
	{
		assertEquals((int)999, (int)prueba.get("b1000"));
	}

	public void testPut()
	{
		prueba.put("c201", 42);
		assertEquals((int)42,(int)prueba.get("c201"));
	}
	
	public void testHeight()
	{
		assertEquals("Error en la altura del arbol", 15, prueba.height());
	}
	
	public void testMax()
	{
		assertEquals("b9999",prueba.max());
	}

	public void testMin()
	{
		assertEquals("a",prueba.min());
	}
	
	public void testCheck()
	{
		assertEquals(true,prueba.check());
	}
	
}