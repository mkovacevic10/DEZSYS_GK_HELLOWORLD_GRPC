import grpc
import json
from datawarehouse_pb2 import Warehouse, ProductData
from datawarehouse_pb2_grpc import DataWarehouseServiceStub


def pretty_json(data):
    return json.dumps(data, indent=4, ensure_ascii=False)


def main():
    channel = grpc.insecure_channel("127.0.0.1:50051")
    stub = DataWarehouseServiceStub(channel)

    warehouse = Warehouse(
        warehouseID="001",
        warehouseName="TGM Bahnhof",
        warehouseAddress="Wexstraße",
        warehousePostalCode="1210",
        warehouseCity="Wien",
        warehouseCountry="Österreich",
        timestamp="2025-12-02 15:02:57.163",
        productdata=[
            ProductData(
                productID="00-852374",
                productName="Apfelsaft",
                productCategory="Saft",
                productQuantity=1245,
                productUnit="Packung 1L"
            ),
            ProductData(
                productID="00-992100",
                productName="Mineralwasser",
                productCategory="Getränk",
                productQuantity=500,
                productUnit="Flasche 0.5L"
            )
        ]
    )

    response = stub.sendWarehouse(warehouse)

    print("\n===== Warehouse Sent =====\n")

    output = {
        "warehouseID": warehouse.warehouseID,
        "warehouseName": warehouse.warehouseName,
        "warehouseAddress": warehouse.warehouseAddress,
        "warehousePostalCode": warehouse.warehousePostalCode,
        "warehouseCity": warehouse.warehouseCity,
        "warehouseCountry": warehouse.warehouseCountry,
        "timestamp": warehouse.timestamp,
        "productdata": [
            {
                "productID": p.productID,
                "productName": p.productName,
                "productCategory": p.productCategory,
                "productQuantity": p.productQuantity,
                "productUnit": p.productUnit,
            }
            for p in warehouse.productdata
        ]
    }

    print(pretty_json(output))

    print("\n===== Server Response =====")
    print("Message:", response.message)


if __name__ == "__main__":
    main()
