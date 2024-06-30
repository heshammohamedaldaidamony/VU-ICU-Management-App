package com.example.graduation.vu.login;

import com.example.graduation.vu.entity.Doctor;
import com.example.graduation.vu.entity.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> authenticateUser(User user) {
        String role=user.getRole();
        String phone= user.getPhone();
        if(role.equals("doctor")){
            Doctor doctor = userRepo.findDoctorByPhone(phone);
            if(doctor!=null)
                return ResponseEntity.ok(doctor);
        }
        else if (role.equals("nurse")){
            Nurse nurse = userRepo.findNurseByPhone(phone);
            if(nurse!=null)
                return ResponseEntity.ok(nurse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    public ResponseEntity<?> setProfilePic(String id, String role , MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            byte[] picture = file.getBytes();
            if(role.equals("doctor"))
                userRepo.updateDoctorProfilePictureBYId(id,picture);
            else
                userRepo.updateNurseProfilePictureBYId(id,picture);
            return ResponseEntity.ok("Profile Picture Has Set");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please Choose A Picture");
    }
}
