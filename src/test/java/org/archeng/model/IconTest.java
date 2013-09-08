package org.archeng.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IconTest {

    @Test
    public void testInstantiation() {
        Icon icon = new Icon();
        assertThat(icon, is(not(nullValue())));
    }
    
}
