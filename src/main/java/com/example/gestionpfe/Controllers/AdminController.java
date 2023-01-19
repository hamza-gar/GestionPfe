package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Requests.AdminRequest;
import com.example.gestionpfe.Responses.AdminResponse;
import com.example.gestionpfe.Responses.DomaineResponse;
import com.example.gestionpfe.Services.AdminService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admins")
public class AdminController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    AdminService adminService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AdminResponse> getAdmin(@PathVariable String id) {
        AdminDto adminDto = adminService.getAdminByIdAdmin(id);
        AdminResponse adminResponse = new AdminResponse();
        adminResponse = modelMapper.map(adminDto, AdminResponse.class);
        return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<AdminResponse> addAdmin(@RequestBody AdminRequest adminRequest) {

        AdminDto adminDto = new AdminDto();
        adminDto = modelMapper.map(adminRequest, AdminDto.class);

        AdminDto AddAdmin = adminService.addAdmin(adminDto);

        AdminResponse adminResponse = new AdminResponse();
        adminResponse = modelMapper.map(AddAdmin, AdminResponse.class);

        return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN_AUTHORITY')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable String id, @RequestBody AdminRequest adminRequest) {

        AdminDto adminDto = new AdminDto();
        adminDto = modelMapper.map(adminRequest, AdminDto.class);

        AdminDto UpdateAdmin = adminService.updateAdmin(id, adminDto);

        AdminResponse adminResponse = new AdminResponse();
        adminResponse = modelMapper.map(UpdateAdmin, AdminResponse.class);

        return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN_AUTHORITY')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_LIST_ADMIN_AUTHORITY')")
    @GetMapping
    public List<AdminResponse> getAllAdmins(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        List<AdminResponse> adminResponses = new ArrayList<>();
        List<AdminDto> adminDtos = adminService.getAllAdmins(page, limit);

        for (AdminDto adminDto : adminDtos) {
            AdminResponse adminResponse = new AdminResponse();
            adminResponse = modelMapper.map(adminDto, AdminResponse.class);

            adminResponses.add(adminResponse);
        }
        return adminResponses;
    }
}
