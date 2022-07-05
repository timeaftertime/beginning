package cn.milai.common.uniform.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Response from domain object
 * @author milai
 * @date 2022.07.02
 */
public class DomainResp<T, E extends DomainEvent> {

	private T data;
	private List<? extends E> domainEvents = Collections.emptyList();

	/**
	 * Create {@link DomainResp} with specified {@link DomainEvent}
	 * @param <T>
	 * @param <E>
	 * @param data
	 * @param domainEvents
	 * @return
	 */
	@SafeVarargs
	public static <T, E extends DomainEvent> DomainResp<T, E> of(T data, E... domainEvents) {
		DomainResp<T, E> resp = new DomainResp<>();
		resp.data = data;
		resp.domainEvents = domainEvents.length == 0 ? Collections.emptyList() : Arrays.asList(domainEvents);
		return resp;
	}

	/**
	 * Create {@link DomainResp} with specified {@link DomainEvent} and set data as <code>null</code>
	 * @param <E>
	 * @param domainEvents
	 * @return
	 */
	@SafeVarargs
	public static <E extends DomainEvent> DomainResp<Void, E> of(E... domainEvents) {
		return of(null, domainEvents);
	}

	public T getData() { return data; }

	/**
	 * Get domain events which need to be published.
	 * @return
	 */
	public List<? extends E> getDomainEvents() { return Collections.unmodifiableList(domainEvents); }

}
