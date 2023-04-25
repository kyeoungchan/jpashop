package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;


    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    /* 다대다 관계도 연관관계 주인을 정하고 해야 한다.
     * JoinTable이 필요한 이유: 중간테이블 매핑이 필요하므로.
     * 객체는 컬렉션이 있어서 다대다 관계가 가능하지만
     * 관계형 DB는 컬렉션 관계를 양쪽에 가질 수 없으므로 일대다 다대일로 풀어내주는 중간 테이블이 있어야 가능하다.
     * 테이블 설계도에 보면 Category_item 테이블을 설계해주는 작업
     * 실전에서는 쓰지말라고 하는데, 이 그림밖에 안 되고, 더 필드 추가가 불가능하므로 실무에서 거의 못 쓴다.
     * 중간에 등록한 날짜 등 무언가를 넣어줘야하지만 안 된다.*/
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent; // 부모는 내 타입

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
