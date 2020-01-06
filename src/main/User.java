package main;

/**
 * User is a class to communicate with the user of the program through CLI.
 */
public class User {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";
    private final String ANSI_WHITE = "\u001B[37m";
    private final String optionFormat = ANSI_GREEN+"   %-25s"+ANSI_RESET+" %-100s %n%n";
    private final String optionLongDescrFormat = ANSI_GREEN+"   %-25s"+ANSI_RESET+" %-100s %n";
    private final String hello =
            "Welcome to BibTeX Parser CLI!" + "\nThese are options you can use to run bibparser:\n\n";
    private final String options =
            String.format(optionLongDescrFormat, "--file <path>", "Specify path to a BiBTeX file to read from.") +
            String.format(optionFormat, "","Prints all entries if no other option given.") +
            String.format(optionFormat, "-f <path>", "Same as --file <path>.") +
            String.format(optionLongDescrFormat, "--author <args>", "Print entries by last names of authors given in <args> divided by spaces.") +
            String.format(optionFormat, "", "To use this option path to file must be specified through --file option.") +
            String.format(optionFormat, "-a <args>", "Same as --author <args>.") +
            String.format(optionLongDescrFormat, "--category <args>", "Print entries by categories given in <args> divided by spaces.") +
            String.format(optionFormat, "", "To use this option path to file must be specified through --file option.") +
            String.format(optionFormat, "-c <args>", "Same as --category <args>.");

    /**
     * user is a static attribute to have only one instance of the class.
     */
    static User user = null;

    /**
     * Method to get user instance.
     * @return <code>User</code>
     */
    public static User getUser() {
        if(user == null)user = new User();
        return user;
    }

    /**
     * Private constructor to use Singleton design pattern
     */
    private User() {}

    /**
     * Prints message for user to the console.
     * @param type is type of message.
     * @see MessageType
     * @param message is message content.
     */
    public void printMessage(MessageType type, String message){
        String ANSI_COLOR = getColor(type);

        System.out.print(ANSI_COLOR+"\n***\n");

        System.out.print(message);

        System.out.print(ANSI_COLOR+"\n***\n\n"+ANSI_RESET);

    }

    /**
     * Method that prints info message to console.
     * @param message is a text that user os going to see
     *                in console.
     */
    public void printMessage(String message) {
        printMessage(MessageType.INFO, message);
    }

    /**
     * Gets color for given message type
     * @param t is  type of message.
     * @see MessageType
     * @return color constant.
     */
    private String getColor(MessageType t) {
        String result = null;
        switch (t) {

            case INFO:
                result = ANSI_YELLOW;
                break;
            case DANGER:
                result = ANSI_RED;
                break;

        }
        return result;
    }

    /**
     * Prints out help message to the console.
     */
    public void printHelp() {
        printMessage(hello+options);
    }

    /**
     * Enum holds types of messages that user can receive.
     */
    public enum MessageType{
        INFO,
        DANGER
    }

    /**
     * Prints out help message about commands available
     * with a corresponding introduction to it.
     * @param intro is text that will explain the user what
     *              is not okay in his command.
     */
    public void printOptions(String intro) {
        printMessage(intro+options);
    }
}
