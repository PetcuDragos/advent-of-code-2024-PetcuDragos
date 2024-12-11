package org.example;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    public long computeChecksum(String inputFileName) {
        List<String> lines = FileReaderUtil.readLinesFromFile(inputFileName);

        String diskMap = lines.get(0);
        List<Integer> memoryBlocks = getMemoryBlocks(diskMap);
        List<Integer> freeSpaceBlocks = getFreeSpaceBlocks(diskMap);

        return computeChecksum(memoryBlocks, freeSpaceBlocks);
    }

    private long computeChecksum(List<Integer> memoryBlocks, List<Integer> freeSpaceBlocks) {
        int memoryBlockIndex = 0;
        int blockPosition = 0;
        long checksum = 0;

        while (memoryBlockIndex < memoryBlocks.size()) {
            int memoryBlock = memoryBlocks.get(memoryBlockIndex);
            int freeSpaceBlock = freeSpaceBlocks.size() > memoryBlockIndex ? freeSpaceBlocks.get(memoryBlockIndex) : 0;

            while (memoryBlock > 0) {
                checksum += (long) blockPosition * memoryBlockIndex;
                blockPosition++;
                memoryBlock--;
            }

            if (memoryBlocks.size() - 1 == memoryBlockIndex) break;
            while (freeSpaceBlock > 0) {
                int lastMemoryAvailable = getLastMemoryAvailable(memoryBlocks);
                checksum += (long) blockPosition * lastMemoryAvailable;
                blockPosition++;
                freeSpaceBlock--;
            }

            memoryBlockIndex++;
        }
        return checksum;
    }

    private int getLastMemoryAvailable(List<Integer> memoryBlocks) {
        Integer lastMemoryBlock = memoryBlocks.get(memoryBlocks.size() - 1);
        while (lastMemoryBlock == 0) {
            memoryBlocks.remove(memoryBlocks.size() - 1);
            if (memoryBlocks.size() == 0) return 0;
            lastMemoryBlock = memoryBlocks.get(memoryBlocks.size() - 1);
        }
        memoryBlocks.set(memoryBlocks.size() - 1, memoryBlocks.get(memoryBlocks.size() - 1) - 1);
        return memoryBlocks.size() - 1;
    }

    private List<Integer> getMemoryBlocks(String diskMap) {
        List<Integer> memoryBlocks = new ArrayList<>();
        for (int index = 0; index < diskMap.length(); index += 2) {
            memoryBlocks.add(Integer.parseInt(String.valueOf(diskMap.charAt(index))));
        }
        return memoryBlocks;
    }

    private List<Integer> getFreeSpaceBlocks(String diskMap) {
        List<Integer> freeSpaceBlocks = new ArrayList<>();
        for (int index = 1; index < diskMap.length(); index += 2) {
            freeSpaceBlocks.add(Integer.parseInt(String.valueOf(diskMap.charAt(index))));
        }
        return freeSpaceBlocks;
    }


}
