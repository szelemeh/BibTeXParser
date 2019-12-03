package entries;

import java.util.ArrayList;
import java.util.EnumMap;

public abstract class Entry {
    protected EntryType type;
    protected EnumMap<FieldType, String> fields;
    ArrayList<FieldType> requiredFieldsTypes;

    Entry(){
          fields = new EnumMap<FieldType, String>(FieldType.class);
    }

    protected void addField(FieldType type, String value) {
        fields.put(type, value);
    }

    protected boolean verifyFields() {
        for(FieldType requiredFieldType: requiredFieldsTypes) {
            if(!fields.containsKey(requiredFieldType)) return false;
        }
        return true;
    }
}
