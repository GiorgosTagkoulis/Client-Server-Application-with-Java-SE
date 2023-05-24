package com.georgios;

import java.io.IOException;

import com.georgios.client.Client;

public class StartUpClient {
    private static final String host = "localhost";
    private static final int port = 8000;

    public static void main(String[] args) {

        Client client = new Client(host, port);

        try {
            client.start();
        } catch (IOException e) {
            System.out.println("StartUpClient:main:IOException:" + e.getMessage());
        }
    }
}
