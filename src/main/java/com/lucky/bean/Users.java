package com.lucky.bean;


public class Users {

  private long userId;
  private String userNickName;
  private String userPassword;
  private String userRealName;
  private long userType;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserNickName() {
    return userNickName;
  }

  public void setUserNickName(String userNickName) {
    this.userNickName = userNickName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserRealName() {
    return userRealName;
  }

  public void setUserRealName(String userRealName) {
    this.userRealName = userRealName;
  }


  public long getUserType() {
    return userType;
  }

  public void setUserType(long userType) {
    this.userType = userType;
  }

    public Users() {
    }

    public Users(long userId, String userNickName, String userPassword, String userRealName, long userType) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userPassword = userPassword;
        this.userRealName = userRealName;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userType=" + userType +
                '}';
    }
}
