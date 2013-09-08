package org.archeng.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PlayerCharacterTest {

    private static final Logger logger = LoggerFactory.getLogger(PlayerCharacterTest.class);
    
    @Test
    public void testIconRelationships() {
        
        Icon icon1 = Icon.builder().id("id1").name("name1").nature(Icon.Nature.VILLANOUS).build();
        Icon icon2 = Icon.builder().id("id2").name("name2").nature(Icon.Nature.AMBIGIOUS).build();
        
        List<IconRelationship> relationships = Arrays.asList(
            IconRelationship.builder().icon(icon1).nature(IconRelationship.Nature.POSITIVE).points(1).build(),
            IconRelationship.builder().icon(icon2).nature(IconRelationship.Nature.CONFLICTED).points(3).build()
        );
        
        CharacterClass characterClass = new CharacterClass();
        
        PlayerCharacter pc = PlayerCharacter.builder().id("id1").name("name1").characterClass(characterClass).iconRelationships(relationships).build();
        
        assertThat(pc, is(not(nullValue())));
        
        Gson gson = new Gson();
        String json = gson.toJson(pc);
        logger.debug(json);
        
        pc = gson.fromJson(json, PlayerCharacter.class);
        assertThat(pc, is(not(nullValue())));
        logger.debug(pc.toString());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalIconRelationshipPoints() {
        Icon icon1 = Icon.builder().id("id1").name("name1").nature(Icon.Nature.VILLANOUS).build();
        IconRelationship.builder().icon(icon1).nature(IconRelationship.Nature.POSITIVE).points(2).build();   
    }
    
}
