package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.entity.ExhibitEntity;

import java.util.List;

public class ExhibitDaoImpl implements ExhibitDao {
    @Override
    public boolean saveExhibit(ExhibitEntity exhibit) {
        return false;
    }

    @Override
    public List<ExhibitEntity> readAllExhibits() {
        return null;
    }

    @Override
    public int updateExhibit(ExhibitEntity exhibit) {
        return 0;
    }

    @Override
    public int deleteExhibit(int id_exhibit) {
        return 0;
    }
}
