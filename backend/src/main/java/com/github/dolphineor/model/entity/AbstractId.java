package com.github.dolphineor.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created on 2016-01-23.
 *
 * @author dolphineor
 */
@MappedSuperclass
public abstract class AbstractId {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
