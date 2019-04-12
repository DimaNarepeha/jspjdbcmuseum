package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.GuideDao;
import com.softserve.academy.entity.GuideEntity;

import java.util.List;

public class GuideDaoImpl implements GuideDao {
    @Override
    public boolean saveGuide(GuideEntity guide) {
        return false;
    }

    @Override
    public List<GuideEntity> readAllGuides() {
        return null;
    }

    @Override
    public int updateGuide(GuideEntity guide) {
        return 0;
    }

    @Override
    public int deleteGuide(int id_guide) {
        return 0;
    }
}
