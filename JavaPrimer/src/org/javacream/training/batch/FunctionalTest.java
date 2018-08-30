package org.javacream.training.batch;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

	@Test
	public void testAlgorithms() {
		Map<String, Function<String, String>> stringTransformations = new HashMap<>();
		stringTransformations.put("upper", (String input) -> {return input.toUpperCase();});
		stringTransformations.put("lower", (String input) -> {return input.toLowerCase();});
		stringTransformations.put("reverse", (String input) -> {return new StringBuilder(input).reverse().toString();});
		
		
		
		String upperTransformed = stringTransformations.get("upper").apply("Hugo");
		String lowerTransformed = stringTransformations.get("lower").apply("Hugo");
		String reverseTransformed = stringTransformations.get("reverse").apply("Hugo");

		Assert.assertEquals("HUGO", upperTransformed);
		Assert.assertEquals("hugo", lowerTransformed);
		Assert.assertEquals("oguH", reverseTransformed);
		
		
	}
}
