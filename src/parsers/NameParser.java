package parsers;

import model.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NameParser {

    //returns list of Name objects from value of editor or author field
    public List<Name> getNames(String fieldValue) {
        List<Name> names = new ArrayList<>();

        String[] wholeNames = fieldValue.split("and");

        for(int i=0; i<wholeNames.length; i++) {
            Name name = null;

            String rawName = wholeNames[i].replaceAll("[^a-zA-Z ,]", "");
            if(!rawName.contains(",")) {
                name = parseRawNameWithoutCommas(rawName);
            }
            else {
                name = parseRawNameWithCommas(rawName);
            }
            names.add(name);
        }
        return names;
    }



    public Name parseRawNameWithCommas(String rawName) {
        Name name = new Name();
        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();
        StringBuilder von = new StringBuilder();
        StringBuilder jr = new StringBuilder();

        StringTokenizer betweenCommas = new StringTokenizer(rawName, ",");
        int howManyTokens = betweenCommas.countTokens();
        String rawVonAndLastName = "";
        if(betweenCommas.hasMoreTokens()) rawVonAndLastName = betweenCommas.nextToken();
        String rawJr = "";
        if(howManyTokens == 3 && betweenCommas.hasMoreTokens()) rawJr = betweenCommas.nextToken();
        String rawFirstName = "";
        if(betweenCommas.hasMoreTokens()) rawFirstName = betweenCommas.nextToken();

        //building jr
        StringTokenizer forJr = new StringTokenizer(rawJr);
        while(forJr.hasMoreTokens()){
            jr.append(forJr.nextToken());
            jr.append("~");
        }

        //building firstName
        StringTokenizer forFirstName = new StringTokenizer(rawFirstName);
        while(forFirstName.hasMoreTokens()){
            firstName.append(forFirstName.nextToken());
            firstName.append("~");
        }

        StringTokenizer forVonAndLastName = new StringTokenizer(rawVonAndLastName);
        ArrayList<String> words = new ArrayList<>();
        while(forVonAndLastName.hasMoreTokens())words.add(forVonAndLastName.nextToken());

        //getting to the end of von
        int j;
        for(j=words.size()-1; j>=0 && !isLowered(words.get(j)); j-- );

        //building von
        for(int i=0; i<=j; i++) {
            von.append(words.get(i));
            von.append("~");
        }

        //building lastName
        j++;
        while(j < words.size()) {
            lastName.append(words.get(j));
            lastName.append("~");
            j++;
        }

        name.setFirstName(firstName.toString());
        name.setLastName(lastName.toString());
        name.setVon(von.toString());
        name.setJr(jr.toString());

        return name;
    }

    public Name parseRawNameWithoutCommas(String rawName) {
        Name name = new Name();
        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();
        StringBuilder von = new StringBuilder();

        //tokenizing rawName
        StringTokenizer tokens = new StringTokenizer(rawName);
        ArrayList<String> words = new ArrayList<>();
        while(tokens.hasMoreTokens())words.add(tokens.nextToken());

        //getting to the beginning of von and building firstName
        int i;
        for(i=0; i<words.size() && isCapitalized(words.get(i)); i++) {
            firstName.append(words.get(i));
            firstName.append("~");
        }

        //getting to the end of von
        int j;
        for(j=words.size()-1; j>= 0 && !isLowered(words.get(j)); j--);

        //building von
        while(i <= j) {
            von.append(words.get(i));
            von.append("~");
            i++;
        }
        j++;
        //building lastName
        while(j < words.size()) {
            lastName.append(words.get(j));
            lastName.append("~");
            j++;
        }

        name.setFirstName(firstName.toString());
        name.setLastName(lastName.toString());
        name.setVon(von.toString());
        return name;
    }

    private Boolean isCapitalized(String word){
        if(word == null) return false;
        return ( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' );
    }
    private Boolean isLowered(String word){
        return ( word.charAt(0) >= 'a' && word.charAt(0) <= 'z' );
    }
}
