package com.notes.notes_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {
    HiddenHttpMethodFilter hiddenHttpMethodFilter (){
        return new HiddenHttpMethodFilter();
    }
}
