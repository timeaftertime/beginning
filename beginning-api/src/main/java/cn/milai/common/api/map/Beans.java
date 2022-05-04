package cn.milai.common.api.map;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;

import cn.milai.common.base.Classes;

/**
 * Bean 工具类
 * @author milai
 * @date 2022.01.01
 */
public class Beans {

	private Beans() {
	}

	/**
	 * 复制 source 属性到默认构造方法创建的 targetClass 实例上，复制为浅复制且忽略 ignores 指定字段
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param targetClass
	 * @param ignores
	 * @return
	 */
	public static <S, T> T copy(S source, Class<T> targetClass, String... ignores) {
		return copy(source, instance(targetClass), ignores);
	}

	/**
	 * 复制 source 属性到 target上，复制为浅复制且忽略 ignores 指定字段
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param target
	 * @param ignores
	 * @return
	 */
	public static <S, T> T copy(S source, T target, String... ignores) {
		return copyProperties(source, target, ignoreList(ignores), false);
	}

	/**
	 * 复制 source 属性到默认构造方法创建的 targetClass 实例上，复制为深复制且忽略 ignores 指定字段
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param targetClass
	 * @param ignores
	 * @return
	 */
	public static <S, T> T deepCopy(S source, Class<T> targetClass, String... ignores) {
		return deepCopy(source, instance(targetClass), ignores);
	}

	/**
	 * 复制 source 属性到 target上，复制为深复制且忽略 ignores 指定字段
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param target
	 * @param ignores
	 * @return
	 */
	public static <S, T> T deepCopy(S source, T target, String... ignores) {
		return copyProperties(source, target, ignoreList(ignores), true);
	}

	private static List<String> ignoreList(String[] ignores) {
		return (ignores == null || ignores.length <= 0) ? null : Arrays.asList(ignores);
	}

	/**
	 * 复制对象
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param target
	 * @param ignores
	 * @param deep 是否需要递归深度复制
	 * @return
	 * @see BeanUtils#copyProperties(Object, Object, String...)
	 */
	private static <S, T> T copyProperties(S source, T target, List<String> ignores, boolean deep) {
		if (source == null) {
			return null;
		}
		for (PropertyDescriptor targetPd : BeanUtils.getPropertyDescriptors(target.getClass())) {
			if (ignores != null && ignores.contains(targetPd.getName())) {
				continue;
			}
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod == null) {
				continue;
			}
			copyField(source, target, targetPd.getName(), deep);
		}
		return target;
	}

	/**
	 * 从 source 复制指定字段到 target，返回是否复制成功
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param target
	 * @param field
	 * @param deep
	 * @return
	 */
	public static <S, T> boolean copyField(S source, T target, String field, boolean deep) {
		PropertyDescriptor targetPd = BeanUtils.getPropertyDescriptor(target.getClass(), field);
		Method writeMethod = targetPd.getWriteMethod();
		if (writeMethod == null) {
			return false;
		}
		PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
		if (sourcePd == null) {
			return false;
		}
		Method readMethod = sourcePd.getReadMethod();
		if (readMethod == null) {
			return false;
		}
		ResolvableType sourceResolvableType = ResolvableType.forMethodReturnType(readMethod);
		ResolvableType targetResolvableType = ResolvableType.forMethodParameter(writeMethod, 0);
		boolean isAssignable = (sourceResolvableType.hasUnresolvableGenerics() || targetResolvableType
			.hasUnresolvableGenerics() ? ClassUtils.isAssignable(
				writeMethod.getParameterTypes()[0], readMethod.getReturnType()
			) : targetResolvableType.isAssignableFrom(sourceResolvableType));

		if (!isAssignable) {
			return false;
		}
		try {
			if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
				readMethod.setAccessible(true);
			}
			Object value = readMethod.invoke(source);
			if (deep && !Classes.isSingle(value.getClass())) {
				value = copySelf(value);
			}
			if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
				writeMethod.setAccessible(true);
			}
			writeMethod.invoke(target, value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static <S, T> void copyField(S source, T target, String field) {
		copyField(source, target, field, false);
	}

	@SuppressWarnings("unchecked")
	private static <T> T copySelf(T o) {
		if (o == null) {
			return null;
		}
		if (Classes.isSingle(o.getClass())) {
			return o;
		}
		if (o instanceof Collection<?>) {
			Collection<?> c = (Collection<?>) o;
			return (T) copyCollection(c, instance(c.getClass()));
		}
		if (o instanceof Map<?, ?>) {
			Map<?, ?> m = (Map<?, ?>) o;
			return (T) copyMap(m, instance(m.getClass()));
		}
		return (T) copyProperties(o, instance(o.getClass()), null, true);
	}

	private static <T, C extends Collection<T>> C copyCollection(C source, C target) {
		for (T s : source) {
			target.add((T) copySelf(s));
		}
		return target;
	}

	private static <K, V, M extends Map<K, V>> M copyMap(M source, M target) {
		for (K k : source.keySet()) {
			target.put((K) copySelf(k), (V) copySelf(source.get(k)));
		}
		return target;
	}

	private static <T> T instance(Class<T> c) {
		return BeanUtils.instantiateClass(c);
	}
}
