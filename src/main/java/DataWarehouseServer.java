import io.grpc.Server;
import io.grpc.ServerBuilder;

public class DataWarehouseServer {
    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder.forPort(50051)
                .addService(new DataWarehouseServiceImpl())
                .build();

        System.out.println("DataWarehouse gRPC Server running...");
        server.start();
        server.awaitTermination();
    }
}
