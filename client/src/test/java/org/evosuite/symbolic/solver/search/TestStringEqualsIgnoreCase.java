package org.evosuite.symbolic.solver.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.evosuite.symbolic.TestCaseBuilder;
import org.evosuite.symbolic.expr.Comparator;
import org.evosuite.symbolic.expr.Constraint;
import org.evosuite.symbolic.expr.IntegerConstraint;
import org.evosuite.symbolic.expr.Operator;
import org.evosuite.symbolic.expr.StringConstraint;
import org.evosuite.symbolic.expr.bv.IntegerBinaryExpression;
import org.evosuite.symbolic.expr.bv.IntegerConstant;
import org.evosuite.symbolic.expr.bv.StringBinaryComparison;
import org.evosuite.symbolic.expr.bv.StringBinaryToIntegerExpression;
import org.evosuite.symbolic.expr.bv.StringUnaryToIntegerExpression;
import org.evosuite.symbolic.expr.str.StringBinaryExpression;
import org.evosuite.symbolic.expr.str.StringConstant;
import org.evosuite.symbolic.expr.str.StringVariable;
import org.evosuite.symbolic.solver.ConstraintSolverTimeoutException;
import org.evosuite.symbolic.solver.DefaultTestCaseConcolicExecutor;
import org.evosuite.symbolic.solver.Solver;
import org.evosuite.symbolic.solver.search.EvoSuiteSolver;
import org.evosuite.testcase.DefaultTestCase;
import org.evosuite.testcase.variable.VariableReference;
import org.junit.Test;

import com.examples.with.different.packagename.solver.TestCaseStringEqualsIgnoreCase;

public class TestStringEqualsIgnoreCase {

	@Test
	public void testStringEqualsIgnoreCase() throws SecurityException,
			NoSuchMethodException, ConstraintSolverTimeoutException {

		IntegerConstant zero = new IntegerConstant(0);
		StringVariable stringVar0 = new StringVariable("var0", "");
		StringConstant strConst = new StringConstant("bar");

		StringBinaryComparison cmp1 = new StringBinaryComparison(stringVar0,
				Operator.EQUALS, strConst, 0L);
		StringConstraint constr1 = new StringConstraint(cmp1, Comparator.EQ,
				zero);

		StringBinaryComparison cmp2 = new StringBinaryComparison(stringVar0,
				Operator.EQUALSIGNORECASE, strConst, 1L);
		StringConstraint constr2 = new StringConstraint(cmp2, Comparator.NE,
				zero);

		Collection<Constraint<?>> constraints = Arrays.<Constraint<?>> asList(
				constr1, constr2);

		EvoSuiteSolver solver = new EvoSuiteSolver();

		Map<String, Object> solution = solver.solve(constraints);
		assertNotNull(solution);
		String var0 = (String) solution.get("var0");

		assertNotNull(var0);
		assertTrue(!var0.equals("bar"));
		assertTrue(var0.equalsIgnoreCase("bar"));
	}

}