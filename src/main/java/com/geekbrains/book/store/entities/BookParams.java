package com.geekbrains.book.store.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
@NoArgsConstructor
public class BookParams {

    private int pageNumber;
    private int minPrice;
    private int maxPrice;
    private String name;

}
