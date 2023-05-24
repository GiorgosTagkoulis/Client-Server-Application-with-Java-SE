package com.georgios.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileHandler {

    ByteBuffer readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        try (FileChannel fileChannel = FileChannel.open(path)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            return byteBuffer;
        }
    }
}
