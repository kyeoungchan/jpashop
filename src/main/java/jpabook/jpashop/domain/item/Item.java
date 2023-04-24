package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    // 구현체가 필요하므로 추상클래스로 선언
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}

/* 상속관계 매핑이기 때문에 전략을 지정해야하는데
* 그것을 부모클래스에서 잡아줘야한다.
* 우리는 싱글 테이블 전략을 사용할 예정이다.
* => @Inheritance(strategy = )*/
