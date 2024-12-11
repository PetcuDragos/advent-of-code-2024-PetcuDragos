package org.example;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    public long computeChecksum(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        List<Integer> diskBlocks = getDiskBlocks(lines.get(0));
        List<Integer> compactFileSystem = computeCompactFileSystem(diskBlocks);
        return calculateCheckSum(compactFileSystem);
    }

    private List<Integer> computeCompactFileSystem(List<Integer> diskBlocks) {
        List<Integer> compactFileSystem = new ArrayList<>();
        int diskBlockIndex = 0;

        while (diskBlockIndex < diskBlocks.size()) {

            if (isDiskBlockCompletelyProcessed(diskBlocks, diskBlockIndex)) {
                diskBlockIndex++;
            } else {
                processBlock(diskBlocks, diskBlockIndex, compactFileSystem);
            }
        }
        return compactFileSystem;
    }

    private void processBlock(List<Integer> diskBlocks, int diskBlockIndex, List<Integer> compactFileSystem) {
        decrementDiskBlockProcesses(diskBlocks, diskBlockIndex);

        if (isMemoryDiskBlock(diskBlockIndex)) {
            compactFileSystem.add(getFileSystemId(diskBlockIndex));
        } else {
            int lastAvailableMemoryBlockIndex = getLastAvailableMemoryBlockIndex(diskBlocks);

            if (lastAvailableMemoryBlockIndex == -1) {
                return;
            }

            processBlock(diskBlocks, lastAvailableMemoryBlockIndex, compactFileSystem);
        }
    }

    private int getLastAvailableMemoryBlockIndex(List<Integer> diskBlocks) {
        for (int blockIndex = diskBlocks.size() - 1; blockIndex >= 0; blockIndex--) {
            if (isMemoryDiskBlock(blockIndex)) {
                if (isDiskBlockCompletelyProcessed(diskBlocks, blockIndex)) {
                    diskBlocks.remove(blockIndex--);
                    diskBlocks.remove(blockIndex);
                } else {
                    return blockIndex;
                }
            }
        }
        return -1;
    }

    private void decrementDiskBlockProcesses(List<Integer> diskBlocks, int diskBlockIndex) {
        diskBlocks.set(diskBlockIndex, diskBlocks.get(diskBlockIndex) - 1);
    }

    private Integer getFileSystemId(int diskBlockIndex) {
        return diskBlockIndex / 2;
    }

    private boolean isMemoryDiskBlock(int diskBlockIndex) {
        return diskBlockIndex % 2 == 0;
    }

    private boolean isDiskBlockCompletelyProcessed(List<Integer> diskBlocks, int diskBlockIndex) {
        return diskBlocks.get(diskBlockIndex) == 0;
    }

    private long calculateCheckSum(List<Integer> compactFileSystem) {
        long checksum = 0;
        for (int index = 0; index < compactFileSystem.size(); index++) {
            checksum += (long) index * compactFileSystem.get(index);
        }
        return checksum;
    }

    private List<Integer> getDiskBlocks(String diskMap) {
        List<Integer> diskBlocks = new ArrayList<>();
        for (int index = 0; index < diskMap.length(); index++) {
            diskBlocks.add(Integer.parseInt(String.valueOf(diskMap.charAt(index))));
        }
        return diskBlocks;
    }
}
