package parsers;

import main.Command;
import main.CommandType;
import main.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * CommandParser parses arguments for the program
 * to build commands that the program can take
 * and execute on.
 */
public class CommandParser {
    String[] args;
    /**
     * Constructs an instance of CommandParser.
     * @param args is array of arguments to the program.
     */
    public CommandParser(String[] args) {
        this.args = args;
    }

    /**
     * Method that parses <code>args</code> to a list of commands.
     * @return An ArrayList of commands to execute on.
     */
    public ArrayList<Command> getOptions() {
        ArrayList<Command> commands = new ArrayList<>();

        //edge cases:
        if (args.length == 0) {
            commands.add(new Command(CommandType.HELP, null));
            return commands;
        }

        Iterable<String> iterable = Arrays.asList(args);
        Iterator<String> it = iterable.iterator();

        ArrayList<String> currOptionArgs = null;
        CommandType currCommandType = null;
        Boolean gotArgToCurrOption = null;

        while(it.hasNext()) {
            String word = it.next();

            //initializing currents:
            if(currCommandType == null) {
                if(word.charAt(0) != '-') {
                    User.getUser().printMessage(User.MessageType.DANGER,
                            "Start writing arguments to the program with an option!\n" +
                                    "Run program with no arguments to get help."
                    );
                    System.exit(1);
                }

                currCommandType = getType(word);
                gotArgToCurrOption = false;
                currOptionArgs = new ArrayList<>();
            }
            //processing new option
            else if (word.charAt(0) == '-') {
                if (!gotArgToCurrOption) {
                    User.getUser().printMessage(User.MessageType.DANGER,
                            "Provide arguments to the option \"" +
                                    currCommandType.toString().toLowerCase() +
                                    "\" before moving to another one!\n" +
                                    "Run program with no arguments to get help."
                    );
                    System.exit(1);
                }
                commands.add(new Command(currCommandType, currOptionArgs));
                currCommandType = getType(word);
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
            User.getUser().printMessage(User.MessageType.DANGER,
                    "Provide arguments to the option \"" +
                            currCommandType.toString().toLowerCase() +
                            "\"!\nRun program with no arguments to get help."
            );
            System.exit(1);
        }
        else if (gotArgToCurrOption != null && gotArgToCurrOption) {
            commands.add(new Command(currCommandType, currOptionArgs));
        }

        if (commands.get(0).getType() == CommandType.FILE && commands.size() == 1) {
            commands.add(new Command(CommandType.ALL, null));
        }

        return sortCommands(commands);
    }

    /**
     * Gets the command type out of <code>word</code>
     * @param word is an option which user typed in.
     * @return a <code>CommandType</code>
     * @see CommandType
     */
    private CommandType getType(String word) {
        switch (word) {
            case "--file":
            case "-f":
                return CommandType.FILE;

            case "--author":
            case "-a":
                return CommandType.AUTHOR;

            case "--category":
            case "-c":
                return CommandType.CATEGORY;

            default:
                User.getUser().printOptions("Use these options: \n");
                System.exit(1);
        }
        return null;
    }


    /**
     * Method that sorts options so that option with type FILE is
     * put at the beginning of ArrayList to avoid execution
     * of a command that would use document with it entries,
     * before it is created.
     * @param commands is an ArrayList of commands.
     * @see main.Document
     * @return sorted <code>commands</code>.
     */
    private ArrayList<Command> sortCommands(ArrayList<Command> commands) {
        for (int i = 0; i< commands.size(); i++) {
            if (commands.get(i).getType() == CommandType.FILE) {
                Command fileCommand = commands.get(i);

                if(!fileExist(fileCommand.getArgs().get(0))) {
                    User.getUser().printMessage(User.MessageType.DANGER,
                            "File "+ fileCommand.getArgs().get(0)+" does not exist!");

                    System.exit(1);
                }

                ArrayList<Command> result = new ArrayList<>();
                result.add(fileCommand);
                result.addAll(commands.subList(0, i));
                result.addAll(commands.subList(i+1, commands.size()));

                return result;
            }
        }
        User.getUser().printMessage(User.MessageType.DANGER,
                "Provide program with BiBTeX file to parse!");
        System.exit(1);

        return commands;
    }

    /**
     * Checks if file with <code>path</code> exist.
     * @param path is a path to file to file.
     * @return <code>true</code> if file exist, <code>false</code> otherwise.
     */
    private boolean fileExist(String path) {
        int indexOfStartOfFileFormat = path.lastIndexOf('.');

        if (indexOfStartOfFileFormat == -1) {
            User.getUser().printMessage(User.MessageType.DANGER,
                    "Please provide file with the .bib format!"
            );
            System.exit(1);
        }

        String fileFormat = path.substring(indexOfStartOfFileFormat);

        if (!fileFormat.equals(".bib")) {
            User.getUser().printMessage(User.MessageType.DANGER,
                    "Please provide file with the .bib format!"
            );
            System.exit(1);
        }

        File tempFile = new File(path);
        return tempFile.exists();
    }
}
