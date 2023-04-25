package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장타입 => 어딘가에 내장이 될 수 있다.
@Getter
public class Address {
    /* 값 타입은 Getter만 넣었다.
    * 값 타입은 immutable하게 설계해야 한다.
    * 생성할 때만 값이 세팅이 되고
    * Setter를 안 넣는 게 좋다.*/
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
        /* 리플렉션이나 프록시 기술을 써야할 때가 많은데
        * 기본 생성자가 없으면 그게 안 되므로 기본 생성자를 만들어줘야한다.
        * public으로 하면 사람들이 많이 호출할 수 있으므로 JPA 스펙에서는 protected까지 허용해준다.
        * 상속할 일이 거의 없으니까 그냥 이정도면 손대지말자고 다들 암묵적으로 동의하는 분위기다.*/
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
