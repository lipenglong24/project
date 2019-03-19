package com.lipenglong.java.designmode.structure.facade;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class Computer {
    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    Computer() {
        cpu = new Cpu();
        memory = new Memory();
        disk = new Disk();
    }

    void start() {
        System.out.println("computer start begin");
        cpu.start();
        memory.start();
        disk.start();
        System.out.println("computer start end");
    }

    void shutDown() {
        System.out.println("computer shutDown begin");
        cpu.shutDown();
        memory.shutDown();
        disk.shutDown();
        System.out.println("computer shutDown end");
    }
}
