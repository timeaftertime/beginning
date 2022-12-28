package cn.milai.common.uniform.req;

/**
 * 分页请求
 * @author milai
 * @date 2022.10.16
 */
public class Page {

	private int page = 0;
	private int pageSize = 10;

	public Page() {
	}

	public Page(int page, int pageSize) {
		setPage(page);
		setPageSize(pageSize);
	}

	public void setPage(int page) {
		if (page > 0) {
			this.page = page;
		}
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getPage() { return page; }

	public int getPageSize() { return pageSize; }

	public int getStart() { return (page - 1) * pageSize; }

	public int getEnd() { return page * pageSize; }
}
