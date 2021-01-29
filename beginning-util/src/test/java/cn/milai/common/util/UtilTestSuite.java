package cn.milai.common.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	value = { DigestsTest.class, StringsTest.class, CharsTest.class, RandomsTest.class, ClassesTest.class,
		CollectionsTest.class }
)
public class UtilTestSuite {

}
