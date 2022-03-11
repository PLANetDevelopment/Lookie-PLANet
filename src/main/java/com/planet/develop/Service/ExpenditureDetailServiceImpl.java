package com.planet.develop.Service;

import com.planet.develop.DTO.ExpenditureDetailDto;
import com.planet.develop.DTO.ExpenditureRequestDto;
import com.planet.develop.DTO.ExpenditureTypeDetailDto;
import com.planet.develop.Entity.Expenditure;
import com.planet.develop.Entity.ExpenditureDetail;
import com.planet.develop.Entity.User;
import com.planet.develop.Enum.EcoEnum;
import com.planet.develop.Enum.money_Type;
import com.planet.develop.Enum.money_Way;
import com.planet.develop.Repository.ExpenditureDetailRepository;
import com.planet.develop.Repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExpenditureDetailServiceImpl implements ExpenditureDetailService {

    private final ExpenditureRepository expenditureRepository;
    private final ExpenditureDetailRepository detailRepository;
    private final ExpenditureService service;

    private final EntityManager em;

    /** 지출 등록 */
    @Override
    public Long save(ExpenditureRequestDto dto) {
        ExpenditureDetail entity = dtoToEntity(dto);
        detailRepository.save(entity);
        return entity.getEno();
    }

    /** 하루 지출 총액 */
    @Override
    public Long totalDay(User user, LocalDate date) {
        Long total = 0L;
        List<Object[]> exTypeList = expenditureRepository.getDayList(user, date);
        for (Object[] arr : exTypeList) {
            total += (Long) arr[0];
        }
        return total;
    }

    /** 하루 지출 유형별 총액 */
    @Override
    public Long totalTypeDay(User user, money_Type type, LocalDate date) {
        Long total = 0L;
        List<Object[]> exTypeList = expenditureRepository.getDayExTypeList(user, type, date);
        for (Object[] arr : exTypeList) {
            total += (Long) arr[1];
        }
        return total;
    }

    /** 하루 친반환경별 총액 */
    @Override
    public Long totalEcoDay(User user, EcoEnum eco, LocalDate date) {
        Long total = 0L;
        List<Object[]> ecoList = expenditureRepository.getDayEcoList(user, eco, date);
        for (Object[] arr : ecoList) {
            total += (Long) arr[1];
        }
        return total;
    }

    /** 하루 지출 방법별 총액 */
    @Override
    public Long totalWayDay(User user, money_Way way, LocalDate date) {
        Long total = 0L;
        List<Object[]> exWayList = expenditureRepository.getDayExWayList(user, way, date);
        for (Object[] arr : exWayList) {
            total += (Long) arr[1];
        }
        return total;
    }

    @Override
    public List<ExpenditureTypeDetailDto> findDay(User user, LocalDate date) {
        List<Object[]> dayList = expenditureRepository.getDayList(user, date);
        List<ExpenditureTypeDetailDto> dtoList = new ArrayList<>();
        for (Object[] arr : dayList) {
            ExpenditureTypeDetailDto dto = new ExpenditureTypeDetailDto(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7]);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /** 한 달 지출 리스트 */
    @Override
    public List<Expenditure> getMonthList(User user, int month) {
        LocalDate startDate = LocalDate.of(2022,month,1);
        int lengthOfMonth = startDate.lengthOfMonth();
        LocalDate endDate = LocalDate.of(2022,month,lengthOfMonth);
        return em.createQuery("select e from Expenditure e left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "where :startDate <= e.date and e.date <= :endDate " +
                        "and e.user = :user", Expenditure.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .setParameter("user", user)
                .getResultList();
    }

    /** 한 달 지출 유형별 리스트 */
    @Override
    public List<Expenditure> getMonthTypeList(User user, int month, money_Type type) {
        LocalDate startDate = LocalDate.of(2022,month,1);
        int lengthOfMonth = startDate.lengthOfMonth();
        LocalDate endDate = LocalDate.of(2022,month,lengthOfMonth);
        return em.createQuery("select e from Expenditure e left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "where :startDate <= e.date and e.date <= :endDate " +
                        "and e.user = :user and e.detail.exType = :type", Expenditure.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .setParameter("user", user)
                .setParameter("type", type)
                .getResultList();
    }

    /** 한 달 지출 방법별 리스트 */
    @Override
    public List<Expenditure> getMonthWayList(User user, int month, money_Way way) {
        LocalDate startDate = LocalDate.of(2022,month,1);
        int lengthOfMonth = startDate.lengthOfMonth();
        LocalDate endDate = LocalDate.of(2022,month,lengthOfMonth);
        return em.createQuery("select e from Expenditure e left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "where :startDate <= e.date and e.date <= :endDate " +
                        "and e.user = :user and e.detail.exWay = :way", Expenditure.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .setParameter("user", user)
                .setParameter("way", way)
                .getResultList();
    }

    /** 한 달 친반환경별 리스트 */
    @Override
    public List<Expenditure> getMonthEcoList(User user, int month, EcoEnum eco) {
        LocalDate startDate = LocalDate.of(2022,month,1);
        int lengthOfMonth = startDate.lengthOfMonth();
        LocalDate endDate = LocalDate.of(2022,month,lengthOfMonth);
        return em.createQuery("select e from Expenditure e left join ExpenditureDetail ed on e.eno = ed.eno " +
                        "where :startDate <= e.date and e.date <= :endDate " +
                        "and e.user = :user and e.detail.eco = :eco", Expenditure.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .setParameter("user", user)
                .setParameter("eco", eco)
                .getResultList();
    }

    /** 한 달 지출 총액 */
    @Override
    public Long totalMonth(User user, int year ,int month) {
        LocalDate startDate = LocalDate.of(year,month,1);
        LocalDate endDate = LocalDate.of(year,month,startDate.lengthOfMonth());
        Long total = expenditureRepository.calMonth(user,startDate,endDate);
        return total;
    }

    /** 한 달 지출 유형별 총액 */
    @Override
    public Long totalMonthType(User user, int month, money_Type type) {
        Long total = 0L;
        List<Expenditure> exTypeList = getMonthTypeList(user, month, type);
        for (Expenditure e : exTypeList) {
            ExpenditureRequestDto dto = service.entityToDto(e);
            total += dto.getEx_cost();
        }
        return total;
    }

    /** 한 달 지출 방법별 총액 */
    @Override
    public Long totalWayMonth(User user, int month, money_Way way) {
        Long total = 0L;
        List<Expenditure> exWayList = getMonthWayList(user, month, way);
        for (Expenditure e : exWayList) {
            ExpenditureRequestDto dto = service.entityToDto(e);
            total += dto.getEx_cost();
        }
        return total;
    }

    /** 한 달 친반환경별 지출 총액 */
    @Override
    public Long totalEcoMonth(User user, int month, EcoEnum eco) {
        Long total = 0L;
        List<Expenditure> ecoList = getMonthEcoList(user, month, eco);
        for (Expenditure e : ecoList) {
            ExpenditureRequestDto dto = service.entityToDto(e);
            total += dto.getEx_cost();
        }
        return total;
    }

    /** 지출 수정 */
    @Transactional
    public Long update(Long id, ExpenditureRequestDto dto) throws IllegalAccessException {
        Expenditure expenditure = expenditureRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        ExpenditureDetail expenditureDetail = detailRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        expenditure.update(dto.getEx_cost(), dto.getDate());
        expenditureDetail.update(dto.getExType(), dto.getExWay(), dto.getMemo());
        return id;
    }
    @Override
    public void delete(Long id) {
        expenditureRepository.deleteById(id);
        detailRepository.deleteById(id);
    }

}