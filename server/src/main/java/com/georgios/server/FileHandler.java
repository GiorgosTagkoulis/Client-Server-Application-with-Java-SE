package com.georgios.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileHandler {

    public static void main(String[] args) {
        System.out.println("FileHandler");
        FileHandler fileHandler = new FileHandler();
        try {
            ByteBuffer byteBuffer = fileHandler.readFile("server/src/main/resources/islands_in_the_stream.txt");
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
        } catch (IOException e) {
            System.out.println("FileHandler:main:IOException:" + e.getMessage());
        }
    }

    public ByteBuffer readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (FileChannel fileChannel = FileChannel.open(path)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            return byteBuffer;
        }
    }
}
