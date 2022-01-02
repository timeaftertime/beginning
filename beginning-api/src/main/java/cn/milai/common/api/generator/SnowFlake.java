package cn.milai.common.api.generator;

/**
 * 雪花算法生成整数 id
 * @author milai
 * @date 2021.12.21
 */
public class SnowFlake implements LongGenerator {

	/**
	 * 一共可用的比特位
	 */
	private static final int AVAILABLE_BITS = 63;

	/**
	 * 默认开始时间 2021/12/22
	 */
	private static final long DEF_ZERO_TIME = 1640131200000L;

	/**
	 * 默认的时间微秒数占用比特位数
	 */
	private static final int DEF_TIME_BITS = 41;

	/**
	 * 默认的数据中心编号占用比特位数
	 */
	private static final int DEF_DATACENTER_BITS = 5;

	/**
	 * 默认的机器编号占用比特位数
	 */
	private static final int DEF_MACHINE_BITS = 5;

	/**
	 * 最后一次产生 id 时刻的微秒计数
	 */
	private long lastMillisecond;

	/**
	 * datacenterId 和 machineId 拼接而成 
	 */
	private long workerPart;

	/**
	 * 当前微秒内的序列号
	 */
	private long sequence;

	private long zeroTime;

	private int workerBits;
	private int sequenceBits;

	private int timeShift;
	private long timeMask;

	private static final long[] MAX_VALUES;

	static {
		MAX_VALUES = new long[AVAILABLE_BITS];
		for (int i = 1; i < AVAILABLE_BITS; i++) {
			MAX_VALUES[i] = MAX_VALUES[i - 1] << 1 | 1;
		}
	}

	public SnowFlake(long datacenter, long machine) {
		this(DEF_ZERO_TIME, datacenter, machine, DEF_TIME_BITS, DEF_DATACENTER_BITS, DEF_MACHINE_BITS);
	}

	public SnowFlake(long zeroTime, long datacenter, long machine, int timeBits, int datacenterBits, int machineBits) {
		int totalBits = (timeBits + datacenterBits + machineBits);
		if (totalBits >= AVAILABLE_BITS) {
			throw new IllegalArgumentException("超出 long 总比特位限制");
		}
		this.zeroTime = zeroTime;
		this.workerBits = datacenterBits + machineBits;
		this.sequenceBits = AVAILABLE_BITS - totalBits;

		this.workerPart = (datacenter << machineBits | machine) << this.sequenceBits;
		this.timeShift = this.workerBits + this.sequenceBits;

		long timeMask = 0;
		for (int i = 0; i < timeBits; i++) {
			timeMask = timeMask << 1 | 1;
		}
		this.timeMask = timeMask;
	}

	@Override
	public synchronized long next() {
		if (lastMillisecond == System.currentTimeMillis() && sequence >= MAX_VALUES[sequenceBits]) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
			sequence = 0;
		}
		lastMillisecond = System.currentTimeMillis();
		long res = (lastMillisecond - zeroTime) << timeShift | workerPart | sequence;
		sequence++;
		return res;
	}

	/**
	 * 获取指定 id 对应的创建时间(毫秒)
	 * @param id
	 * @return
	 */
	public long getTime(long id) {
		return ((id >> this.timeShift) & this.timeMask) + zeroTime;
	}

	public static class Builder {
		private long zeroTime = DEF_ZERO_TIME;
		private long datacenter;
		private long machine;
		private int timeBits;
		private int datacenterBits;
		private int machineBits;

		public SnowFlake build() {
			return new SnowFlake(
				zeroTime, this.datacenter, this.machine, this.timeBits, this.datacenterBits, this.machineBits
			);
		}

		public Builder zeroTime(long zeroTime) {
			this.zeroTime = zeroTime;
			return this;
		}

		public Builder datacenter(long datacenter) {
			checkPositive(datacenter);
			this.datacenter = datacenter;
			return this;
		}

		public Builder machine(long machine) {
			checkPositive(machine);
			this.machine = machine;
			return this;
		}

		public Builder timeBits(int timeBits) {
			checkPositive(timeBits);
			this.timeBits = timeBits;
			return this;
		}

		public Builder datacenterBits(int datacenterBits) {
			checkPositive(datacenterBits);
			this.datacenterBits = datacenterBits;
			return this;
		}

		public Builder machineBits(int machineBits) {
			checkPositive(machineBits);
			this.machineBits = machineBits;
			return this;
		}

		private static void checkPositive(long n) {
			if (n <= 0) {
				throw new IllegalArgumentException("参数必须大于 0: " + n);
			}
		}
	}

}
