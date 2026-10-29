package visitors;

import elements.Directory;
import elements.File;

public interface FileSystemVisitor {
    void visitFile(File file);
    void visitDirectory(Directory directory);
}
