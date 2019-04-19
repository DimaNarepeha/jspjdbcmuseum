package ua.home.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.home.entity.Guide;
import ua.home.entity.Test;

import java.util.List;

@Repository
@Transactional
public interface GuideRepository  {
    List<Guide> findAll();
    void deleteById(Integer integer);
    Guide getGuideById(Integer integer);
    boolean existsById(Integer integer);
}
