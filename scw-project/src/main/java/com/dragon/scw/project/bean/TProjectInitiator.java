package com.dragon.scw.project.bean;

public class TProjectInitiator {
    private Integer id;

    private String selfintroduction;

    private String detailselfintroduction;

    private String telphone;

    private String hotline;

    private Integer projectid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelfintroduction() {
        return selfintroduction;
    }

    public void setSelfintroduction(String selfintroduction) {
        this.selfintroduction = selfintroduction == null ? null : selfintroduction.trim();
    }

    public String getDetailselfintroduction() {
        return detailselfintroduction;
    }

    public void setDetailselfintroduction(String detailselfintroduction) {
        this.detailselfintroduction = detailselfintroduction == null ? null : detailselfintroduction.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline == null ? null : hotline.trim();
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }
}