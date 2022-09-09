//package com.example.FlatironCapstoneLoveWicks.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @ManyToOne
//    private AppUser appUser;
//    @OneToMany(mappedBy = "order")
//    private List<OrderDetails> orderDetails = new ArrayList<>();
//}
