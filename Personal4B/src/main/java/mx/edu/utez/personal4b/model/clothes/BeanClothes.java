package mx.edu.utez.personal4b.model.clothes;

import mx.edu.utez.personal4b.model.categories.BeanCategories;
import mx.edu.utez.personal4b.model.clothe_types.BeanClotheTypes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanClothes {
    private Long id;
    private String name;
    private double price;
    private String size;
    private String brand;
    private int stock;
    private int status;
    private String clothType;
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    private BeanCategories categories;
    private BeanClotheTypes clotheTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClothType() {
        return clothType;
    }

    public void setClothType(String clothType) {
        this.clothType = clothType;
    }

    public BeanCategories getCategories() {
        return categories;
    }

    public void setCategories(BeanCategories categories) {
        this.categories = categories;
    }

    public BeanClotheTypes getClotheTypes() {
        return clotheTypes;
    }

    public void setClotheTypes(BeanClotheTypes clotheTypes) {
        this.clotheTypes = clotheTypes;
    }
}