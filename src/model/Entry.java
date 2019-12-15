package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Objects;

public abstract class Entry {
    protected String key = null; // TODO: 09-Dec-19 change so it will take key from input fields if possible
    public EntryType type;
    protected EnumMap<FieldType, String> fields;
    protected FieldTypeList fieldTypeList;

    Entry(EntryType type){
        this.type = type;
        fields = new EnumMap<FieldType, String>(FieldType.class);
        fieldTypeList = new FieldTypeList();
    }
    public void addField(FieldType type, String value) {
        if(fields.containsKey(type)) throw new IllegalArgumentException("The field has already been added!");
        checkValidityOfInputField(type);
        fields.put(type, value);
    }

    private void checkValidityOfInputField(FieldType type) {
        if(!fieldTypeList.doesFieldExist(type))
            throw new IllegalArgumentException("Field is not appropriate for this entry!");
        FieldType partner = fieldTypeList.getPartnerOfField(type);
        if(partner != null){
            if(fields.containsKey(partner))
                throw new IllegalArgumentException("This entry already has an alternative field!");
        }
    }

    protected Boolean areRequiredFieldsPresent() {
        return fieldTypeList.areRequiredFieldsPresentIn(fields);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) &&
                type == entry.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, type);
    }

    public EnumMap<FieldType, String> getFields() {
        return fields;
    }

    public Boolean doesFieldTypeExist(FieldType type) {
        return fieldTypeList.doesFieldExist(type);
    }

    public void fillItself() {
        this.setKey("mock-key");
        ArrayList<FieldType> singles = fieldTypeList.getSingleRequired();
        singles.addAll(fieldTypeList.getSingleOptional());
        for(FieldType type: singles) {
            this.addField(type, "mock");
        }
    }
}
