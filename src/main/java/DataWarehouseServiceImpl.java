import io.grpc.stub.StreamObserver;

public class DataWarehouseServiceImpl
        extends DataWarehouseServiceGrpc.DataWarehouseServiceImplBase {

    @Override
    public void sendWarehouse(Datawarehouse.Warehouse request,
                              StreamObserver<Datawarehouse.Ack> responseObserver) {

        System.out.println("===== RECEIVED WAREHOUSE FROM PYTHON =====");

        System.out.println("warehouseID:       " + request.getWarehouseID());
        System.out.println("warehouseName:     " + request.getWarehouseName());
        System.out.println("warehouseAddress:  " + request.getWarehouseAddress());
        System.out.println("warehousePostCode: " + request.getWarehousePostalCode());
        System.out.println("warehouseCity:     " + request.getWarehouseCity());
        System.out.println("warehouseCountry:  " + request.getWarehouseCountry());
        System.out.println("timestamp:         " + request.getTimestamp());

        System.out.println("\nProducts:");

        for (Datawarehouse.ProductData p : request.getProductdataList()) {
            System.out.println("-----------------------------------");
            System.out.println("productID:       " + p.getProductID());
            System.out.println("productName:     " + p.getProductName());
            System.out.println("productCategory: " + p.getProductCategory());
            System.out.println("productQuantity: " + p.getProductQuantity());
            System.out.println("productUnit:     " + p.getProductUnit());
        }

        Datawarehouse.Ack ack = Datawarehouse.Ack.newBuilder()
                .setMessage("Warehouse & product data received successfully")
                .build();

        responseObserver.onNext(ack);
        responseObserver.onCompleted();
    }
}
