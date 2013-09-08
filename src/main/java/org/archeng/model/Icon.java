package org.archeng.model;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.*;

public final class Icon {

    public static enum Nature { HEROIC, AMBIGIOUS, VILLANOUS }
    
    private final String id;
    private final String name;
    private final Nature nature;
    private final String flavorText;
    
    private Icon(String id, String name, Nature nature, String flavorText) {
        
        checkArgument(!Strings.isNullOrEmpty(id), "Icon id cannot be empty");
        checkArgument(!Strings.isNullOrEmpty(name), "Icon name cannot be empty");
        
        this.nature = checkNotNull(nature, "Icon nature must be declared");
        this.id = id;
        this.name = name;
        this.flavorText = flavorText;
    }

    public final static class Builder {
        private String _id;
        private String _name;
        private Nature _nature;
        private String _flavorText;
        private Builder() {
            // hide default constructor
        }
        private static Builder from(Icon icon) {
            Builder builder = new Builder();
            builder._id = icon.id;
            builder._name = icon.name;
            builder._nature = icon.nature;
            builder._flavorText = icon.flavorText;
            return builder;
        }
        public Icon build() { return new Icon(_id, _name, _nature, _flavorText); }
        public Builder id(String id) { _id = id; return this; }
        public Builder name(String name) { _name = name; return this; }
        public Builder nature(Nature nature) { _nature = nature; return this; }
        public Builder flavorText(String flavorText) { _flavorText = flavorText; return this; }
    }
    
    public static Builder builder() { return new Builder(); }
    public static Builder from(Icon icon) { return Builder.from(icon); }
    
    public String id() { return id; }
    public String name() { return name; }
    public Nature nature() { return nature; }
    public String flavorText() { return flavorText; }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("id", id)
            .add("name", name)
            .add("nature", nature)
            .add("flavorText", flavorText)
            .toString();
    }
    
}
