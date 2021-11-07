package cn.milai.common.decoupling;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.common.decoupling.map.MappersTest;

@RunWith(Suite.class)
@SuiteClasses(
	value = {
		MappersTest.class, RespTest.class
	}
)
public class AllTestSuite {

}
