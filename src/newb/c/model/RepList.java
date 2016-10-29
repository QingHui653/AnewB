package newb.c.model;

public class RepList {
	
	private String result;
	
	private Long count;

	public RepList(String result, Long count) {
		super();
		this.result = result;
		this.count = count;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
