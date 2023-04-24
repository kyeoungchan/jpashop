package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    /* 하나의 order는 여러 개의 order_item을 가질 수 있다.
    * 반대로 order_item은 하나의 order만 가질 수 있다.
    * 매핑은 member와 orders 관계처럼 양방향 연관관계다.
    * 테이블을 보면 당연히 다에 FK가 order_id로 들어가있다.
    * 따라서 order_item이 연관관계의 주인*/
    private Order order;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량
}
