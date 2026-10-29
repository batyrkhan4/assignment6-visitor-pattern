
import elements.Directory;
import elements.File;
import visitors.SizeCalculatorVisitor;
import visitors.SearchVisitor;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory documents = new Directory("documents");

        root.add(new File("photo.jpg", 1500));
        root.add(new File("notes.txt", 500));

        documents.add(new File("report.pdf", 2000));
        documents.add(new File("notes.txt", 300));

        root.add(documents);

        SizeCalculatorVisitor sizeVisitor = new SizeCalculatorVisitor();
        root.accept(sizeVisitor);

        System.out.println("Total file size: " + sizeVisitor.getTotalSize() + " bytes");

        SearchVisitor searchVisitor = new SearchVisitor("notes.txt");
        root.accept(searchVisitor);

        System.out.println("Search results: " + searchVisitor.getResults());
    }
}
