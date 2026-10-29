package visitors;

import elements.Directory;
import elements.File;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicLong;

public class SizeCalculatorVisitor implements FileSystemVisitor {
    private final AtomicLong totalSize = new AtomicLong(0);

    @Override
    public void visitFile(File file) {
        totalSize.addAndGet(file.getSize()); // Not thread-safe!
    }

    @Override
    public void visitDirectory(Directory directory) {
        for (var child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public long getTotalSize() {
        return totalSize.get();
    }
}
