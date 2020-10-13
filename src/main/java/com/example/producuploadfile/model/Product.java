package com.example.producuploadfile.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;

    private String name;

    private String description;

    private String price;

    private String img;

    @ManyToOne
    @JoinColumn(name = "cId")
    private Category category;

    private Long quantity;

    @Transient
    private MultipartFile imgFile;

//    public Product(String name, String description, String price, String img, Category category, Long quantity) {
//        Name = name;
//        this.description = description;
//        this.price = price;
//        this.img = img;
//        this.category = category;
//        this.quantity = quantity;
//    }
//
//    public Product(String name, String description, String price, String img, Category category, Long quantity, MultipartFile imgFile) {
//        Name = name;
//        this.description = description;
//        this.price = price;
//        this.img = img;
//        this.category = category;
//        this.quantity = quantity;
//        this.imgFile = imgFile;
//    }
//
//    public Product(Long pId, String name, String description, String price, String img, Category category, Long quantity, MultipartFile imgFile) {
//        this.pId = pId;
//        Name = name;
//        this.description = description;
//        this.price = price;
//        this.img = img;
//        this.category = category;
//        this.quantity = quantity;
//        this.imgFile = imgFile;
//    }
//
//    public Product() {
//    }
//
//    public Long getpId() {
//        return pId;
//    }
//
//    public void setpId(Long pId) {
//        this.pId = pId;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Long getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Long quantity) {
//        this.quantity = quantity;
//    }
//
//    public MultipartFile getImgFile() {
//        return imgFile;
//    }
//
//    public void setImgFile(MultipartFile imgFile) {
//        this.imgFile = imgFile;
//    }
}
