package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")// 싱글테이블인데 저장될 때 DB 입장에서 구분이 돼야하는데 이게 그 기능을 한다.
@Getter @Setter
public class Book extends Item {
    private String author;
    private String isbn;
}
