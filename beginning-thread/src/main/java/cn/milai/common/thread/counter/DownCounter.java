package cn.milai.common.thread.counter;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * 倒计时 {@link Counter}
 * @author milai
 * @date 2021.02.26
 */
public class DownCounter implements Counter {

	private final long INIT_VAL;
	private AtomicLong cnt;
	private Consumer<DownCounter> callback;

	/**
	 * 创建一个初始值为 {@code cnt} 的 {@link DownCounter}
	 * @param cnt
	 */
	public DownCounter(long cnt) {
		this(cnt, null);
	}

	/**
	 * 构造一个初始值为 {@code cnt} 且在计数第一次变为 0 时调用指定回调函数的 {@link DownCounter}。
	 * 当前 {@link DownCounter} 将作为回调的参数
	 * @param cnt
	 * @param callback
	 */
	public DownCounter(long cnt, Consumer<DownCounter> callback) {
		if (cnt <= 0) {
			throw new IllegalArgumentException("初始值必须大于 0");
		}
		INIT_VAL = cnt;
		this.cnt = new AtomicLong(INIT_VAL);
		this.callback = callback;
	}

	@Override
	public void count() {
		if (cnt.getAndUpdate(pre -> Math.max(0, pre - 1)) > 0) {
			if (isMet() && callback != null) {
				callback.accept(this);
			}
		}
	}

	@Override
	public long getCount() { return cnt.longValue(); }

	@Override
	public boolean isMet() { return getCount() == 0; }

	@Override
	public void reset() {
		cnt.set(INIT_VAL);
	}

	@Override
	public void toMet() {
		if (cnt.getAndUpdate(pre -> 0) > 0) {
			if (callback != null) {
				callback.accept(this);
			}
		}
	}

}
