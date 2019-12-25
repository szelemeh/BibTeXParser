package main;

import model.*;
import parsers.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Document doc = new Document("C:\\Users\\stass\\IdeaProjects\\BibTexParser\\tests\\resourses\\example.bib");
        doc.printAll();

    }
}
