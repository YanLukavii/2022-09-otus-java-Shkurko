package ru.otus.protobuf;


import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NumbersServer {

    private static final Logger log = LoggerFactory.getLogger(NumbersServer.class);
    public static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws IOException, InterruptedException {

        log.info("numbers Server is starting...");

        var server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new NumbersServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Received shutdown request");
            server.shutdown();
            log.info("Server stopped");
        }));

        log.info("server waiting for client connections, port:{}", SERVER_PORT);
        server.awaitTermination();
    }
}
