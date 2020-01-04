package model;

import java.util.ArrayList;
import java.util.EnumMap;



/**
 *
 */
public interface IFieldTypeList {
    void defineSingleRequiredFields(FieldType... types);

    void defineSingleOptionalFields(FieldType... types);

    void defineDuplexRequiredFields(FieldPair... pairs);

    void defineDuplexOptionalFields(FieldPair... pairs);

    Boolean doesFieldExist(FieldType type);

    //returns null if field is single
    FieldType getPartnerOfField(FieldType type);

    ArrayList<FieldType> getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields);

    ArrayList<FieldType> getMissingRequiredFieldTypes(EnumMap<FieldType, String> fields, Entry crossReferenced);
}
