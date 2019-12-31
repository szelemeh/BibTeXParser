package model;

import java.util.ArrayList;
import java.util.EnumMap;

public interface IFieldTypeList {
    public void defineSingleRequiredFields(FieldType... types);

    public void defineSingleOptionalFields(FieldType... types);

    public void defineDuplexRequiredFields(FieldPair... pairs);

    public void defineDuplexOptionalFields(FieldPair... pairs);

    public Boolean doesFieldExist(FieldType type);

    //returns null if field is single
    public FieldType getPartnerOfField(FieldType type);

    public ArrayList<FieldType> getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields);

    public ArrayList<FieldType> getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields, Entry crossReferenced);
}
