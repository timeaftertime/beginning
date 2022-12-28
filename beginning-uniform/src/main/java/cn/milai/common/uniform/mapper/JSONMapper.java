package cn.milai.common.uniform.mapper;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.core.type.TypeReference;

import cn.milai.common.uniform.serialize.JSON;

/**
 * Util to build {@link Mapper} that convert between JSON and Class
 * @author milai
 * @date 2022.11.01
 */
public class JSONMapper {

	private JSONMapper() {
	}

	/**
	 * Create a {@link Mapper} which map json string to specified Class
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T> Mapper<String, T> to(Class<T> c) {
		return new BaseMapper<String, T>(String.class, c) {
			@Override
			public T map(String source) {
				return JSON.read(source, c);
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static <T> Mapper<String, T> to(TypeReference<T> c) {
		return new BaseMapper<String, T>(String.class, (Class<T>) ((ParameterizedType) c.getType()).getRawType()) {
			@Override
			public T map(String source) {
				return JSON.read(source, c);
			}
		};
	}

	public static <T> Mapper<T, String> from(Class<T> c) {
		return new BaseMapper<T, String>(c, String.class) {
			@Override
			public String map(T source) {
				return JSON.write(source);
			}
		};
	}
}
