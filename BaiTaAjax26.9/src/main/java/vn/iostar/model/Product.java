package vn.iostar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private Integer quantity;
    @Column(name = "description")
    private String desc;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // --- SỬA HÀM KHỞI TẠO Ở ĐÂY ---
    public Product(String title, Integer quantity, String desc, Double price, User user, Category category) {
        this.title = title;
        this.quantity = quantity;
        this.desc = desc;
        this.price = price;
        this.user = user;
        this.category = category; // Thêm dòng này
    }
}