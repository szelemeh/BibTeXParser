package parsers;

import main.Option;
import main.OptionType;
import main.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// TODO: 02-Jan-20 inplement this 
public class OptionsParser {
    String[] args;

    public OptionsParser(String[] args) {
        this.args = args;
    }

    /**
     * 
     * @return An ArrayList of options to execute on.
     */
    public ArrayList<Option> getOptions() {
        ArrayList<Option> options = new ArrayList<>();

        //edge cases:
        if (args.length == 0) {
            options.add(new Option(OptionType.HELP, null));
            return options;
        }

        Iterable<String> iterable = Arrays.asList(args);
        Iterator<String> it = iterable.iterator();

        ArrayList<String> currOptionArgs = null;
        OptionType currOptionType = null;
        Boolean gotArgToCurrOption = null;

        while(it.hasNext()) {
            String word = it.next();

            //initializing currents:
            if(currOptionType == null) {
                if(word.charAt(0) != '-') {
                    User.getUser().sendMessage(User.MessageType.DANGER,
                            "Start writing arguments to the program with an option!\n" +
                                    "Run program with no arguments to get help."
                    );
                    System.exit(1);
                }

                currOptionType = getType(word);
                gotArgToCurrOption = false;
                currOptionArgs = new ArrayList<>();
            }
            //processing new option
            else if (word.charAt(0) == '-') {
                if (!gotArgToCurrOption) {
                    User.getUser().sendMessage(User.MessageType.DANGER,
                            "Provide arguments to the option \"" +
                                    currOptionType.toString().toLowerCase() +
                                    "\" before moving to another one!\n" +
                                    "Run program with no arguments to get help."
                    );
                    System.exit(1);
                }
                options.add(new Option(currOptionType, currOptionArgs));
                currOptionType = getType(word);
                gotArgToCurrOption = false;
                currOptionArgs = new ArrayList<>();
            }
            //adding option argument to current
            else if (word.charAt(0) != '-') {
                currOptionArgs.add(word);
                gotArgToCurrOption = true;
            }
        }

        if (gotArgToCurrOption != null && !gotArgToCurrOption) {
            User.getUser().sendMessage(User.MessageType.DANGER,
                    "Provide arguments to the option \"" +
                            currOptionType.toString().toLowerCase() +
                            "\"!\nRun program with no arguments to get help."
            );
            System.exit(1);
        }
        else if (gotArgToCurrOption != null && gotArgToCurrOption) {
            options.add(new Option(currOptionType, currOptionArgs));
        }

        if (options.get(0).getType() == OptionType.FILE && options.size() == 1) {
            options.add(new Option(OptionType.ALL, null));
        }

        return sortOptions(options);
    }

    private OptionType getType(String word) {
        switch (word) {
            case "--file":
            case "-f":
                return OptionType.FILE;

            case "--author":
            case "-a":
                return OptionType.AUTHOR;

            case "--category":
            case "-c":
                return OptionType.CATEGORY;

            default:
                User.getUser().printOptions("Use these options: \n");
                System.exit(1);
        }
        return null;
    }


    /**
     * Method that sorts options so that options with type FILE are put in the beginning of ArrayList
     * @return
     */
    private ArrayList<Option> sortOptions(ArrayList<Option> options) {
        for (int i=0; i<options.size(); i++) {
            if (options.get(i).getType() == OptionType.FILE) {
                Option fileOption = options.get(i);

                ArrayList<Option> result = new ArrayList<>();
                result.add(fileOption);
                result.addAll(options.subList(0, i));
                result.addAll(options.subList(i+1, options.size()));

                return result;
            }
        }
        User.getUser().sendMessage(User.MessageType.DANGER,
                "Provide program with BiBTeX file to parse!");
        System.exit(1);

        return options;
    }
}
