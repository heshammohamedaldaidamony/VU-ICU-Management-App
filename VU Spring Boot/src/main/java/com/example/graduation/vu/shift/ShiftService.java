package com.example.graduation.vu.shift;

import com.example.graduation.vu.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepo shiftRepo;

    public ResponseEntity<?> getShift(String id, String role) {
        List<Unit> units;
        if(role.equals("doctor"))
            units=shiftRepo.findDoctorUnitsById(id);
        else
            units=shiftRepo.findNurseUnitsById(id);

        Shift shift=new Shift();
        shift.setUnits(filterUnits(units));
        shift.setIcu(shiftRepo.findICUByUnitId(units.get(0).getId()));
        return ResponseEntity.ok(shift);
    }

    //Select current shifts
    public List<Unit> filterUnits(List<Unit> units){
        List<Unit> filteredUnits=new ArrayList<>();
        LocalDateTime currentTime=LocalDateTime.now();

        for (Unit unit:units){
            LocalDateTime start=unit.getStart();
            LocalDateTime end = unit.getEnd();
            if( (currentTime.isEqual(start)||currentTime.isAfter(start))
                    && (currentTime.isEqual(end)||currentTime.isBefore(end)) )
                filteredUnits.add(unit);
        }
        return filteredUnits;
    }
}
