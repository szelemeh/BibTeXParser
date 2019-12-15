package parsers;

import model.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {
    private String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    //returns list of Name objects from value of editor or author field
    public List<Name> getNames(String fieldValue) {
        List<Name> names = new ArrayList<>();

        StringTokenizer wholeNames = new StringTokenizer(fieldValue, "and");

        while(wholeNames.hasMoreElements()) {
            Name name = null;

            String rawName = wholeNames.nextElement().toString();
            if(!rawName.contains(",")) {
                name = parseRawNameWithoutCommas(rawName);
            }
            else {
                name = parseRawNameWithCommas(rawName);

            }

            names.add(name);
        }
        return null;
    }

    private Name parseRawNameWithCommas(String rawName) {
        Name name = new Name();
        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();
        StringBuilder von = new StringBuilder();
        StringBuilder jr = new StringBuilder();



        return name;
    }

    public Name parseRawNameWithoutCommas(String rawName) {
        Name name = new Name();
        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();
        StringBuilder von = new StringBuilder();


        StringTokenizer words = new StringTokenizer(rawName);
        while(words.hasMoreElements()){
            String word = words.nextElement().toString();
            if(isCapitalized(word))firstName.append(word);
            else {
                von.append(word);
                break;
            }
        }

        while (words.hasMoreElements()){
            String word = words.nextElement().toString();
            if(isLowered(word)) von.append(word);
            else {
                lastName.append(word);
                break;
            }
        }

        while(words.hasMoreElements()) {
            lastName.append(words.nextElement().toString());
        }

        name.setFirstName(firstName.toString());
        name.setLastName(lastName.toString());
        name.setVon(von.toString());
        return name;
    }

    private Boolean isCapitalized(String word){
        return ( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' );
    }
    private Boolean isLowered(String word){
        return ( word.charAt(0) >= 'a' && word.charAt(0) <= 'z' );
    }
}
