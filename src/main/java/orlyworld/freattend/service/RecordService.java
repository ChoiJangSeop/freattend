package orlyworld.freattend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orlyworld.freattend.entity.*;
import orlyworld.freattend.repository.AdminRepository;
import orlyworld.freattend.repository.ItemRepository;
import orlyworld.freattend.repository.MemberRepository;
import orlyworld.freattend.repository.RecordRepository;

import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final AdminRepository adminRepository;

    /**
     * 근무변동 요청
     */
    @Transactional
    public Long requestRecord(Long memberId, Long itemId, Date useDate) {
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.find(itemId);

        validateRequestRecord(member, item, useDate);

        Record record = Record.createRecord(member, useDate, item);

        recordRepository.save(record);

        return record.getId();
    }

    /**
     * 근무변동 요청 취소
     */
    // 근무변동 요청 응답에 병합함

    /**
     * 근무요청 형식 검증
     */
    private void validateRequestRecord(Member member, Item item, Date useDate) {

        if (member.getId() != item.getMember().getId()) {
            throw new IllegalStateException("요청자의 근무 변동 사항이 아닙니다");
        }

        if (item.getStockQuantity() <= 0) {
            throw new IllegalStateException("잔여 휴무가 부족합니다");
        }

        // TODO 신청 날짜에 이미 휴무가 있는지 확인, 있을 경우 예외를 던짐

        if (item.getDeadline().before(useDate)) {
            throw new IllegalStateException("휴무 사용 기한이 지났습니다");
        }
    }

    // TODO 요청 응답 메서드를 어디에 둘것인가? (RecordService vs AdminService)
    /**
     * 요청 응답
     */
    public Long responseRecord(Long recordId, Long adminId, ApproveStatus status, String comment) {
        Record record = recordRepository.find(recordId);
        Admin admin = adminRepository.find(adminId);

        record.answerRecord(admin, status, comment);

        return recordId;
    }

    /**
     * 요청 검색
     */
    // TODO 요청 검색 메서드 동적쿼리 필요함


}
