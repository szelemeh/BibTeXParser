package parsers;

import model.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * NameParser parses the content of a BibTeX name which
 * may be a value of author or editor field.
 */
public class NameParser {

    //returns

    /**
     * Parses BibTeX name to an object representation of it.
     * @param fieldValue is a raw value of some name field.
     * @return list of Name objects from value of editor or author field
     */
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


    /**
     * Method takes one BibTeX name and parses it to return
     * a Name instance.
     * @param rawName is raw BibTeX name which contains one or two
     *                commas depending whether jr part is present.
     * @return object representation of BibTeX name.
     * @see Name
     */
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

    /**
     * Similarly to <code>parseRawNameWithCommas(String rawName)</code>
     * it parses BibTeX name to an object representation of it.
     * The difference lies in the fact that <code>rawName</code> in
     * this method does not contain commas which changes the way it should
     * be parsed.
     * @param rawName is raw BibTeX name which does not contain commas.
     * @return an instance of <code>Name</code>
     * @see Name
     */
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
            if (i == words.size()-1) {
                lastName.append(words.get(i));
                lastName.append("~");
                name.setFirstName(firstName.toString());
                name.setLastName(lastName.toString());
                return name;
            } else {
                firstName.append(words.get(i));
                firstName.append("~");
            }
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

    /**
     * Checks if <code>word</code> is capitalized.
     * @param word is a word to be checked.
     * @return true if first letter of <code>word</code>
     * is capitalized, false otherwise.
     */
    private Boolean isCapitalized(String word){
        if(word == null) return false;
        return ( word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' );
    }
    /**
     * Checks if <code>word</code> is lowered.
     * @param word is a word to be checked.
     * @return true if first letter of <code>word</code>
     * is written in lowercase, false otherwise.
     */
    private Boolean isLowered(String word){
        if(word == null) return false;
        return ( word.charAt(0) >= 'a' && word.charAt(0) <= 'z' );
    }
}
