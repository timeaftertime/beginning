package cn.milai.common.uniform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.milai.common.uniform.domain.DomainRespTest;
import cn.milai.common.uniform.generator.GeneratorTestSuite;
import cn.milai.common.uniform.mapper.BaseMapperTest;
import cn.milai.common.uniform.mapper.MappersTest;
import cn.milai.common.uniform.resp.RespTest;
import cn.milai.common.uniform.serialize.JSONTest;

@RunWith(Suite.class)
@SuiteClasses(
	value = {
		BaseMapperTest.class, MappersTest.class, RespTest.class, GeneratorTestSuite.class, JSONTest.class,
		DomainRespTest.class
	}
)
public class AllTestSuite {

}
