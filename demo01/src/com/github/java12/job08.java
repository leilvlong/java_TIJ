package com.github.java12;

import sun.nio.cs.Surrogate;

import java.util.ArrayList;
import java.util.Random;


/*
泛型容器在多重嵌套中持有对象依然是安全与可管理的:
    层层嵌套,每一层都管理自己的泛型
 */
public class job08 {
    public static void main(String[] args) {
        System.out.println(new Store(14, 5, 10));


    }
}


class Product {
    private static int counter;
    private final int id = counter++;
    private String description;
    private double price;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public Product(String description, double price) {
        this.description = description;
        this.price = price;

    }

    public void priceChange(double d) {
        price += d;
    }

    public static Generator<Product> generator = new Generator<Product>() {

        private Random random = new Random(47);

        @Override
        public Product next() {
            return new Product("Test", Math.round(random.nextDouble() * 1000) + 0.99);
        }
    };
}


class Shelf extends ArrayList<Product> {
    public Shelf(int i) {
        job04.fill(this, Product.generator, i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shelf[");
        for (Product product : this) {
            sb.append(product.toString()).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }
}


class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
        for (int i = 0; i < nShelves; i++) {
            add(new Shelf(nProducts));
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aisle[");
        for (Shelf shelf : this) {
            sb.append(shelf.toString()).append(",\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Aisle(5, 2));
    }

}


class CheckoutStand {
};


class Office {
};


class Store extends ArrayList<Aisle> {

    private ArrayList<CheckoutStand> checkoutStands = new ArrayList<>();
    private Office office = new Office();

    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Store[");
        for (Aisle aisle : this) {
            sb.append(aisle.toString()).append(",\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        return sb.toString();
    }


}