package com.wit.aws.web.controllers;/***************************************************************
 * Copyright (c) 2018 Errigal Inc.
 *
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 *
 *************************************************************** */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by annadowling on 17/10/2018.
 */

@Controller
public class HomeController {

        @RequestMapping("/spring-aws")
        public String greeting() {
            return "forward:/index.html";
        }

}
