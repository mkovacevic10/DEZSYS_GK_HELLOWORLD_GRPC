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
                .setProductCategory("Getraenk")
                .setProductQuantity(500)
                .setProductUnit("Flasche 0.5L")
                .build();

        Datawarehouse.Warehouse warehouse = Datawarehouse.Warehouse.newBuilder()
                .setWarehouseID("001")
                .setWarehouseName("TGM Bahnhof")
                .setWarehouseAddress("Wexstrasse")
                .setWarehousePostalCode("1210")
                .setWarehouseCity("Wien")
                .setWarehouseCountry("Oesterreich")
                .setTimestamp("2025-12-02 15:02:57.163")
                .addProductdata(product1)
                .addProductdata(product2)
                .build();

        stub.sendWarehouse(warehouse);

        System.out.println("=== Warehouse ===");
        System.out.println();
        System.out.println("ID:        " + warehouse.getWarehouseID());
        System.out.println("Name:      " + warehouse.getWarehouseName());
        System.out.println("Adresse:   " + warehouse.getWarehouseAddress());
        System.out.println("PLZ/Ort:   " + warehouse.getWarehousePostalCode() + " " + warehouse.getWarehouseCity());
        System.out.println("Land:      " + warehouse.getWarehouseCountry());
        System.out.println("Zeit:      " + warehouse.getTimestamp());
        System.out.println();

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
