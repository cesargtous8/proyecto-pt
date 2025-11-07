package com.fullstack.inventory.dto;

public class InventoryResponse {
    private String productCode;
    private int quantity;
    private double price;
    private int reservedQuantity;

    // Constructor por defecto
    public InventoryResponse() {}

    // Constructor con parámetros
    public InventoryResponse(String productCode, int quantity, double price, int reservedQuantity) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
        this.reservedQuantity = reservedQuantity;
    }

    // Getters y Setters
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    // Método builder estático (alternativa sin Lombok)
    public static InventoryResponseBuilder builder() {
        return new InventoryResponseBuilder();
    }

    // Clase Builder interna
    public static class InventoryResponseBuilder {
        private String productCode;
        private int quantity;
        private double price;
        private int reservedQuantity;

        public InventoryResponseBuilder productCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public InventoryResponseBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public InventoryResponseBuilder price(double price) {
            this.price = price;
            return this;
        }

        public InventoryResponseBuilder reservedQuantity(int reservedQuantity) {
            this.reservedQuantity = reservedQuantity;
            return this;
        }

        public InventoryResponse build() {
            return new InventoryResponse(productCode, quantity, price, reservedQuantity);
        }
    }
}