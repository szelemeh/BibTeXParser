package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

/**
 * This class is a container for required
 * and optional field types in an entry.
 * Such implementation is used because some
 * BibTeX entries have alternative field which
 * cannot be together in one entry.
 */
public class FieldTypeList {
    /**
     * Contains all required types of fields that
     * an entry must have.
     */
    private ArrayList<FieldType> singleRequired;
    /**
     * Contains all required pairs of field types
     * of which only one member must appear in a full entry.
     */
    private ArrayList<FieldPair> duplexRequired;
    /**
     * Contains all optional types of fields that
     * an entry can have.
     */
    private ArrayList<FieldType> singleOptional;
    /**
     * Contains all optional pairs of field types
     * of which only one member can appear in a full entry.
     */
    private ArrayList<FieldPair> duplexOptional;

    /**
     * Constructs a FieldTypesList with empty containers for
     * field types.
     */
    public FieldTypeList() {
        this.singleRequired = new ArrayList<>();
        this.duplexRequired = new ArrayList<>();
        this.singleOptional = new ArrayList<>();
        this.duplexOptional = new ArrayList<>();
    }

    /**
     * Defines single required field types for an entry.
     * @param types is an array of FieldTypes
     */
    public void defineSingleRequiredFields(FieldType... types) {
        singleRequired.addAll(Arrays.asList(types));
    }

    /**
     * Defines single optional field types for an entry.
     * @param types is an array of FieldTypes
     */
    public void defineSingleOptionalFields(FieldType... types) {
        singleOptional.addAll(Arrays.asList(types));
    }

    /**
     * Defines duplex required field types for an entry.
     * @param pairs is an array of FieldTypes
     */
    public void defineDuplexRequiredFields(FieldPair... pairs) {
        duplexRequired.addAll(Arrays.asList(pairs));
    }

    /**
     * Defines duplex optional field types for an entry.
     * @param pairs is an array of FieldTypes
     */
    public void defineDuplexOptionalFields(FieldPair... pairs) {
        duplexOptional.addAll(Arrays.asList(pairs));
    }

    /**
     * Method that checks whether type is appropriate for the entry.
     * @param type is a FieldType.
     * @return true if any of the field type container in this FieldTypeList instance
     * contains type, false otherwise.
     */
    public Boolean doesFieldExist(FieldType type) {
        if(singleRequired.contains(type) ||singleOptional.contains(type)) return true;
        for(FieldPair pair: duplexRequired) if(pair.contains(type))return true;
        for(FieldPair pair: duplexOptional) if(pair.contains(type))return true;
        return false;
    }

    /**
     * Gets other member of field type pair.
     * @param other is one of the members of some pair
     * @return FieldType if other is member of some pair, null otherwise.
     */
    public FieldType getPartnerOfField(FieldType other) {
        for(FieldPair pair: duplexRequired) if(pair.getOther(other) != null) return pair.getOther(other);
        for(FieldPair pair: duplexOptional) if(pair.getOther(other) != null) return pair.getOther(other);
        return null;
    }

    /**
     * Returns an ArrayList of missing fields for the entry. It takes
     * <code>fields</code> and checks whether it contains all required
     * single and duplex fields. Method does not check if the entry contains
     * all optional field.
     * @param fields is an ArrayList of fields that are present in the entry.
     * @return ArrayList of missing fields.
     */
    public ArrayList<FieldType> getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields) {
        ArrayList<FieldType> missingFields = new ArrayList<>();
        for(FieldType type: singleRequired){
            if(!fields.containsKey(type))missingFields.add(type);
        }
        for(FieldPair pair: duplexRequired){
            if(!fields.containsKey(pair.first) && !fields.containsKey(pair.second)) {
                missingFields.add(pair.first);
                missingFields.add(pair.second);
            }
        }
        if (missingFields.isEmpty()) return null;
        return missingFields;
    }

    /**
     * Similarly to <code>getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields)</code>
     * it checks if the entry contains all required fields but this method in case if
     * it does not find field type in the entry in looks for the field in the
     * <code>crossReferenced</code> entry.
     * @param fields is an ArrayList of fields that are present in the entry.
     * @param crossReferenced is crossreferenced in this entry.
     * @return ArrayList of missing fields.
     */
    public ArrayList<FieldType> getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields, Entry crossReferenced) {
        if (crossReferenced == null) return getMissingRequiredFieldTypes(fields);

        ArrayList<FieldType> missingFields = new ArrayList<>();

        EnumMap<FieldType, String> crossFields = crossReferenced.fields;

        for(FieldType type: singleRequired){
            if(!fields.containsKey(type) && !crossFields.containsKey(type)) missingFields.add(type);
        }
        for(FieldPair pair: duplexRequired){
            if((!fields.containsKey(pair.first) && !fields.containsKey(pair.second))
                    && (!crossFields.containsKey(pair.first) && !crossFields.containsKey(pair.second))) {

                missingFields.add(pair.first);
                missingFields.add(pair.second);
            }
        }
        if (missingFields.isEmpty()) return null;
        return missingFields;
    }
}
