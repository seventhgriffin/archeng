package org.archeng.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IconTest {
    
    private static final Logger logger = LoggerFactory.getLogger(IconTest.class);

    @Test
    public void testInstantiation() {
        Icon icon = Icon.builder()
                .id("myId")
                .name("myIcon")
                .build();
        
        
        Gson gson = new Gson();
        String json = gson.toJson(icon);
        logger.debug(json);
        
        icon = Icon.from(icon)
                .id("myOtherId")
                .build();
        
        json = gson.toJson(icon);
        logger.debug(json);
        
        icon = gson.fromJson(json, Icon.class);
        
        logger.debug(icon.toString());
        logger.debug(icon.id());
        
        assertThat(icon, is(not(nullValue())));
    }
    
}
