package cn.milai.common.uniform.mapper;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 * {@link Mapper} 默认实现
 * @author milai
 * @date 2021.03.04
 */
public class BaseMapper<S, T> implements Mapper<S, T> {

	private Class<S> sourceClass;
	private Class<T> targetClass;

	private Map<String, Mapper<?, ?>> mappers = new HashMap<>();

	public BaseMapper(Class<S> sourceClass, Class<T> targetClass) {
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}

	@Override
	public Mapper<S, T> use(Mapper<?, ?> mapper) {
		mappers.put(Mapper.keyOf(mapper.sourceClass(), mapper.targetClass()), mapper);
		return this;
	}

	@Override
	public T map(S source) {
		return map(source, BeanUtils.instantiateClass(targetClass));
	}

	@Override
	public T map(S source, T target) {
		if (source == null) {
			return null;
		}
		for (PropertyDescriptor targetPd : BeanUtils.getPropertyDescriptors(target.getClass())) {
			PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
			if (sourcePd == null) {
				continue;
			}
			Class<?> targetClass = targetPd.getPropertyType();
			Class<?> sourceClass = sourcePd.getPropertyType();
			@SuppressWarnings("unchecked")
			Mapper<Object, Object> mapper = (Mapper<Object, Object>) mappers.get(
				Mapper.keyOf(sourceClass, targetClass)
			);
			Beans.copyField(source, target, targetPd.getName(), mapper == null ? null : mapper::map);
		}
		return target;
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
