package ru.greenatom.zmaev.greenatomhibernate.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    private static int ELS_PER_PAGE = 5;

    private int currentPage;

    private int ageFilter;

    private String nameFilter;

    private String lastnameFilter;

    private String isAdminFilter;

    public Map<String, Object> getCriteria() {
        Map<String, Object> cr = new HashMap<>();
        if (nameFilter != null) {
            cr.put("firstname", nameFilter);
        }
        if (lastnameFilter != null) {
            cr.put("lastname", lastnameFilter);
        }
        if (ageFilter != 0) {
            cr.put("age", ageFilter);
        }
        if (isAdminFilter != null) {
            cr.put("isAdmin", Boolean.valueOf(isAdminFilter));
        }
        return cr;
    }
}