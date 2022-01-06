package todolist.todotask;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class TodoTaskDto {

	private long id;

	@NotEmpty
	@Size(min = 2)
	private String workName;

	@NotEmpty
	@DateTimeFormat(pattern = "yyyyMMdd")
	private String startDate;

	@NotEmpty
	@DateTimeFormat(pattern = "yyyyMMdd")
	private String endDate;

	@NotEmpty
	@Size(max = 8)
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
