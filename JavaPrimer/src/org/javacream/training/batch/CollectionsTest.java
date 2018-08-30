package org.javacream.training.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class CollectionsTest {

	@Test
	public void testStringList() {
		List<String> names = new ArrayList<String>();
		names.add("Hugo");
		names.add("Emil");
		names.add("Hugo");
		Assert.assertTrue(names.size() == 3);
		
	}
	@Test
	public void testStringSet() {
		Set<String> names = new HashSet<String>();
		names.add("Hugo");
		names.add("Emil");
		names.add("Hugo");
		Assert.assertTrue(names.size() == 2);
		
	}

	@Test
	public void testDataList() {
		List<DemoData> names = new ArrayList<>();
		names.add(new DemoData("Hugo"));
		names.add(new DemoData("Emil"));
		names.add(new DemoData("Hugo"));
		Assert.assertTrue(names.size() == 3);
		
	}
	@Test
	public void testDataSet() {
		Set<DemoData> names = new HashSet<>();
		names.add(new DemoData("Hugo"));
		names.add(new DemoData("Emil"));
		names.add(new DemoData("Hugo"));
		Assert.assertTrue(names.size() == 2);
		
	}

	@Test void testMap() {
		Map<String, DemoData> map = new HashMap<>();
		map.put("Emil", new DemoData("Egal"));
		//..
		
	}
	
	class DemoData{
		private String data;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DemoData other = (DemoData) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (data == null) {
				if (other.data != null)
					return false;
			} else if (!data.equals(other.data))
				return false;
			return true;
		}

		public DemoData(String data) {
			super();
			this.data = data;
		}

		private CollectionsTest getOuterType() {
			return CollectionsTest.this;
		}
		
	}
}
