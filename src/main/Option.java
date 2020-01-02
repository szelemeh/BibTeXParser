package main;

import java.util.ArrayList;
import java.util.Objects;

public class Option {
    private OptionType type;
    private ArrayList<String> args;

    public Option(OptionType type, ArrayList<String> args) {
        this.type = type;
        this.args = args;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return type == option.type && areEqualArrayLists(this.args, option.args);
    }

    private boolean areEqualArrayLists(ArrayList<String> fst, ArrayList<String> sec) {
        if (fst.size() != sec.size())return false;
        for(int i=0; i<fst.size(); i++) {
            if (!fst.get(i).equals(sec.get(i)))return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, args);
    }
}
