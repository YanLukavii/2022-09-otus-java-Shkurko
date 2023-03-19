package ru.otus.protobuf;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumberResponse;
import ru.otus.protobuf.generated.NumbersServiceGrpc;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class NumbersServiceImpl extends NumbersServiceGrpc.NumbersServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(NumbersServiceImpl.class);

    @Override
    public void number(NumberRequest request, StreamObserver<NumberResponse> responseStreamObserver) {

        log.info("request for the new seq. of numbers firstVal:{} lastVal:{} ", request.getFirstVal(),
                request.getLastVal());
        var currentVal = new AtomicLong(request.getFirstVal());

        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            var value = currentVal.incrementAndGet();
            var response = NumberResponse.newBuilder().setNumber(value).build();
            responseStreamObserver.onNext(response);
            if (value == request.getLastVal()) {
                executor.shutdown();
                responseStreamObserver.onCompleted();
                log.info("seq. of numbers finished");
            }
        };
        executor.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
    }
}
