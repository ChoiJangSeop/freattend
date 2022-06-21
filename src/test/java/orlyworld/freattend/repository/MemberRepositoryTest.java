package orlyworld.freattend.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import orlyworld.freattend.entity.Member;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void memberTest() throws Exception {
        // given
        Member member = new Member();
        member.setName("test");
        member.setTel("010-1234-5678");
        member.setEmail("abcd@efgh");

        Long id = memberRepository.save(member);

        // when
        Member findMember = memberRepository.find(id);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(id);
    }

}