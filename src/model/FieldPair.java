package model;

/**
 * This class holds information about pair of fields,
 * that cannot be in an entry together.
 * For example for entry Book such pair is author/editor pair.
 */
public class FieldPair{
    public FieldType first;
    public FieldType second;

    /**
     * Constructs a pair of fields.
      * @param first is a member of pair.
     * @param second is a member of pair.
     */
    public FieldPair(FieldType first, FieldType second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Checks of this pair contains member.
     * @param type is FieldType instance.
     * @return true if first or second is equal to type.
     */
    public Boolean contains(FieldType type) {
        return first == type || second == type;
    }

    /**
     * Gets other member of the pair.
     * @param other is one of first or second field types.
     * @return other member of pair or null if other is not in this pair.
     */
    public FieldType getOther(FieldType other) {
        if(first == other) return second;
        if(second == other) return first;
        return null;
    }
}
