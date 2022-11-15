package com.encuentralofacil.encuentralofacil.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String id;
    @Column
    private String name;
}
