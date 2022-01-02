package cn.milai.common.api.generator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SnowFlakeTest.class, TimeRandomIdGeneratorTest.class })
public class GeneratorTestSuite {

}
