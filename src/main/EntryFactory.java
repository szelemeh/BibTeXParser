package main;

import model.*;

/**
 * EntryFactory is class that creates entries.
 */
public class EntryFactory {
    /**
     * Method that creates and returns new Entry based on parameter type.
     * @param type is an entry type(category)
     * @return <code>Entry</code>
     * @see Entry
     * @see EntryType
     */
    public static Entry create(EntryType type) {
        switch(type) {
            case ARTICLE:
                return new Article();
            case BOOK:
                return new Book();
            case BOOKLET:
                return new Booklet();
            case CONFERENCE:
                return new Conference();
            case INBOOK:
                return new Inbook();
            case INCOLLECTION:
                return new Incollection();
            case INPROCEEDINGS:
                return new Inproceedings();
            case MANUAL:
                return new Manual();
            case MASTERSTHESIS:
                return new MastersThesis();
            case MISC:
                return new Misc();
            case PHDTHESIS:
                return new PhdThesis();
            case PROCEEDINGS:
                return new Proceedings();
            case TECHREPORT:
                return new TechReport();
            case UNPUBLISHED:
                return new Unpublished();
        }
        return null;
    }
}
