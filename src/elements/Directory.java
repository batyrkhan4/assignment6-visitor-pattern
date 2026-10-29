package elements;

import visitors.FileSystemVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> children = new ArrayList<>();

    public Directory(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Directory name cannot be null or empty");
        }
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void add(FileSystemElement element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        children.add(element);
    }

    public List<FileSystemElement> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visitDirectory(this);
    }

}
