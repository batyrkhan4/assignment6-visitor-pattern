package elements;

import visitors.FileSystemVisitor;

public interface FileSystemElement {
    void accept(FileSystemVisitor visitor);
}

