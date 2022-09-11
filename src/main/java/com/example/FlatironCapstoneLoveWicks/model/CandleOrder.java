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
public class CandleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private AppUser appUser;
    @NotNull
    private Boolean open;
    @OneToMany(mappedBy = "candleOrder", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
