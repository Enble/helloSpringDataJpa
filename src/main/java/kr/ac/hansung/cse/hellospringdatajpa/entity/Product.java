package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "상품명은 필수입니다")
    @Size(min = 2, max = 100, message = "상품명은 2자 이상 100자 이하여야 합니다")
    private String name;

    @NotBlank(message = "브랜드는 필수입니다")
    @Size(min = 1, max = 50, message = "브랜드명은 1자 이상 50자 이하여야 합니다")
    private String brand;

    @NotBlank(message = "제조국은 필수입니다")
    @Size(min = 2, max = 50, message = "제조국은 2자 이상 50자 이하여야 합니다")
    private String madeIn;

    @DecimalMin(value = "0.0", inclusive = false, message = "가격은 0보다 커야 합니다")
    @NotNull(message = "가격은 필수입니다")
    private Double price;

    public Product(String name, String brand, String madeIn, double price) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
    }
}
