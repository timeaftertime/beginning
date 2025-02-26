package cn.milai.common.collection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { FilterTest.class, MergeTest.class, MappingTest.class, CreatorTest.class, IntersectionTest.class,
		CollectionUtilsTest.class })
public class CollectionTestSuite {

}
