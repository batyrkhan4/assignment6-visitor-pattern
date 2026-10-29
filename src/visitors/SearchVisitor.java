package visitors;

import elements.Directory;
import elements.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements FileSystemVisitor {
    private final String targetName;
    // Thread-safe synchronized list
    private final List<String> results = Collections.synchronizedList(new ArrayList<>());

    public SearchVisitor(String targetName) {
        if (targetName == null || targetName.isBlank()) {
            throw new IllegalArgumentException("Target name is null or empty");
        }
        this.targetName = targetName;
    }

    @Override
    public void visitFile(File file) {
        if (file.getName().equalsIgnoreCase(targetName)) {
            results.add(file.getName());
        }
    }
    @Override
    public void visitDirectory(Directory directory) {
        for (var child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public List<String> getResults() {
        // Guard iteration/copying with a synchronized block
        synchronized (results) {
            return List.copyOf(results);
        }
    }

}
