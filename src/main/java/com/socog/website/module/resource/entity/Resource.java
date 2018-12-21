package com.socog.website.module.resource.entity;


import com.socog.website.module.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author xinghao
 * @descreption 文档实体类
 * @date 2018/12/19
 */

@Entity
public class Resource extends BaseEntity {

    private String title;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(nullable = false)
    private String resourceURL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType resourceType;


    private String brief;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
