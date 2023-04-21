package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext // 스프링부트가 EntityManager를 주입할 수 있게 해주는 애너테이션
    EntityManager em;

    public Long save(Member member) {
        /* command와 query를 구분해라.
         * 저장하면 가급적 side effect를 일으키는 command성이기 때문에 return 값을 거의 안 만든다.
         * id 정도 있으면 다음에 다시 조회할 수 있으니까 id 정도만 조회하는 것으로 주로 설계를 한다.*/
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
