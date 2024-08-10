package com.drivemart.grpc;

import com.drivemart.grpc.OrderServiceProto.OrderIdRequest;
import com.drivemart.grpc.OrderServiceProto.OrderRequest;
import com.drivemart.grpc.OrderServiceProto.OrderResponse;
import io.grpc.stub.StreamObserver;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void createOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        // Реализация создания заказа
        OrderResponse response = OrderResponse.newBuilder()
                .setOrderId("12345")
                .setDescription(request.getDescription())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(OrderIdRequest request, StreamObserver<OrderResponse> responseObserver) {
        // Реализация получения заказа
        OrderResponse response = OrderResponse.newBuilder()
                .setOrderId(request.getOrderId())
                .setDescription("Sample Order")
                .setQuantity(1)
                .setPrice(100.0)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
