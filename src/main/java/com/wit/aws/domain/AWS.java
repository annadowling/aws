package com.wit.aws.domain;/***************************************************************
 * Copyright (c) 2018 Errigal Inc.
 *
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 *
 *************************************************************** */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by annadowling on 07/10/2018.
 */

@Entity
public class AWS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serviceName;
    private String category;

    public AWS() {
    }

    public AWS(String serviceName, String category) {
        this.serviceName = serviceName;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
