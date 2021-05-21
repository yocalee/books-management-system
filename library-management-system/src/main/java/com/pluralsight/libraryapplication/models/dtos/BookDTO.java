package com.pluralsight.libraryapplication.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @Getter @Setter
    private Long ISBN;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String summary;
    @Getter @Setter
    private String image;
    @Getter @Setter
    private Price price;
    @Getter @Setter
    private String author;

    @Override
    public String toString() {
        return "BookDTO{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }
}
