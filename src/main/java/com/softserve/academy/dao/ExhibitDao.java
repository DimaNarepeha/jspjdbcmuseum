package com.softserve.academy.dao;

import com.softserve.academy.entity.ExhibitEntity;

import java.util.List;

public interface ExhibitDao {
    boolean saveExhibit(ExhibitEntity exhibit);

    List<ExhibitEntity> readAllExhibits();

    ExhibitEntity getExhibitById(int id);

    int updateExhibit(ExhibitEntity exhibit);

    int deleteExhibit(int id_exhibit);

}
