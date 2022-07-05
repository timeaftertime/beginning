package cn.milai.common;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.common.base.BaseTestSuite;
import cn.milai.common.collection.CollectionTestSuite;

@RunWith(Suite.class)
@SuiteClasses(
	value = {
		BaseTestSuite.class, CollectionTestSuite.class
	}
)
public class AllTestSuite {

}
