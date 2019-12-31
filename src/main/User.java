package main;

public class User {
    static User user = null;
    public static User getUser() {
        if(user == null)user = new User();
        return user;
    }

    private User() {}

    public void sendMessage(String message){
        int length = message.length();

        System.out.print("\n***\n");

        System.out.print(message);

        System.out.print("\n***\n\n");

    }
}
