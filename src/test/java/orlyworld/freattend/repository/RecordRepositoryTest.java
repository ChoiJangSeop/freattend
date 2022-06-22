package orlyworld.freattend.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import orlyworld.freattend.entity.*;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class RecordRepositoryTest {

    @Autowired RecordRepository recordRepository;

    @Test
    public void 기록저장() throws Exception {
        // given
        Team team = Team.createTeam("test", "5678-1234");
        Member member = Member.createMember("test", "1234-5678", "test@test.com", team);
        Item testItem = Item.createItem(ItemCategory.TEST, member, new Date(), 10, "테스트");

        Record record = Record.createRecord(member, new Date(), testItem);
        Long findId = recordRepository.save(record);

        // when
        Record findRecord = recordRepository.find(findId);

        // then
        assertThat(record).isEqualTo(findRecord);
    }



}