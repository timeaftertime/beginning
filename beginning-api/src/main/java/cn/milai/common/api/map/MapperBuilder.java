package cn.milai.common.api.map;

import java.util.ArrayList;
import java.util.List;

import cn.milai.beginning.collection.Merge;
import cn.milai.common.api.map.Mappers.IMapper;

/**
 * {@link Mapper} 构造器，用于构造多个 {@link Mapper} 顺序转换模型的 {@link Mapper}
 * @author milai
 * @date 2021.03.04
 */
public class MapperBuilder<S, T> {

	private Class<S> sourceClass;
	private Class<T> targetClass;

	/**
	 * 映射器列表
	 */
	private List<Mapper<S, T>> mappers;

	/**
	 * 创建一个新 {@link MapperBuilder}，其映射器列表为当前列表加上指定 {@code mapper}
	 * @param mapper
	 * @return
	 */
	public MapperBuilder<S, T> then(IMapper<S, T> mapper) {
		return new MapperBuilder<>(this, new AbstractMapper<S, T>(sourceClass, targetClass) {
			@Override
			public T map(S source, T target, String... ignores) {
				return mapper.map(source, target, ignores);
			}
		});
	}

	/**
	 * 创建一个新 {@link MapperBuilder}，其映射器列表为当前列表加上指定 {@code mapper}
	 * @param mapper
	 * @return
	 */
	public MapperBuilder<S, T> then(Mapper<S, T> mapper) {
		return new MapperBuilder<>(this, mapper);
	}

	/**
	 * 创建一个空转换器列表的 {@link MapperBuilder}
	 */
	public MapperBuilder(Class<S> sourceClass, Class<T> targetClass) {
		this(sourceClass, targetClass, new ArrayList<>());
	}

	private MapperBuilder(MapperBuilder<S, T> builder, Mapper<S, T> mapper) {
		this(builder.sourceClass, builder.targetClass, Merge.list(builder.mappers, mapper));
	}

	private MapperBuilder(Class<S> sourceClass, Class<T> targetClass, List<Mapper<S, T>> mappers) {
		this.mappers = mappers;
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}

	/**
	 * 以当前 {@link Mapper} 列表构造一个新 {@link Mapper} 实例
	 * @return
	 */
	public Mapper<S, T> build() {
		return new AbstractMapper<S, T>(sourceClass, targetClass) {
			@Override
			public T map(S source, T target, String... ignores) {
				for (Mapper<S, T> mapper : mappers) {
					target = mapper.map(source, target, ignores);
				}
				return target;
			}
		};
	}
}