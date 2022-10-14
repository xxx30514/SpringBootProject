package mysite.cardstore.admin.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public class Emp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer empId;
	private String empName;
	private Integer empAge;
	private String empGender;
	private Dept dept; //員工對部門 多對一 對一 對應一個實體對象 對多 對應集合
	private String[] hobby;
	private Map<String, Teacher> teacherMap;
	
	public Emp() {
	}

	public Emp(Integer empId, String empName, Integer empAge, String empGender) {
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empGender = empGender;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpAge() {
		return empAge;
	}

	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}

	public String getEmpGender() {
		return empGender;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	
	
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	
	
	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	
	public Map<String, Teacher> getTeacherMap() {
		return teacherMap;
	}

	public void setTeacherMap(Map<String, Teacher> teacherMap) {
		this.teacherMap = teacherMap;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empGender=" + empGender
				+ ", dept=" + dept + ", hobby=" + Arrays.toString(hobby) + ", teacherMap=" + teacherMap + "]";
	}
	
	
	
}
