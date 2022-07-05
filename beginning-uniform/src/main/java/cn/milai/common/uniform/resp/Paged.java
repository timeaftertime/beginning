package cn.milai.common.uniform.resp;

import java.util.List;

/**
 * 分页
 * @author milai
 * @date 2022.04.09
 */
public class Paged<T> {

	private List<T> records;
	private long total;

	public Paged(List<T> records, long total) {
		this.records = records;
		this.total = total;
	}

	public List<T> getRecords() { return records; }

	public void setRecords(List<T> records) { this.records = records; }

	public long getTotal() { return total; }

	public void setTotal(long total) { this.total = total; }

}
