package mysite.cardstore.pojo;

public class Teacher {
	
	private Integer tid;
	private String tname;
	
	public Teacher() {
		System.out.println("生命周期1:bean實例化");
	}
	public Teacher(Integer tid, String tname) {
		this.tid = tid;
		this.tname = tname;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		System.out.println("生命周期2:bean依賴注入(屬性賦值)");
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + "]";
	}
	
	public void initMethod() {
		System.out.println("生命周期:初始化");
	}
	
	public void destroyMethod() {
		System.out.println("生命周期:銷毀");
	}
}
