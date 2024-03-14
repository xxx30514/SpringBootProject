package mysite.cardstore.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;

@TableName("t_user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId
  private Integer userId;
  private String userAccount;
  @JsonInclude(JsonInclude.Include.NON_NULL) // 為空時不生成json
  @NotBlank
  private String userPassword;
  private String userName;
  private String userNickname;
  @NotBlank
  private String userEmail;
  private LocalDateTime userCreatedate;
  private LocalDateTime userUpdatedate;
  private Integer userStatus;

  public User() {
  }

  public User(Integer userId, String userAccount, String userPassword, String userName, String userNickname,
      String userEmail, LocalDateTime userCreatedate, LocalDateTime userUpdatedate, Integer userStatus) {
    this.userId = userId;
    this.userAccount = userAccount;
    this.userPassword = userPassword;
    this.userName = userName;
    this.userNickname = userNickname;
    this.userEmail = userEmail;
    this.userCreatedate = userCreatedate;
    this.userUpdatedate = userUpdatedate;
    this.userStatus = userStatus;
  }

  public User(String userAccount, String userPassword, String userName, String userNickname, String userEmail,
      LocalDateTime userCreatedate, LocalDateTime userUpdatedate, Integer userStatus) {
    this.userAccount = userAccount;
    this.userPassword = userPassword;
    this.userName = userName;
    this.userNickname = userNickname;
    this.userEmail = userEmail;
    this.userCreatedate = userCreatedate;
    this.userUpdatedate = userUpdatedate;
    this.userStatus = userStatus;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserNickname() {
    return userNickname;
  }

  public void setUserNickname(String userNickname) {
    this.userNickname = userNickname;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public LocalDateTime getUserCreatedate() {
    return userCreatedate;
  }

  public void setUserCreatedate(LocalDateTime userCreatedate) {
    this.userCreatedate = userCreatedate;
  }

  public LocalDateTime getUserUpdatedate() {
    return userUpdatedate;
  }

  public void setUserUpdatedate(LocalDateTime userUpdatedate) {
    this.userUpdatedate = userUpdatedate;
  }

  public Integer getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  @Override
  public String toString() {
    return "User [userId=" + userId + ", userAccount=" + userAccount + ", userPassword=" + userPassword + ", userName="
        + userName + ", userNickname=" + userNickname + ", userEmail=" + userEmail + ", userCreatedate="
        + userCreatedate + ", userUpdatedate=" + userUpdatedate + ", userStatus=" + userStatus + "]";
  }

}
