package model;

import java.util.ArrayList;
import java.util.EnumMap;

public abstract class Entry {
    protected String key;
    public EntryType type;
    protected EnumMap<FieldType, String> fields;
    protected FieldTypeList fieldTypeList;

    Entry(EntryType type){
        this.type = type;
        fields = new EnumMap<FieldType, String>(FieldType.class);
        fieldTypeList = new FieldTypeList();
    }
    public void addField(FieldType type, String value) {
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

}
