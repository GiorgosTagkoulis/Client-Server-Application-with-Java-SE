package com.georgios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.georgios.client.Client;

public class StartUpClient {
    private String host;
    private final int port = 8000;

    void setHost() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String temp;
            while (true) {
                System.out.println(
                        "If you want to exit, type: exit()\nMake sure the server is running locally.\nDo you want to connect to localhost?\nType 'y':");
                temp = reader.readLine();
                if (temp.equals("exit()")) {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                if (!temp.equals("y")) {
                    System.out.println("Invalid input. Please try again.\n");
                    continue;
                }
                if (temp.equals("y")) {
                    host = "localhost";
                    break;
                } else if (temp.equals("no")) {
                    continue;
                }
            }

        } catch (IOException e) {
            System.out.println("StartUpClient:setPort:IOException:" + e.getMessage());
            System.out.println("Defaulting to localhost");
            host = "localhost";
        }
    }

    String getHost() {
        return this.host;
    }

    int getPort() {
        return this.port;
    }

    public static void main(String[] args) {

        StartUpClient startUpClient = new StartUpClient();

        while (true) {
            try {
                startUpClient.setHost();
                Client client = new Client(startUpClient.getHost(), startUpClient.getPort());
                client.start();
                System.out.println();
            } catch (IOException e) {
                System.out.println("StartUpClient:main:IOException:" + e.getMessage());
            }
        }

    }
}
