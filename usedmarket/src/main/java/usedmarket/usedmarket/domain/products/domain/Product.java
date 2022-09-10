package usedmarket.usedmarket.domain.products.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedmarket.usedmarket.domain.productLike.domain.ProductLike;
import usedmarket.usedmarket.domain.category.domain.Category;
import usedmarket.usedmarket.domain.comment.domain.Comment;
import usedmarket.usedmarket.domain.member.domain.Member;
import usedmarket.usedmarket.global.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRODUCTS")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    private String imgName;

    private String imgPath;

    private int price;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private final List<ProductLike> productLikeList = new ArrayList<>();

    @Builder
    public Product(String title, String imgName, String imgPath, int price, String content) {
        this.title = title;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.price = price;
        this.content = content;
    }

    public void updateProduct(String title, String imgName, String imgPath, int price, String content) {
        this.title = title;
        this.imgName = imgName;
        this.imgPath = imgPath;
        this.price = price;
        this.content = content;
    }

    public void confirmWriter(Member writer) {
        this.writer = writer;
        writer.addProduct(this);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    public void addProductLike(ProductLike productLike) {
        productLikeList.add(productLike);
    }

    public void addSaleStatus() {
        this.productStatus = ProductStatus.SALE;
    }

    public void updateStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

}