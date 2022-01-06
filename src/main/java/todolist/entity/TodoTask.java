package todolist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "todotask", uniqueConstraints = { @UniqueConstraint(columnNames = { "WORKNAME" }) })
public class TodoTask {
	private long id;
	private String workName;
	private String startDate;
	private String endDate;
	private int status;

	public TodoTask(long id, String workName, String startDate, String endDate, int status) {
		this.id = id;
		this.workName = workName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public TodoTask() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WORKNAME", nullable = false)
	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	@Column(name = "STARTDATE", nullable = false)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "ENDDATE", nullable = false)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "STATUS", nullable = false)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}