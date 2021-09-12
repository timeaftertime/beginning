package cn.milai.beginning.collection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	value = {
		FilterTest.class, MergeTest.class, MappingTest.class
	}
)
public class AllTestSuite {

}
