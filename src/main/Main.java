package main;

public class Main {

    public static void main(String[] args) {
        Document doc = new Document("C:\\Users\\stass\\IdeaProjects\\BibTexParser\\tests\\resourses\\main_example.bib");
//        Document doc = new Document("C:\\Users\\stass\\IdeaProjects\\BibTexParser\\tests\\resourses\\ex.bib");
        doc.checkAllEntries();
//        doc.printAll();

    }
}
