package todolist.todotask;

import java.util.List;

public class TodoTaskResponse {
	private List<TodoTaskDto> list;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;

	public List<TodoTaskDto> getList() {
		return this.list;
	}

	public List<TodoTaskDto> setList(List<TodoTaskDto> list) {
		return this.list = list;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return this.totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean getLast() {
		return this.last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
}
