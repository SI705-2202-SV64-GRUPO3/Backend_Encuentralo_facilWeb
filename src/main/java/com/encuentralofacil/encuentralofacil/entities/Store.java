package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String district;
    @Column
    private String email;
    @Column
    private String password;
}
