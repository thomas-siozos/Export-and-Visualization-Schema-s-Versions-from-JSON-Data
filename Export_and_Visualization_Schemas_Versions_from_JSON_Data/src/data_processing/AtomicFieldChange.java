package data_processing;

public class AtomicFieldChange {
	
	private String key;
	private String value;
	private String parent;
	private String act;
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public String getParent() {
		return parent;
	}
	
	public void setAct(String act) {
		this.act = act;
	}
	
	public String getAct() {
		return act;
	}

}
