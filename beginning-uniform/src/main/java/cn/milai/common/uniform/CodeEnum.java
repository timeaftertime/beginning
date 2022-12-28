package cn.milai.common.uniform;

/**
 * Enum which has a integer code and a string description.
 * @author milai
 * @date 2022.08.18
 */
public interface CodeEnum {

	/**
	 * Get the unique code of this {@link CodeEnum}
	 * @return
	 */
	int getCode();

	/**
	 * Get the description string of this {@link CodeEnum}
	 * @return
	 */
	String getDesc();

	/**
	 * Get the enum which'e {@link #getCode()} equals the specified code, if none, then return <code>null</code>.
	 * @param <T>
	 * @param e
	 * @param code
	 * @return
	 */
	static <T extends CodeEnum> T find(Class<T> e, int code) {
		for (T t : e.getEnumConstants()) {
			if (t.getCode() == code) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Get the enum which'e {@link #getCode()} equals the specified code, if none, then throws {@link IllegalArgumentException}
	 * @param <T>
	 * @param e
	 * @param code
	 * @return
	 */
	static <T extends CodeEnum> T of(Class<T> e, int code) {
		T find = find(e, code);
		if (find == null) {
			throw new IllegalArgumentException(
				String.format("Enum for code not found: enum = %s, code = %d", e.getName(), code)
			);
		}
		return find;
	}

}
