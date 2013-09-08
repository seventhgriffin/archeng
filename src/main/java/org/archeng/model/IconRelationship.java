package org.archeng.model;

public final class IconRelationship {

    public static enum Nature { POSITIVE, CONFLICTED, NEGATIVE };
    
    private Nature nature;
    private int points;
    private Icon icon;
    
}
