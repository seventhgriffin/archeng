package org.archeng.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.*;

public class PlayerCharacter {

    private final String id;
    private final String name;
    private final CharacterClass characterClass;
    private final List<IconRelationship> iconRelationships;

    private PlayerCharacter(String id, String name, CharacterClass characterClass, List<IconRelationship> iconRelationships) {
        this.id = checkNotNull(id, "PlayerCharacter id must be specified");
        this.name = checkNotNull(name, "PlayerCharacter name must be specified");
        this.characterClass = checkNotNull(characterClass, "PlayerCharacter characterClass must be specified");
        this.iconRelationships = checkIconRelationships(iconRelationships);
    }
    
    public final static class Builder {
        private String _id;
        private String _name;
        private CharacterClass _characterClass;
        private List<IconRelationship> _iconRelationships;

        private Builder() {
            // hide default constructor
        }
        private static Builder from(PlayerCharacter playerCharacter) {
            Builder builder = new Builder();
            builder._id = playerCharacter.id;
            builder._name = playerCharacter.name;
            builder._characterClass = playerCharacter.characterClass;
            builder._iconRelationships = ImmutableList.copyOf(playerCharacter.iconRelationships);
            return builder;
        }
        public PlayerCharacter build() { return new PlayerCharacter(_id, _name, _characterClass, _iconRelationships); }
        public Builder id(String id) { _id = id; return this; }
        public Builder name(String name) { _name = name; return this; }
        public Builder characterClass(CharacterClass characterClass) { _characterClass = characterClass; return this; }
        public Builder iconRelationships(List<IconRelationship> iconRelationships) {
            _iconRelationships = ImmutableList.copyOf(iconRelationships);
            return this;
        }
    }
    
    public static Builder builder() { return new Builder(); }
    public static Builder from(PlayerCharacter playerCharacter) { return Builder.from(playerCharacter); }

    public String id() { return id; }
    public String name() { return name; }
    public CharacterClass characterClass() { return characterClass; }
    public List<IconRelationship> iconRelationships() { return iconRelationships; }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("characterClass", characterClass)
                .add("iconRelationships", iconRelationships)
                .toString();
    }
    
    public static List<IconRelationship> checkIconRelationships(List<IconRelationship> iconRelationships) {
        checkNotNull(iconRelationships, "PlayerCharacter iconRelationships must be specified");
        Set<Icon> icons = new HashSet<Icon>();
        for (IconRelationship iconRelationship : iconRelationships) {
            Icon icon = iconRelationship.icon();
            if (icons.contains(icon)) {
                throw new IllegalArgumentException("PlayerCharacter cannot have multiple IconRelationships with: " + icon.name());
            }
            icons.add(icon);
        }
        return ImmutableList.copyOf(iconRelationships);
    }
}
