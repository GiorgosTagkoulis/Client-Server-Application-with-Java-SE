package com.georgios.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {
    private final String host;
    private final int port;

    private final String fileName = "client/src/main/resources/islands_in_the_stream.txt";

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        try (SocketChannel client = SocketChannel.open(new InetSocketAddress(host, port))) {
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            FileChannel outChannel = FileChannel.open(Paths.get(fileName),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            while (client.read(buffer) > 0) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            WordCounter wordCounter = new WordCounter();
            wordCounter.countWords(fileName);
        }
    }
}
