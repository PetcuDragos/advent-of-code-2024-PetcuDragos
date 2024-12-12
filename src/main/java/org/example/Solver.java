package org.example;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    public long computeChecksum(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        List<DiskBlock> diskDiskBlocks = getDiskBlocks(lines.get(0));
        processBlocks(diskDiskBlocks);

        List<Integer> compactFileSystem = getCompactFileSystem(diskDiskBlocks);
        return calculateCheckSum(compactFileSystem);
    }

    private void processBlocks(List<DiskBlock> diskBlocks) {
        int diskBlockIndex = 0;
        while (diskBlockIndex < diskBlocks.size()) {
            if (!canBlockBeProcessed(diskBlocks, diskBlockIndex)) diskBlockIndex++;
        }
    }

    private boolean canBlockBeProcessed(List<DiskBlock> diskBlocks, int diskBlockIndex) {
        int maxFileNumberThatCanFitInBlock = diskBlocks.get(diskBlockIndex).getFreeSpace();
        if (maxFileNumberThatCanFitInBlock == 0) return false;

        int furthestDiskBlockThatCanBeMoved =
                getFurthestDiskBlockThatCanBeMoved(diskBlocks, diskBlockIndex, maxFileNumberThatCanFitInBlock);

        if (furthestDiskBlockThatCanBeMoved == -1) return false;

        reallocateMemory(diskBlocks, diskBlockIndex, furthestDiskBlockThatCanBeMoved);
        return true;
    }

    private void reallocateMemory(List<DiskBlock> diskBlocks, int toBlockIndex, int fromBlockIndex) {
        DiskBlock toBlock = diskBlocks.get(toBlockIndex);
        DiskBlock fromBlock = diskBlocks.get(fromBlockIndex);
        toBlock.addFiles(fromBlock.getFileSystem());
        fromBlock.clearFiles();
    }

    private int getFurthestDiskBlockThatCanBeMoved(List<DiskBlock> diskBlocks, int diskBlockIndex, int maxFileNumber) {
        for (int blockIndex = diskBlocks.size() - 1; blockIndex > diskBlockIndex; blockIndex--) {
            int numberOfFiles = diskBlocks.get(blockIndex).getNumberOfFiles();
            if (numberOfFiles > 0 && numberOfFiles <= maxFileNumber) {
                return blockIndex;
            }
        }
        return -1;
    }

    private Integer getFileSystemId(int diskBlockIndex) {
        return diskBlockIndex / 2;
    }

    private boolean isMemoryDiskBlock(int diskBlockIndex) {
        return diskBlockIndex % 2 == 0;
    }

    private long calculateCheckSum(List<Integer> compactFileSystem) {
        long checksum = 0;
        for (int index = 0; index < compactFileSystem.size(); index++) {
            checksum += (long) index * compactFileSystem.get(index);
        }
        return checksum;
    }

    private List<Integer> getCompactFileSystem(List<DiskBlock> diskDiskBlocks) {
        return diskDiskBlocks.stream()
                .flatMap(diskBlock -> diskBlock.getFileSystem().stream()).toList();
    }

    private List<DiskBlock> getDiskBlocks(String diskMap) {
        List<DiskBlock> diskDiskBlocks = new ArrayList<>();
        for (int index = 0; index < diskMap.length(); index++) {
            int capacity = Integer.parseInt(String.valueOf(diskMap.charAt(index)));
            DiskBlock diskBlock = new DiskBlock(capacity);
            if (isMemoryDiskBlock(index)) diskBlock.addFiles(getFileSystemId(index), capacity);
            diskDiskBlocks.add(diskBlock);
        }
        return diskDiskBlocks;
    }
}
