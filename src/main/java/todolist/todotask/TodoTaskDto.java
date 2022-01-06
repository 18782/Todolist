package todolist.todotask;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class TodoTaskDto {

	private long id;

	@NotEmpty
	@Size(min = 2)
	private String workName;

	@NotEmpty
	@Size(min = 8,max = 8)
	private String startDate;

	@NotEmpty
	@Size(min = 8,max = 8)
	private String endDate;

	@NotEmpty
	@Size(min = 1,max = 1)
	private int status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = startDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
