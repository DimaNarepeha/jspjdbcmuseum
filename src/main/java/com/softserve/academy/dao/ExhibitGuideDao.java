package com.softserve.academy.dao;

import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.util.List;

public interface ExhibitGuideDao {
    public List<GuideEntity> getGuidesByExhibitId();
    public List<ExhibitEntity> getExhibitsByGudeId();
}
