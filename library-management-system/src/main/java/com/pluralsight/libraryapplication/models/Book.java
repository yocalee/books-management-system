package com.pluralsight.libraryapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity{
    @Getter @Setter
    private Long ISBN;
    @Getter @Setter
    private String title;
    @Getter @Setter
    @Column(length = 80)
    private String summary;
    @Getter @Setter
    @Column(name = "image_url")
    private String imageUrl;
    @Getter @Setter
    private double price;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

}
