package com.softserve.academy.entity;

public class ExhibitEntity {
    final private Integer id_exhibit;
    private String exhibit_name;
    private String hall_name;
    private String firstName;
    private String lastName;
    private String material_name;
    private String technique_name;

    public ExhibitEntity(Integer id_exhibit) {
        this.id_exhibit = id_exhibit;
    }

    public Integer getId_exhibit() {
        return id_exhibit;
    }

    public String getExhibit_name() {
        return exhibit_name;
    }

    public void setExhibit_name(String exhibit_name) {
        this.exhibit_name = exhibit_name;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getTechnique_name() {
        return technique_name;
    }

    public void setTechnique_name(String technique_name) {
        this.technique_name = technique_name;
    }
}
