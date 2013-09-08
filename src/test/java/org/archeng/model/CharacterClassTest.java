package org.archeng.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class CharacterClassTest {

    @Test
    public void testInstantiation() {
        CharacterClass characterClass = new CharacterClass();
        assertThat(characterClass, is(not(nullValue())));
    }
    
}
