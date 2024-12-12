package org.example;

import java.util.ArrayList;
import java.util.List;

public class DiskBlock {

    private final int capacity;
    private final List<Integer> files;

    public DiskBlock(int capacity) {
        this.capacity = capacity;
        files = new ArrayList<>();
    }

    public int getFreeSpace() {
        return capacity - files.size();
    }

    public int getNumberOfFiles() {
        return files.size();
    }

    public void addFiles(int fileId, int count) {
        while (count > 0) {
            files.add(fileId);
            count--;
        }
    }

    public void addFiles(List<Integer> files) {
        this.files.addAll(files);
    }

    public void clearFiles() {
        files.clear();
    }

    public List<Integer> getFileSystem() {
        List<Integer> fileSystem = new ArrayList<>(files);
        while (fileSystem.size() < capacity) fileSystem.add(0);
        return fileSystem;
    }
}
