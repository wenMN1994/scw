package com.dragon.scw.project.bean;

public class TProjectImages {
    private Integer id;

    private Integer projectid;

    private String imgurl;

    private Byte imgtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Byte getImgtype() {
        return imgtype;
    }

    public void setImgtype(Byte imgtype) {
        this.imgtype = imgtype;
    }
}