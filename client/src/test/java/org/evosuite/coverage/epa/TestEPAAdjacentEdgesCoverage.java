package org.evosuite.coverage.epa;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.evosuite.Properties;
import org.evosuite.Properties.Criterion;
import org.evosuite.TestGenerationContext;
import org.evosuite.testcase.DefaultTestCase;
import org.evosuite.testcase.variable.VariableReference;
import org.evosuite.testsuite.TestSuiteChromosome;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.examples.with.different.packagename.epa.MiniBoundedStack;

public class TestEPAAdjacentEdgesCoverage extends TestEPATransitionCoverage {

	private static final String MINI_BOUNDED_STACK_EPA_XML = String.join(File.separator, System.getProperty("user.dir"),
			"src", "test", "resources", "epas", "MiniBoundedStack.xml");

	private int DEFAULT_TIMEOUT = Properties.TIMEOUT;

	@Before
	public void prepareTest() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		final File epaXMLFile = new File(MINI_BOUNDED_STACK_EPA_XML);
		Assume.assumeTrue(epaXMLFile.exists());
		Properties.EPA_XML_PATH = MINI_BOUNDED_STACK_EPA_XML;
		Properties.TIMEOUT = Integer.MAX_VALUE;
		EPAMonitor.reset();
	}

	@After
	public void tearDown() {
		Properties.EPA_XML_PATH = null;
		Properties.TIMEOUT = DEFAULT_TIMEOUT;
	}

	@Test
	public void testCoverage() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IOException, SAXException, ParserConfigurationException {
		Properties.TARGET_CLASS = MiniBoundedStack.class.getName();
		Properties.EPA_XML_PATH = MINI_BOUNDED_STACK_EPA_XML;
		Properties.CRITERION = new Properties.Criterion[] { Criterion.EPAADJACENTEDGES };

		DefaultTestCase test = createTestCase0();
		TestSuiteChromosome suite = new TestSuiteChromosome();
		suite.addTest(test);

		EPAAdjacentEdgesCoverageFactory factory = new EPAAdjacentEdgesCoverageFactory(EPAFactory.buildEPAOrError(Properties.EPA_XML_PATH));
		List<EPAAdjacentEdgesCoverageTestFitness> goals = factory.getCoverageGoals();
		EPA epa_automata = EPAFactory.buildEPA(Properties.EPA_XML_PATH);
		int numOfStates = epa_automata.getStates().size();
		int numOfActions = epa_automata.getActions().size();
		int maxNumOfEdges = numOfStates * numOfActions * numOfStates;
		int maxNumOfDepartingEdges = numOfActions * numOfStates;
		int numOfAdjacentEdgesGoals = (maxNumOfEdges * maxNumOfDepartingEdges) * 2;
		assertEquals(numOfAdjacentEdgesGoals, goals.size());

		EPAAdjacentEdgesCoverageSuiteFitness adjacentEdgesFitness = new EPAAdjacentEdgesCoverageSuiteFitness(Properties.EPA_XML_PATH);

		suite.addFitness(adjacentEdgesFitness);
		double suiteFitness = adjacentEdgesFitness.getFitness(suite);
		int expectedNumOfCoveredGoals = 3;
		int expectedSuiteFitness = numOfAdjacentEdgesGoals - expectedNumOfCoveredGoals;
		assertEquals(expectedSuiteFitness, suiteFitness, 0.000000001);
	}
	
	@Test
	public void testCoverage_1() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IOException, SAXException, ParserConfigurationException {
		Properties.TARGET_CLASS = MiniBoundedStack.class.getName();
		Properties.EPA_XML_PATH = MINI_BOUNDED_STACK_EPA_XML;
		Properties.CRITERION = new Properties.Criterion[] { Criterion.EPAADJACENTEDGES };

		DefaultTestCase test = createTestCase1();
		TestSuiteChromosome suite = new TestSuiteChromosome();
		suite.addTest(test);

		EPAAdjacentEdgesCoverageFactory factory = new EPAAdjacentEdgesCoverageFactory(EPAFactory.buildEPAOrError(Properties.EPA_XML_PATH));
		List<EPAAdjacentEdgesCoverageTestFitness> goals = factory.getCoverageGoals();
		EPA epa_automata = EPAFactory.buildEPA(Properties.EPA_XML_PATH);
		int numOfStates = epa_automata.getStates().size();
		int numOfActions = epa_automata.getActions().size();
		int maxNumOfEdges = numOfStates * numOfActions * numOfStates;
		int maxNumOfDepartingEdges = numOfActions * numOfStates;
		int numOfAdjacentEdgesGoals = (maxNumOfEdges * maxNumOfDepartingEdges) * 2;
		assertEquals(numOfAdjacentEdgesGoals, goals.size());

		EPAAdjacentEdgesCoverageSuiteFitness adjacentEdgesFitness = new EPAAdjacentEdgesCoverageSuiteFitness(Properties.EPA_XML_PATH);

		suite.addFitness(adjacentEdgesFitness);
		double suiteFitness = adjacentEdgesFitness.getFitness(suite);
		int expectedNumOfCoveredGoals = 3;
		int expectedSuiteFitness = numOfAdjacentEdgesGoals - expectedNumOfCoveredGoals;
		assertEquals(expectedSuiteFitness, suiteFitness, 0.000000001);
	}
	
	@Test
	public void testCoverage_2() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IOException, SAXException, ParserConfigurationException {
		Properties.TARGET_CLASS = MiniBoundedStack.class.getName();
		Properties.EPA_XML_PATH = MINI_BOUNDED_STACK_EPA_XML;
		Properties.CRITERION = new Properties.Criterion[] { Criterion.EPAADJACENTEDGES };

		DefaultTestCase test = createTestCase2();
		TestSuiteChromosome suite = new TestSuiteChromosome();
		suite.addTest(test);

		EPAAdjacentEdgesCoverageFactory factory = new EPAAdjacentEdgesCoverageFactory(EPAFactory.buildEPAOrError(Properties.EPA_XML_PATH));
		List<EPAAdjacentEdgesCoverageTestFitness> goals = factory.getCoverageGoals();
		EPA epa_automata = EPAFactory.buildEPA(Properties.EPA_XML_PATH);
		int numOfStates = epa_automata.getStates().size();
		int numOfActions = epa_automata.getActions().size();
		int maxNumOfEdges = numOfStates * numOfActions * numOfStates;
		int maxNumOfDepartingEdges = numOfActions * numOfStates;
		int numOfAdjacentEdgesGoals = (maxNumOfEdges * maxNumOfDepartingEdges) * 2;
		assertEquals(numOfAdjacentEdgesGoals, goals.size());

		EPAAdjacentEdgesCoverageSuiteFitness adjacentEdgesFitness = new EPAAdjacentEdgesCoverageSuiteFitness(Properties.EPA_XML_PATH);

		suite.addFitness(adjacentEdgesFitness);
		double suiteFitness = adjacentEdgesFitness.getFitness(suite);
		int expectedNumOfCoveredGoals = 2;
		int expectedSuiteFitness = numOfAdjacentEdgesGoals - expectedNumOfCoveredGoals;
		assertEquals(expectedSuiteFitness, suiteFitness, 0.000000001);
		
		
	}

	/**
	 * Builds the test case:
	 * 
	 * <code>
	 * stack = new BoundedStack(); 
	 * stack.push(10);
	 * stack.pop();
	 * stack.push(10);
	 * stack.pop();
	 * </code>
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	private DefaultTestCase createTestCase0() throws ClassNotFoundException, NoSuchMethodException {
		Class<?> clazz = TestGenerationContext.getInstance().getClassLoaderForSUT().loadClass(Properties.TARGET_CLASS);
		Constructor<?> constructor = clazz.getConstructor();
		EPATestCaseBuilder builder = new EPATestCaseBuilder();
		Method push_method = clazz.getMethod("push", int.class);
		Method pop_method = clazz.getMethod("pop");

		// int var0 = 10;
		VariableReference var0 = builder.addIntegerStatement(10);
		// var1 = new MiniBoundedStack()
		VariableReference var1 = builder.addConstructorStatement(constructor);
		// var1.push(var0)
		builder.addMethodStatement(var1, push_method, var0);
		// var1.pop()
		builder.addMethodStatement(var1, pop_method);
		// var1.push(var0)
		builder.addMethodStatement(var1, push_method, var0);
		// var1.pop()
		builder.addMethodStatement(var1, pop_method);

		DefaultTestCase test = builder.toTestCase();
		return test;
	}
	
	/**
	 * Builds the test case:
	 * 
	 * <code>
	 * stack = new BoundedStack(); 
	 * stack.push(10);
	 * stack.pop();
	 * stack.push(10);
	 * </code>
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	private DefaultTestCase createTestCase1() throws ClassNotFoundException, NoSuchMethodException {
		Class<?> clazz = TestGenerationContext.getInstance().getClassLoaderForSUT().loadClass(Properties.TARGET_CLASS);
		Constructor<?> constructor = clazz.getConstructor();
		EPATestCaseBuilder builder = new EPATestCaseBuilder();
		Method push_method = clazz.getMethod("push", int.class);
		Method pop_method = clazz.getMethod("pop");

		// int var0 = 10;
		VariableReference var0 = builder.addIntegerStatement(10);
		// var1 = new MiniBoundedStack()
		VariableReference var1 = builder.addConstructorStatement(constructor);
		// var1.push(var0)
		builder.addMethodStatement(var1, push_method, var0);
		// var1.pop()
		builder.addMethodStatement(var1, pop_method);
		// var1.push(var0)
		builder.addMethodStatement(var1, push_method, var0);

		DefaultTestCase test = builder.toTestCase();
		return test;
	}
	
	/**
	 * Builds the test case:
	 * 
	 * <code>
	 * stack = new BoundedStack(); 
	 * stack.push(10);
	 * stack.push(10);
	 * </code>
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 */
	private DefaultTestCase createTestCase2() throws ClassNotFoundException, NoSuchMethodException {
		Class<?> clazz = TestGenerationContext.getInstance().getClassLoaderForSUT().loadClass(Properties.TARGET_CLASS);
		Constructor<?> constructor = clazz.getConstructor();
		EPATestCaseBuilder builder = new EPATestCaseBuilder();
		Method push_method = clazz.getMethod("push", int.class);

		// int var0 = 10;
		VariableReference var0 = builder.addIntegerStatement(10);
		// var1 = new MiniBoundedStack()
		VariableReference var1 = builder.addConstructorStatement(constructor);
		// var1.push(var0)
		builder.addMethodStatement(var1, push_method, var0);
		// var1.push(var0)
		builder.addMethodStatement(var1, push_method, var0);

		DefaultTestCase test = builder.toTestCase();
		return test;
	}

}