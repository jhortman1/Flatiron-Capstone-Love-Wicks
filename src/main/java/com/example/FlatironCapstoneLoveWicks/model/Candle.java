package com.example.FlatironCapstoneLoveWicks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @Column(name = "picByte", length = 1000)
    private byte[] photoId;
    @NotNull
    private double price;
    @NotNull
    private boolean inStock;
    @OneToMany(mappedBy = "candle", cascade = CascadeType.ALL)
    List<OrderDetails> orderDetails = new ArrayList<>();
}
