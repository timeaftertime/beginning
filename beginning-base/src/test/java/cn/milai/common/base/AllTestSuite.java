package cn.milai.common.base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	value = {
		DigestsTest.class, StringsTest.class, CharsTest.class, RandomsTest.class, ClassesTest.class, CollectsTest.class,
		ArrayTest.class, BytesTest.class, NamingsTest.class
	}
)
public class AllTestSuite {

}
