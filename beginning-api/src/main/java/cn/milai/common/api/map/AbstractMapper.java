package cn.milai.common.api.map;

import org.springframework.beans.BeanUtils;

/**
 * {@link Mapper} 抽象实现
 * @author milai
 * @date 2021.03.04
 */
public abstract class AbstractMapper<S, T> implements Mapper<S, T> {

	private Class<T> targetClass;

	public AbstractMapper(Class<S> sourceClass, Class<T> targetClass) {
		this.targetClass = targetClass;
	}

	@Override
	public T map(S source, String... ignores) {
		return map(source, BeanUtils.instantiateClass(targetClass), ignores);
	}

}
