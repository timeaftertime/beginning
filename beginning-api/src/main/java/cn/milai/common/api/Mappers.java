package cn.milai.common.api;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

/**
 * 模型属性复制工具类
 * @author milai
 * @date 2021.03.01
 */
public class Mappers {

	private Mappers() {}

	/**
	 * 浅复制，复制 {@code source} 所有 {@code ignores} 以外属性到 {@code target} ，并返回该 {@code target} 
	 * @param <T>
	 * @param source
	 * @param target
	 * @param ignores
	 * @return
	 */
	public static <T> T map(Object source, T target, String... ignores) {
		BeanUtils.copyProperties(source, target, ignores);
		return target;
	}

	/**
	 * 深度复制，使用 {@code targetClass} 的默认构造方法创建其实例，复制 {@code source} 所有属性深度到该实例，并返回该实例 
	 * @param <T>
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public static <T> T map(Object source, Class<T> targetClass) {
		return JSON.parseObject(JSON.toJSONString(source), targetClass);
	}

}
