package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Requests.AdminRequest;
import com.example.gestionpfe.Responses.AdminResponse;
import com.example.gestionpfe.Services.AdminService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    //added now
    @GetMapping(path="/{id}")
    public ResponseEntity<AdminResponse> getAdmin(@PathVariable String id){
        AdminDto adminDto = adminService.getAdminByIdAdmin(id);
        AdminResponse  adminResponse = new AdminResponse();
        BeanUtils.copyProperties(adminDto,adminResponse);
        return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdminResponse> addAdmin(@RequestBody AdminRequest adminRequest){
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminRequest,adminDto);

        AdminDto AddAdmin = adminService.addAdmin(adminDto);

        AdminResponse adminResponse = new AdminResponse();

        BeanUtils.copyProperties(AddAdmin,adminResponse);

        return new ResponseEntity<AdminResponse>(adminResponse,HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable String id,@RequestBody AdminRequest adminRequest){

        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminRequest,adminDto);

        AdminDto UpdateAdmin = adminService.updateAdmin(id,adminDto);

        AdminResponse adminResponse = new AdminResponse();

        BeanUtils.copyProperties(UpdateAdmin,adminResponse);

        return new ResponseEntity<AdminResponse>(adminResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable String id){
        adminService.deleteAdmin(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
