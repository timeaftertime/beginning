package cn.milai.common.thread;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.common.thread.counter.CounterTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ CounterTestSuite.class, BlockConditionTest.class })
public class AllTestSuite {

}
