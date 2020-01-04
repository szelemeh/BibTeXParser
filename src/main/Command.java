package main;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Option is a container for user command
 */
public class Command {
    /**
     * Type of command.
     * @see CommandType
     */
    private CommandType type;
    /**
     * Arguments for command
     */
    private ArrayList<String> args;

    /**
     * Constructs a command.
     * @param type is type of command.
     * @param args is arguments for the command.
     */
    public Command(CommandType type, ArrayList<String> args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Gets command type.
     * @return CommandType
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Sets command type.
     * @param type is a new command type.
     */
    public void setType(CommandType type) {
        this.type = type;
    }

    /**
     * Gets command arguments.
     * @return ArrayList of command arguments.
     */
    public ArrayList<String> getArgs() {
        return args;
    }

    /**
     * Sets command arguments.
     * @param args is a new ArrayList of command arguments
     */
    public void setArgs(ArrayList<String> args) {
        this.args = args;
    }

    /**
     * Compares to ArrayLists<String>
     * @param fst is one ArrayList to compare.
     * @param sec is another ArrayList to compare.
     * @return True if corresponding elements in arrays are equal.
     * False if ArrayLists have different sizes or one ore more
     * corresponding elements are different.
     */
    private boolean areEqualArrayLists(ArrayList<String> fst, ArrayList<String> sec) {
        if (fst.size() != sec.size())return false;
        for(int i=0; i<fst.size(); i++) {
            if (!fst.get(i).equals(sec.get(i)))return false;
        }
        return true;
    }


    /**
     * @see Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return type == command.type && areEqualArrayLists(this.args, command.args);
    }

    /**
     * @see Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, args);
    }
}
