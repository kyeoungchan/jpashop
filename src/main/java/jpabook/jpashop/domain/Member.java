package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id") // 그냥 두면 id로 이름이 된다.
    private Long id;

    private String name;

    @Embedded // 내장 타입을 포함했다는 어노테이션. @Embeddable과 더불어 둘 중 하나만 있어도 된다.
    private Address address;

    @OneToMany(mappedBy = "member")
    /* 일대다의 관계
    * order 테이블에 있는 member 필드에 의해서 나는 매핑됐다.라는 표현
    * 나는 매핑을 하는 애가 아니라 누군가에 의해서 mapped된 거울일 뿐이라고 표현하는 것
    * 따라서 여기에 값을 넣는다고 해서 FK 값이 변겨오디지 않는다.*/
    private List<Order> orders = new ArrayList<>();
}
