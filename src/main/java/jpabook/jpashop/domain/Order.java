package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // name 지정을 안 해주면 order가 돼버린다.
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id") // name으로 그냥 id로 지정해줘도 되지만 다들 이걸 선호한다.
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    /* 다대일 관계
     * fetch의 값은 default가 사실 이미 FecthType.EAGER다.
     * order 조회 시 member를 join을 해서 쿼리 한 방에 같이 가져온다.
     * 이건 em.find() 한 건 조회할 때는 그렇게 되는데
     * JPQL select o From order o; -> SQL select * from order*/
    @JoinColumn(name = "member_id") // 매핑을 뭘로 할거냐의 의미. foreign key name이 member_id가 된다.
    private Member member;
    // 여기의 값을 세팅하면 member_id값이 다른 멤버로 변경된다.

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간. 시간과 분까지 표시해준다.
    // hibernate가 알아서 자동으로 지원해주는 클래스

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

}

/* 서로 양방향 연관관계(양방향 참조)일 때는 연관관계의 주인을 꼭 정해줘야한다!
 * DB의 FK는 Orders에 있는 member_id 하나밖에 없다.
 * Member와 Order의 관계를 변경하고 싶으면 FK인 member_id를 바꿔줘야 한다.
 * 회원 엔티티 분석에는 Member에도 orders 연관관계 필드가 있고, Order에도 Member라는 필드가 있다.
 * 그러면 JPA는 둘다 값을 확인해서 변경해줘야하나? 혼란스러워진다.
 * FK를 업데이트하는 것은 둘 중에서 하나만 선택하기로 JPA에서 약속을 했다.
 * 객체는 변경 포인트가 2개지만, 테이블은 FK 하나만 바꾸면 된다.
 * 그래서 둘 중 하나를 주인이라는 개념으로 잡아주고(연관관계 주인)
 * orders나 member 중에서 누구의 값이 변경됐을 때 저 FK를 바꿔줄거야라고 지정해주는데 그것이 연관관계의 주인이다.
 * 그러면 연관관계의 주인은? FK가 가까운 곳으로 지정하면 된다.
 * member_id로 FK를 갖고 있는 orders가 연관관계의 주인이다.
 * */