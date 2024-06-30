package com.example.graduation.vu.shift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shift")
public class ShiftContr {
    @Autowired
    private ShiftService shiftService;

    //Get the shift (units)
    @GetMapping("/unit")
    public ResponseEntity<?> getShift(@RequestParam String id, @RequestParam String role){
        return shiftService.getShift(id,role);
    }
}
