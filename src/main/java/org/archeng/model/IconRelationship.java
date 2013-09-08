package org.archeng.model;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;

import static com.google.common.base.Preconditions.*;

public final class IconRelationship {

    public static enum Nature { POSITIVE, CONFLICTED, NEGATIVE };
    
    private static Map<Icon.Nature, Map<IconRelationship.Nature, Integer>> MAX_POINTS_BY_NATURE = initializeMaxPointsByNature();
    
    private final Nature nature;
    private final Integer points;
    private final Icon icon;
    
    private IconRelationship(Icon icon, Nature nature, Integer points) {
        this.nature = checkNotNull(nature, "IconRelationship nature must be specified");
        this.points = checkNotNull(points, "IconRelationship points must be specified");
        this.icon = checkNotNull(icon, "IconRelationship icon must be specified");
        checkArgument(this.points > 0, "IconRelationship points must be greater than zero");
        checkPoints();
    }
    
    public final static class Builder {
        private Nature _nature;
        private Integer _points;
        private Icon _icon;
        private Builder() {
            // hide default constructor
        }
        private static Builder from(IconRelationship iconRelationship) {
            Builder builder = new Builder();
            builder._nature = iconRelationship.nature;
            builder._points = iconRelationship.points;
            builder._icon= iconRelationship.icon;
            return builder;
        }
        public IconRelationship build() { return new IconRelationship(_icon, _nature, _points); }
        public Builder nature(Nature nature) { _nature = nature; return this; }
        public Builder points(Integer points) { _points = points; return this; }
        public Builder icon(Icon icon) { _icon = icon; return this; }
    }
    
    public static Builder builder() { return new Builder(); }
    public static Builder from(IconRelationship iconRelationship) { return Builder.from(iconRelationship); }

    public Nature nature() { return nature; }
    public Integer points() { return points; }
    public Icon icon() { return icon; }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("icon", icon)
                .add("nature", nature)
                .add("points", points)
                .toString();
    }
    
    private void checkPoints() {
        Integer maxAllowed = MAX_POINTS_BY_NATURE.get(icon.nature()).get(nature);
        if (points > maxAllowed) {
            throw new IllegalArgumentException(String.format("IconRelationship cannot have more than %d points for a %s relationship with a %s icon",
                    maxAllowed, nature, icon.nature()));
        }
    }
    
    private static Map<Icon.Nature, Map<IconRelationship.Nature, Integer>> initializeMaxPointsByNature() {
        
        Map<Icon.Nature, Map<IconRelationship.Nature, Integer>> table = new HashMap<Icon.Nature, Map<IconRelationship.Nature, Integer>>();
        Map<IconRelationship.Nature, Integer> innerTable = new HashMap<IconRelationship.Nature, Integer>();
        
        innerTable.put(IconRelationship.Nature.POSITIVE, 3);
        innerTable.put(IconRelationship.Nature.CONFLICTED, 3);
        innerTable.put(IconRelationship.Nature.NEGATIVE, 1);
        table.put(Icon.Nature.HEROIC, innerTable);
        
        innerTable = new HashMap<IconRelationship.Nature, Integer>();
        innerTable.put(IconRelationship.Nature.POSITIVE, 3);
        innerTable.put(IconRelationship.Nature.CONFLICTED, 3);
        innerTable.put(IconRelationship.Nature.NEGATIVE, 2);
        table.put(Icon.Nature.AMBIGIOUS, innerTable);
        
        innerTable = new HashMap<IconRelationship.Nature, Integer>();
        innerTable.put(IconRelationship.Nature.POSITIVE, 1);
        innerTable.put(IconRelationship.Nature.CONFLICTED, 2);
        innerTable.put(IconRelationship.Nature.NEGATIVE, 2);
        table.put(Icon.Nature.VILLANOUS, innerTable);
        
        return ImmutableMap.copyOf(table);
    }
    
}
