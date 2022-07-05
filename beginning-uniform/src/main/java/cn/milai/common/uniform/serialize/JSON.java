package cn.milai.common.uniform.serialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.milai.common.collection.Creator;
import cn.milai.common.ex.unchecked.RethrownException;
import cn.milai.common.ex.unchecked.Uncheckeds;

/**
 * JSON 工具类，如发生异常，将通过 {@link RethrownException} 封装
 * @author milai
 * @date 2021.12.30
 */
public class JSON {

	private static final ObjectMapper OM = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	/**
	 * 将指定对象转换为 JSON 字符串
	 * @param o
	 * @return
	 */
	public static String write(Object o) {
		return Uncheckeds.rethrow(() -> OM.writeValueAsString(o));
	}

	/**
	 * 获取指定 key 和 对象创建的 JSON 字符串 {"key": writeJSON(o)}
	 * @param key
	 * @param o
	 * @return
	 */
	public static String write(String key, Object o) {
		return Uncheckeds.rethrow(() -> OM.writeValueAsString(Creator.hashMap(key, o)));
	}

	/**
	 * 解析指定 json 字符串，返回对应 {@link JsonNode}
	 * @param json
	 * @return
	 */
	public static JsonNode readTree(String json) {
		return Uncheckeds.rethrow(() -> OM.readTree(json));
	}

	/**
	 * 解析指定 json 字符串并绑定到指定类型的实例上
	 * @param <T>
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T read(String json, Class<T> c) {
		return Uncheckeds.rethrow(() -> OM.readValue(json, c));
	}

	/**
	 * 解析指定 node 对应的 json 字符串并绑定到指定类型的实例上。
	 * 若指定 {@link JsonNode} 为 <code>null</code>，返回 <code>null</code>
	 * @param <T>
	 * @param node
	 * @param c
	 * @return
	 */
	public static <T> T read(JsonNode node, Class<T> c) {
		if (node == null) {
			return null;
		}
		return read(node.toString(), c);
	}

	/**
	 * 解析指定  json 字符串并绑定到 {@link TypeReference} 代表的类型的实例上。
	 * 若指定 {@link JsonNode} 为 <code>null</code>，返回 <code>null</code>
	 * @param <T>
	 * @param node
	 * @param c
	 * @return
	 */
	public static <T> T read(String json, TypeReference<T> r) {
		return Uncheckeds.rethrow(() -> OM.readValue(json, r));
	}

	/**
	 * 解析指定 node 对应的 json 字符串并绑定到 {@link TypeReference} 代表的类型的实例上。
	 * @param <T>
	 * @param node
	 * @param r
	 * @return
	 */
	public static <T> T read(JsonNode node, TypeReference<T> r) {
		if (node == null) {
			return null;
		}
		return read(node.toString(), r);
	}

}
