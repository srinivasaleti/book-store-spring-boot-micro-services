package com.srinivas.bookstore.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
  @SequenceGenerator(
      name = "product_id_seq",
      sequenceName = "product_id_sequence",
      allocationSize = 1)
  private Long id;

  @NotNull(message = "Code cannot be null")
  @Column(nullable = false, unique = true)
  private String code;

  @NotNull(message = "Name cannot be null")
  @Column(nullable = false)
  private String name;

  @Column private String description;

  @Column(name = "image_url")
  private String imageUrl;

  @NotNull(message = "Price cannot be null")
  @Positive(message = "Price must be positive")
  @Column(nullable = false)
  private Double price;
}
