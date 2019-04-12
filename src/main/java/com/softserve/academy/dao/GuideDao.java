package com.softserve.academy.dao;

import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.util.List;

public interface GuideDao {
    boolean saveGuide(GuideEntity guide);

    List<GuideEntity> readAllGuides();

    int updateGuide(GuideEntity guide);

    int deleteGuide(int id_guide);
}
