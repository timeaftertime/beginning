package cn.milai.common.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 使用线程阻塞实现 {@link #await()} 的 {@link Condition}
 * @author milai
 * @date 2021.03.09
 */
public class BlockCondition implements Condition {

	private Supplier<Boolean> met;
	private Consumer<Condition> toMet;
	private List<Thread> waiting = new LinkedList<Thread>();

	/**
	 * 创建一个通过指定回调方法判断 {@link #isMet()} 和实现 {@link #toMet()} 的 {@link BlockCondition}
	 * @param met
	 * @param toMet
	 */
	public BlockCondition(Supplier<Boolean> met, Consumer<Condition> toMet) {
		this.met = met;
		this.toMet = toMet;
	}

	@Override
	public final boolean isMet() { return met.get(); }

	/**
	 * 等待到当前 {@link #isMet()} 返回 {@code true} 后从当前方法返回，返回时线程中断状态将被清除
	 */
	@Override
	public void await() {
		synchronized (waiting) {
			if (isMet()) {
				return;
			}
			waiting.add(Thread.currentThread());
		}
		while (!isMet()) {
			try {
				new CountDownLatch(1).await();
			} catch (InterruptedException e) {
				// 去除线程中断状态
			}
		}
	}

	/**
	 * 中断等待中的所有线程，此时若 {@link #isMet()} 返回 {@code false}，线程将继续进入等待
	 */
	protected void interruptWaitings() {
		synchronized (waiting) {
			for (Thread w : waiting) {
				w.interrupt();
			}
			waiting.clear();
		}
	}

	@Override
	public void toMet() {
		if (toMet != null) {
			toMet.accept(this);
		}
		interruptWaitings();
	}
}
