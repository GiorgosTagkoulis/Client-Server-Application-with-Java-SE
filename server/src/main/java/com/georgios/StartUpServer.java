package com.georgios;

import com.georgios.server.Server;
import java.io.IOException;

public class StartUpServer {
    public static void main(String[] args) {
        try {
            Server server = new Server(8000);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
