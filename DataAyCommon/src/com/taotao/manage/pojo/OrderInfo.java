package com.taotao.manage.pojo;

import java.util.Date;

public class OrderInfo {
    private String id;

    private String orderNumber;

    private String picPath;

    private Integer state;

    private String buyUser;

    private String sellUser;

    private String admin;

    private Date createTime;

    private Date updatetime;

    private Integer num;

    private String buyCompanyName;

    private String buyContacts;

    private String buyContactName;

    private String buyAddress;

    private String detail;

    private String adminAckTracking;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser == null ? null : buyUser.trim();
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser == null ? null : sellUser.trim();
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin == null ? null : admin.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBuyCompanyName() {
        return buyCompanyName;
    }

    public void setBuyCompanyName(String buyCompanyName) {
        this.buyCompanyName = buyCompanyName == null ? null : buyCompanyName.trim();
    }

    public String getBuyContacts() {
        return buyContacts;
    }

    public void setBuyContacts(String buyContacts) {
        this.buyContacts = buyContacts == null ? null : buyContacts.trim();
    }

    public String getBuyContactName() {
        return buyContactName;
    }

    public void setBuyContactName(String buyContactName) {
        this.buyContactName = buyContactName == null ? null : buyContactName.trim();
    }

    public String getBuyAddress() {
        return buyAddress;
    }

    public void setBuyAddress(String buyAddress) {
        this.buyAddress = buyAddress == null ? null : buyAddress.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getAdminAckTracking() {
        return adminAckTracking;
    }

    public void setAdminAckTracking(String adminAckTracking) {
        this.adminAckTracking = adminAckTracking == null ? null : adminAckTracking.trim();
    }
}