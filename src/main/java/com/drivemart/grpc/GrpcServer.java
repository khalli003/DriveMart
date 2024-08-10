package com.drivemart.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;


public class GrpcServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(8080)
                .addService(new OrderServiceImpl())
                .build()
                .start();

        System.out.println("Server started, listening on " + server.getPort());
        server.awaitTermination();
    }
}
