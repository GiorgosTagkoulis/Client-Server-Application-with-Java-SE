package com.georgios.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int numberOfThreads = 100;
    private final int port;
    private final String fileName = "server/src/main/resources/islands_in_the_stream.txt";

    public Server(int port) {
        this.port = port;
    }

    public void serve() throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(port));
            System.out.println("Server:Listening on port " + port);

            while (true) {
                final SocketChannel clientChannel = serverChannel.accept();
                System.out.println("Server:Accepted connection from " + clientChannel);
                executor.submit(() -> {
                    try {
                        FileHandler fileHandler = new FileHandler();
                        ByteBuffer buffer = fileHandler.readFile(fileName);
                        while (buffer.hasRemaining()) {
                            clientChannel.write(buffer);
                        }
                    } catch (IOException e) {
                        System.err.println("Server:serve:IOException:FileHandler" + e.getMessage());
                    } finally {
                        try {
                            clientChannel.close();
                        } catch (IOException e) {
                            System.err.println("Server:serve:IOException:clientClose" + e.getMessage());
                        }
                    }
                });
            }
        } finally {
            executor.shutdown();
        }
    }
}
