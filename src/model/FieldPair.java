package model;

public class FieldPair{
    public FieldType first;
    public FieldType second;

    public FieldPair(FieldType first, FieldType second) {
        this.first = first;
        this.second = second;
    }

    public Boolean contains(FieldType type) {
        return first == type || second == type;
    }

    public FieldType getPartner(FieldType other) {
        if(first == other) return second;
        if(second == other) return first;
        return null;
    }
}
