package main;

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
            "Welcome to bibparser!" + "\nThese are options you can use to run bibparser:\n\n";
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

    static User user = null;
    public static User getUser() {
        if(user == null)user = new User();
        return user;
    }

    private User() {}

    public void sendMessage(MessageType type, String message){
        String ANSI_COLOR = getColor(type);

        System.out.print(ANSI_COLOR+"\n***\n");

        System.out.print(message);

        System.out.print(ANSI_COLOR+"\n***\n\n"+ANSI_RESET);

    }

    public void sendMessage(String message) {
        sendMessage(MessageType.INFO, message);
    }

    private String getColor(MessageType t) {
        switch (t) {

            case INFO:
                return ANSI_YELLOW;
            case DANGER:
                return ANSI_RED;
        }
        return null;
    }

    public void printHelp() {
        sendMessage(hello+options);
    }

    public enum MessageType{
        INFO,
        DANGER
    }

    public void printOptions(String intro) {
        sendMessage(intro+options);
    }
}
