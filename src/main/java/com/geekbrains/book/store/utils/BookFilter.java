package com.geekbrains.book.store.utils;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private StringBuilder filterDefinition;

    public BookFilter(Map<String, String> map, List<String> categories) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (categories!=null){
            for (String s: categories){
                Book.Category category = Book.Category.valueOf(s);
                spec = spec.or(BookSpecifications.categoryEquals(category));
                filterDefinition.append("&category=").append(s);
            }
        }
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if (map.containsKey("name_param") && !map.get("name_param").isEmpty()) {
            spec = spec.and(BookSpecifications.titleLike(map.get("name_param")));
            filterDefinition.append("&name_param=").append(map.get("name_param"));
        }
    }
}
