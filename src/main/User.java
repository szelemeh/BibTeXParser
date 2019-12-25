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
        for(int i=0; i<length+4; i++) {
            System.out.print("*");
        }
        System.out.print('\n');
        System.out.print("* ");

        System.out.print(message);
        System.out.print(" *");

        System.out.print('\n');

        for(int i=0; i<length+4; i++) {
            System.out.print("*");
        }
        System.out.print('\n');
    }
}
