package cn.milai.common.thread.counter;

import cn.milai.common.thread.BlockCondition;

/**
 * 使用线程阻塞实现 {@link Counter#await()} 的 {@link DownCounter}
 * @author milai
 * @date 2021.03.09
 */
public class BlockDownCounter extends DownCounter {

	private BlockCondition condition = new BlockCondition(this::isMet, c -> toMet());

	public BlockDownCounter(long cnt) {
		super(cnt, c -> ((BlockDownCounter) c).condition.toMet());
	}

	@Override
	public void await() {
		condition.await();
	}

}
