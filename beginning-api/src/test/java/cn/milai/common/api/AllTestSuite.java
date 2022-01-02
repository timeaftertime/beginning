package cn.milai.common.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.common.api.data.JSONTest;
import cn.milai.common.api.generator.GeneratorTestSuite;
import cn.milai.common.api.map.MappersTest;

@RunWith(Suite.class)
@SuiteClasses(
	value = {
		MappersTest.class, RespTest.class, GeneratorTestSuite.class, JSONTest.class
	}
)
public class AllTestSuite {

}
