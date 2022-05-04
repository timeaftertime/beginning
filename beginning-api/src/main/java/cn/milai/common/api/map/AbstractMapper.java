package cn.milai.common.api.map;

import org.springframework.beans.BeanUtils;

/**
 * {@link Mapper} 抽象实现
 * @author milai
 * @date 2021.03.04
 */
public abstract class AbstractMapper<S, T> implements Mapper<S, T> {

	private Class<S> sourceClass;
	private Class<T> targetClass;

	public AbstractMapper(Class<S> sourceClass, Class<T> targetClass) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}

	@Override
	public final T map(S source) {
		return map(source, BeanUtils.instantiateClass(targetClass));
	}

	@Override
	public Class<S> sourceClass() {
		return sourceClass;
	}

	@Override
	public Class<T> targetClass() {
		return targetClass;
	}

}
