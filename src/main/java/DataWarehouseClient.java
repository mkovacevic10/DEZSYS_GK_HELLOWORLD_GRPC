import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DataWarehouseClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 50051)
                .usePlaintext()
                .build();

        DataWarehouseServiceGrpc.DataWarehouseServiceBlockingStub stub =
                DataWarehouseServiceGrpc.newBlockingStub(channel);

        Datawarehouse.ProductData product1 = Datawarehouse.ProductData.newBuilder()
                .setProductID("00-852374")
                .setProductName("Apfelsaft")
                .setProductCategory("Saft")
                .setProductQuantity(1245)
                .setProductUnit("Packung 1L")
                .build();

        Datawarehouse.ProductData product2 = Datawarehouse.ProductData.newBuilder()
                .setProductID("00-992100")
                .setProductName("Mineralwasser")
                .setProductCategory("Getränk")
                .setProductQuantity(500)
                .setProductUnit("Flasche 0.5L")
                .build();

        Datawarehouse.Warehouse warehouse = Datawarehouse.Warehouse.newBuilder()
                .setWarehouseID("001")
                .setWarehouseName("TGM Bahnhof")
                .setWarehouseAddress("Wexstraße")
                .setWarehousePostalCode("1210")
                .setWarehouseCity("Wien")
                .setWarehouseCountry("Österreich")
                .setTimestamp("2025-12-02 15:02:57.163")
                .addProductdata(product1)
                .addProductdata(product2)
                .build();

        stub.sendWarehouse(warehouse);

        System.out.println("=== Produktliste ===\n");

        for (Datawarehouse.ProductData p : warehouse.getProductdataList()) {
            System.out.println("ProduktID:      " + p.getProductID());
            System.out.println("Name:           " + p.getProductName());
            System.out.println("Kategorie:      " + p.getProductCategory());
            System.out.println("Menge:          " + p.getProductQuantity());
            System.out.println("Einheit:        " + p.getProductUnit());
            System.out.println("-----------------------------------");
        }

        channel.shutdown();
    }
}
