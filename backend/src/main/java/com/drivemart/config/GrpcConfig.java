package com.drivemart.config;

import com.drivemart.grpc.OrderServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public Server grpcServer() {
        return ServerBuilder.forPort(9090)
                .addService(new OrderServiceImpl()) // Replace with your gRPC service implementation
                .build();
    }
}
