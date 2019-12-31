package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class FieldTypeList implements IFieldTypeList {
    private ArrayList<FieldType> singleRequired;
    private ArrayList<FieldPair> duplexRequired;
    private ArrayList<FieldType> singleOptional;
    private ArrayList<FieldPair> duplexOptional;

    public ArrayList<FieldType> getSingleRequired() {
        return singleRequired;
    }

    public ArrayList<FieldPair> getDuplexRequired() {
        return duplexRequired;
    }

    public ArrayList<FieldType> getSingleOptional() {
        return singleOptional;
    }

    public ArrayList<FieldPair> getDuplexOptional() {
        return duplexOptional;
    }

    public FieldTypeList() {
        this.singleRequired = new ArrayList<>();
        this.duplexRequired = new ArrayList<>();
        this.singleOptional = new ArrayList<>();
        this.duplexOptional = new ArrayList<>();
    }

    @Override
    public void defineSingleRequiredFields(FieldType... types) {
        singleRequired.addAll(Arrays.asList(types));
    }

    @Override
    public void defineSingleOptionalFields(FieldType... types) {
        singleOptional.addAll(Arrays.asList(types));
    }

    @Override
    public void defineDuplexRequiredFields(FieldPair... pairs) {
        duplexRequired.addAll(Arrays.asList(pairs));
    }

    @Override
    public void defineDuplexOptionalFields(FieldPair... pairs) {
        duplexOptional.addAll(Arrays.asList(pairs));
    }

    @Override
    public Boolean doesFieldExist(FieldType type) {
        if(singleRequired.contains(type) ||singleOptional.contains(type)) return true;
        for(FieldPair pair: duplexRequired) if(pair.contains(type))return true;
        for(FieldPair pair: duplexOptional) if(pair.contains(type))return true;
        return false;
    }

    @Override
    public FieldType getPartnerOfField(FieldType type) {
        for(FieldPair pair: duplexRequired) if(pair.getPartner(type) != null) return pair.getPartner(type);
        for(FieldPair pair: duplexOptional) if(pair.getPartner(type) != null) return pair.getPartner(type);
        return null;
    }

    @Override
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
