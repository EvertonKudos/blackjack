package br.com.murah.blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {

		Map<Integer, String> mapaIdadeNome = new HashMap<Integer, String>();
		List<String> listaNome = new ArrayList<String>();

		listaNome.add("Lavinia");
		listaNome.add("Daniele");
		listaNome.add("Nicole");
		listaNome.add("Caroline");

		for (String nome : listaNome)
			if (nome.equals("Daniele"))
				System.out.println("achei ");

		mapaIdadeNome.put(18, "Lavinia");
		mapaIdadeNome.put(24, "Daniele");
		mapaIdadeNome.put(27, "Nicole");
		mapaIdadeNome.put(21, "Caroline");

		System.out.println(mapaIdadeNome.get(24));
		assertTrue(true);
	}
}
