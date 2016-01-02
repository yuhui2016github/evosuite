/**
 * Copyright (C) 2010-2015 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 *
 * This file is part of EvoSuite.
 *
 * EvoSuite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser Public License as published by the
 * Free Software Foundation, either version 3.0 of the License, or (at your
 * option) any later version.
 *
 * EvoSuite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License along
 * with EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * 
 */
package org.evosuite.junit;

import org.evosuite.Properties;
import org.evosuite.coverage.method.JUnitObserver;
import org.evosuite.testcase.execution.ExecutionTracer;
import org.evosuite.utils.LoggingUtils;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * JUnitRunListener class
 * </p>
 * 
 * @author José Campos
 */
public class JUnitRunListener extends RunListener {

	/**
	 * 
	 */
	private JUnitRunner junitRunner = null;

	/**
	 * 
	 */
	private JUnitResult testResult = null;

	/**
	 * 
	 */
	private long start;

	/** Determine if we need to collect data on method calls, e.g. for output coverage */
	private boolean requiresMethodObserver = false;

	/**
	 * 
	 * @param jR
	 */
	public JUnitRunListener(JUnitRunner jR) {
		this.junitRunner = jR;
		List<Properties.Criterion> criteria = Arrays.asList(Properties.CRITERION);
		if(criteria.contains(Properties.Criterion.INPUT) ||
				criteria.contains(Properties.Criterion.OUTPUT) ||
				criteria.contains(Properties.Criterion.METHOD) ||
				criteria.contains(Properties.Criterion.METHODNOEXCEPTION))
			requiresMethodObserver = true;
	}

	/**
	 * Called before any tests have been run
	 */
	@Override
	public void testRunStarted(Description description) {
		LoggingUtils.getEvoLogger().info("* Number of test cases to execute: " + description.testCount());
	}

	/**
	 * Called when all tests have finished
	 */
	@Override
	public void testRunFinished(Result result) {
		LoggingUtils.getEvoLogger().info("* Number of test cases executed: " + result.getRunCount());
	}

	/**
	 * Called when an atomic test is about to be started
	 */
	@Override
	public void testStarted(Description description) {
		LoggingUtils.getEvoLogger().info("* Started: " + "ClassName: " + description.getClassName() + ", MethodName: " + description.getMethodName());

		this.start = System.nanoTime();

		this.testResult = new JUnitResult(description.getClassName() + "#" + description.getMethodName(), this.junitRunner.getJUnitClass());
		if(requiresMethodObserver) {
			JUnitObserver.getInstance().setEnabled(true);
		}
	}

	/**
	 * Called when an atomic test has finished. whether the test successes or fails
	 */
	@Override
	public void testFinished(Description description) {
		LoggingUtils.getEvoLogger().info("* Finished: " + "ClassName: " + description.getClassName() + ", MethodName: " + description.getMethodName());

		this.testResult.setRuntime(System.nanoTime() - this.start);
		this.testResult.setExecutionTrace(ExecutionTracer.getExecutionTracer().getTrace());
		this.testResult.incrementRunCount();
		ExecutionTracer.getExecutionTracer().clear();

		if(requiresMethodObserver) {
			JUnitObserver.getInstance().setEnabled(false);
            this.testResult.addInputGoals(JUnitObserver.getInstance().getInputCoverageGoals());
            this.testResult.addOutputGoals(JUnitObserver.getInstance().getOutputCoverageGoals());
            this.testResult.addMethodGoals(JUnitObserver.getInstance().getCoveredMethodGoals());
            this.testResult.addMethodNoExceptionGoals(JUnitObserver.getInstance().getCoveredMethodNoExceptionGoals());
			JUnitObserver.getInstance().reset();
		}

		this.junitRunner.addResult(this.testResult);
	}

	/**
	 * Called when an atomic test fails
	 */
	@Override
	public void testFailure(Failure failure) {
		LoggingUtils.getEvoLogger().info("* Failure: " + failure.getMessage());
		for (StackTraceElement s : failure.getException().getStackTrace()) {
			LoggingUtils.getEvoLogger().info("   " + s.toString());
		}

		this.testResult.setSuccessful(false);
		this.testResult.setTrace(failure.getTrace());
		this.testResult.incrementFailureCount();
	}

	/**
	 * Called when a test will not be run, generally because a test method is annotated with Ignore
	 */
	@Override
	public void testIgnored(Description description) throws java.lang.Exception {
		LoggingUtils.getEvoLogger().info("* Ignored: " + "ClassName: " + description.getClassName() + ", MethodName: " + description.getMethodName());
	}
}
