package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Objects;

/**
 * Entry is the abstract base class for all entry classes
 * which allow the application to to hold information about
 * an entry and provide methods to check validity of an entry.
 * @see <a href="https://www.wikiwand.com/en/BibTeX#/Entry_types">Entry types in BibTeX</a>
 * @see java.lang.Object
 */
public abstract class Entry {
    protected String key = null; //first item in a BibTeX entry, not a part of any field
    protected String crossRef = null; //the key of the cross-referenced entry
    public EntryType type; // type of the entry
    protected EnumMap<FieldType, String> fields; //EnumMap of all the fields
    protected FieldTypeList fieldTypeList; // List of required and optional fields

    /**
     * One argument constructor to initialize some of the fields.
     * @param type is type of the entry being created
     * @see EntryType
     */
    Entry(EntryType type){
        this.type = type;
        fields = new EnumMap<FieldType, String>(FieldType.class);
        fieldTypeList = new FieldTypeList();
    }

    /**
     * Method to add a field to this entry
     * @param type is a type of field being added
     * @see FieldType
     * @param value is a value of field being added
     * @throws IllegalArgumentException in case of field has already been added to this entry
     */
    public void addField(FieldType type, String value) throws IllegalArgumentException {
        checkFitnessOfField(type, value);
        fields.put(type, value);
    }

    /**
     * Method to check whether field type fits to this entry
     * @param type is type of field being checked for validity for this entry
     * @see FieldType
     * @throws IllegalArgumentException when field was not found in neither required nor optional field types
     * and when this entry already has an alternative field
     */
    private void checkFitnessOfField(FieldType type, String value) {
        if(fields.containsKey(type))
            throw new IllegalArgumentException(
                    "The field " + type + " in entry " + this.type + ":" + this.getKey() + " has already been added! " +
                            "\nIt holds value: \"" + fields.get(type)
                            + "\". \nYou tried to change it to: \""
                            + value +"\".");

        if(!fieldTypeList.doesFieldExist(type))
            throw new IllegalArgumentException("Field "+type+" is not appropriate for "+this.type+"!");
        FieldType partner = fieldTypeList.getPartnerOfField(type);
        if(partner != null){
            if(fields.containsKey(partner))
                throw new IllegalArgumentException(
                        "This "+this.type
                        +":"+this.key
                        +" already has an alternative field: "+partner
                        +". You tried to add: "+type);
        }
    }

    /**
     * Method that checks whether all required fields are present in this entry
     * @param crossReferenced is entry that is being used as a reference for the required fields
     *                        when checking whether required fields are present in this entry.
     * @return an <code>ArrayList</code> with all missing types
     */
    public ArrayList<FieldType> getMissingFieldTypes(Entry crossReferenced) {
        return fieldTypeList.getMissingRequiredFieldTypes(fields, crossReferenced);
    }

    /**
     * Gets the key
     * @return a <code> String </code> specifying the key of this entry
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key
     * @param key is a value of key being set
     */
    public void setKey(String key) {
        this.key = key;
    }
    /**
     * Gets the key
     * @return a <code> String </code> specifying the crossRef of this entry
     */
    public String getCrossRef() {
        return crossRef;
    }

    /**
     * Sets the crossRef
     * @param crossRef is a value of crossRef being set
     */
    public void setCrossRef(String crossRef) {
        this.crossRef = crossRef;
    }

    /**
     * Gets the fields
     * @return a <code> EnumMap </code> of all fields with their values of this entry
     */
    public EnumMap<FieldType, String> getFields() {
        return fields;
    }

    /**
     * Gets value of editor or author field of this entry
     * @return a <code>String</code> of author or editors names
     */
    public String getNameField() {
        if(fields.get(FieldType.AUTHOR) != null) return fields.get(FieldType.AUTHOR);
        if(fields.get(FieldType.EDITOR) != null) return fields.get(FieldType.EDITOR);
        return null;
    }

    /**
     * @see java.lang.Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, type);
    }

    /**
     * @see java.lang.Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) &&
                type == entry.type;
    }

}
