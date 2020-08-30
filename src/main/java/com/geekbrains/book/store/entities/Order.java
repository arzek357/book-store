package com.geekbrains.book.store.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="orders")
public class Order {

        public enum Status{
            IN_PROGRESS,SUCCESS,CANCELLED
        }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(User user) {
        this.user=user;
        this.status=Status.IN_PROGRESS;
    }
}
