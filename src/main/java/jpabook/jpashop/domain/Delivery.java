package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    /* 일대일 관게에서는 FK를 양쪽 다 줄 수 있는데
    * 주로 Acccess를 많이 하는 곳에 주는 것이 유리하다.
    * 주로 Order를 보면서 Delivery를 찾지, Delivery를 갖고 Order를 찾는 경우는 드물다.
    * 그래서 Order에다가 FK를 두는 편이다.
    * 그러므로 연관관계를 Order에 있는 Delivery에 주면 된다.*/
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    /* Enum 타입을 사용할 때 꼭 넣어야 하는 어노테이션!
    * Ordinary : default. 숫자로 들어간다.
    * READY가 1이고, COMP가 2 이런식으로. 그런데 중간에 다른 상태가 생기면 망한다.
    * COMP가 3으로 밀릴 수 있으므로.
    * 그러므로 Ordinary를 절대 쓰지 말고 String으로 써야한다.*/
    private DeliveryStatus status; // READY, COMP
}
