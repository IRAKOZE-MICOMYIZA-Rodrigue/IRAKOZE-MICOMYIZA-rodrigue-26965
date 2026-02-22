package auca.ac.rw.restfullApiAssignment.modal;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "product_id")
    private Long id;

    @Column(name="product_name", unique = true, nullable =false )
    private String name;

    @Column(name="product_description")
    private String description;

    @Column(name="product_price")
    private double price;

    @Column(name="product_category")
    private String category;

    @Column(name="product_stock_quantity")
    private int stockQuantity;

    @Column(name="product_brand")
    private String brand;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


}
