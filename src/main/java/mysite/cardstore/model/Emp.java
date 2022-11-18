package mysite.cardstore.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("t_emp")
public class Emp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableId
	private Integer empId;
	private String empName;
	private Integer empAge;
	private String empGender;
	private Integer deptId;
	@TableField(exist = false)
	private Dept dept; //員工對部門 多對一 對一 對應一個實體對象 對多 對應集合
	@TableField(exist = false)
	private String[] hobby;
	@TableField(exist = false)
	private Map<String, Teacher> teacherMap;
	
	public Emp() {
	}

	public Emp(Integer empId, String empName, Integer empAge, String empGender, Integer deptId, Dept dept,
			String[] hobby, Map<String, Teacher> teacherMap) {
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.empGender = empGender;
		this.deptId = deptId;
		this.dept = dept;
		this.hobby = hobby;
		this.teacherMap = teacherMap;
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", empGender=" + empGender
				+ ", deptId=" + deptId + ", dept=" + dept + ", hobby=" + Arrays.toString(hobby) + ", teacherMap="
				+ teacherMap + "]";
	}
	
	
	
}
