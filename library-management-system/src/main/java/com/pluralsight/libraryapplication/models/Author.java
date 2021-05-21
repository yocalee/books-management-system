package com.pluralsight.libraryapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends BaseEntity{
    @Column(name = "full_name")
    @Getter @Setter
    private String fullName;
    @OneToMany(mappedBy = "author")
    @Getter @Setter
    private Set<Book> books;
}
