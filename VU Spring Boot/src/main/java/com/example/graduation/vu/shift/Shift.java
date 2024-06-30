package com.example.graduation.vu.shift;

import com.example.graduation.vu.entity.ICU;
import com.example.graduation.vu.entity.Unit;

import java.util.List;

public class Shift  {
    private ICU icu;
    private List<Unit> units;

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public ICU getIcu() {
        return icu;
    }

    public void setIcu(ICU icu) {
        this.icu = icu;
    }
}
