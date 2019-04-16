package com.softserve.academy.dao;

import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface ExhibitGuideDao {
    public List<GuideEntity> getGuidesByExhibitId(int id);

    public List<ExhibitEntity> getExhibitsByGuideId(int id);

    public List<GuideEntity> getGuidesThatAreNotInThisExhibitById(int id);

    public int reconnectRelations(HashSet<Integer> guidesToExhibit, int exhibitId);
}
