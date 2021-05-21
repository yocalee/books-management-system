package com.pluralsight.libraryapplication.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Getter @Setter
    private String currency;
    @Getter @Setter
    private double value;
    @Getter @Setter
    private String displayValue;
}
