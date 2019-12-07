package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class FieldTypeList implements IFieldTypeList {
    private ArrayList<FieldType> singleRequired;
    private ArrayList<FieldPair> duplexRequired;
    private ArrayList<FieldType> singleOptional;
    private ArrayList<FieldPair> duplexOptional;

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
    public Boolean areRequiredFieldsPresentIn(EnumMap<FieldType, String> fields) {
        for(FieldType type: singleRequired){
            if(!fields.containsKey(type))return false;
        }
        for(FieldPair pair: duplexRequired){
            if(!fields.containsKey(pair.first) && !fields.containsKey(pair.second))return false;
        }
        return true;
    }
}
