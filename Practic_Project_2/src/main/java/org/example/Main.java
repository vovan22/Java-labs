package org.example;

public class Main {
    public static void main(String[] args) {
        // Створення замовлень
        OrderProcessor<Electronics> electronicsOrder = new OrderProcessor<>(new Electronics("Mobile phone"));
        OrderProcessor<Clothing> clothingOrder = new OrderProcessor<>(new Clothing("Trousers"));

        // Обробка замовлень
        electronicsOrder.process();
        clothingOrder.process();
    }
}
