package com.gzs.learn.algorithm;

import java.util.Random;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class SqrtExample {
	static final int LOOP_TIMES = 1000;
	static final double eps = 0.00000000001;

	@Test
	public void testSqrt() {
		Random random = new Random();
		Stopwatch stopwatch = Stopwatch.createStarted();
		for (int i = 0; i < LOOP_TIMES; i++) {
			double d = random.nextDouble() * LOOP_TIMES;
			Math.sqrt(d);
		}
		stopwatch.stop();
		System.out.println("system sqrt cost:" + stopwatch.toString());
	}

	@Test
	public void testNewtonIterator() {
		Random random = new Random();
		Stopwatch stopwatch = Stopwatch.createStarted();
		for (int i = 0; i < LOOP_TIMES; i++) {
			double d = random.nextDouble() * LOOP_TIMES;
			newtonIterator(d, eps);
		}
		stopwatch.stop();
		System.out.println("newton iterator sqrt cost:" + stopwatch.toString());
	}

	@Test
	public void testNewtonRecursion() {
		Random random = new Random();
		Stopwatch stopwatch = Stopwatch.createStarted();

		for (int i = 0; i < LOOP_TIMES; i++) {
			double d = random.nextDouble() * LOOP_TIMES;
			newtonRecursion(0, 1, d, eps);
		}
		stopwatch.stop();
		System.out.println("newton iterator(recursion) sqrt cost:" + stopwatch.toString());
	}

	@Test
	public void testBinaryIterator() {
		Random random = new Random();
		Stopwatch stopwatch = Stopwatch.createStarted();
		for (int i = 0; i < LOOP_TIMES; i++) {
			double d = random.nextDouble() * LOOP_TIMES;
			binaryIterator(d, eps);
		}
		stopwatch.stop();
		System.out.println("binary iterator sqrt cost:" + stopwatch.toString());
	}

	private static double newtonIterator(double a, double eps) {
		// guess the first value
		double guessVal = a / 2;
		double lastVal = 0;
		do {
			lastVal = guessVal;
			// guest iterator value
			guessVal = (guessVal + a / guessVal) / 2;
		} while (Math.abs(guessVal - lastVal) > eps);
		return guessVal;
	}

	private static double newtonRecursion(double lastResult, double guessVal, double sqrt, double eps) {
		if (Math.abs(guessVal - lastResult) < eps) {
			return guessVal;
		}
		lastResult = guessVal;
		guessVal = (sqrt / lastResult + lastResult) / 2;
		return newtonRecursion(lastResult, guessVal, sqrt, eps);
	}

	private static double binaryIterator(double a, double eps) {
		if (a < 0) {
			throw new IllegalArgumentException("paramter must be greater than 0");
		}
		if (a == 0) {
			return 0;
		}
		if (a > 1) {
			double low = 0, high = a;
			double mid = (low + high) / 2;
			double last = 0;
			do {
				last = mid * mid;
				if (last == a) {
					return mid;
				}
				if (last < a) {
					low = mid;
				} else {
					high = mid;
				}
				mid = (low + high) / 2;
			} while (Math.abs(last - a) > eps);
			return mid;
		} else {
			double low = a, high = 1;
			double mid = (low + high) / 2;
			double last = 0;
			do {
				last = mid * mid;
				if (last == a) {
					return mid;
				}
				if (last < a) {
					low = mid;
				} else {
					high = mid;
				}
				mid = (low + high) / 2;
			} while (Math.abs(last - a) > eps);
			return mid;
		}
	}
}
