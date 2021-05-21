package com.pluralsight.libraryapplication.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    @Getter @Setter
    private String name;
}
