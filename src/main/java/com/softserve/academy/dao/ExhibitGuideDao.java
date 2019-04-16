package com.softserve.academy.dao;

import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.util.List;

public interface ExhibitGuideDao {
    public List<GuideEntity> getGuidesByExhibitId(int id);
    public List<ExhibitEntity> getExhibitsByGuideId(int id);
    public List<GuideEntity> getGuidesThatAreNotInThisExhibitById(int id);

}
