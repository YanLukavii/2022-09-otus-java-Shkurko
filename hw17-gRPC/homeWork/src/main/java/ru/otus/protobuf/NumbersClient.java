package ru.otus.protobuf;

import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumbersServiceGrpc;

public class NumbersClient {

    private static final Logger log = LoggerFactory.getLogger(NumbersClient.class);
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;
    private static final int LOOP_LIMIT = 50;

    private long value = 0;

    public static void main(String[] args) {

        log.info("Numbers client is starting...");
        var managedChannel = ManagedChannelBuilder
                .forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        var asyncClient = NumbersServiceGrpc.newStub(managedChannel);
        new NumbersClient().clientAction(asyncClient);


        log.info("numbers client is shutting down...");
        managedChannel.shutdown();
    }

    private void clientAction(NumbersServiceGrpc.NumbersServiceStub asyncClient) {

        var numberRequest = NumberRequest.newBuilder()
                .setFirstVal(0)
                .setLastVal(30)
                .build();

        var clientStreamObserver = new ClientStreamObserver();
        asyncClient.number(numberRequest, clientStreamObserver);

        for (var idx = 0; idx < LOOP_LIMIT; idx++) {
            var valForPrint = getNextValue(clientStreamObserver);
            log.info("currentValue:{}", valForPrint);
            sleep();
        }
    }

    private long getNextValue(ClientStreamObserver clientStreamObserver) {
        value = value + clientStreamObserver.getLastValueAndReset() + 1;
        return value;
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
