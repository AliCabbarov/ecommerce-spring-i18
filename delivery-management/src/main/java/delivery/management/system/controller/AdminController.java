package delivery.management.system.controller;

import delivery.management.system.model.dto.request.DriverRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.dto.response.DashboardResponse;
import delivery.management.system.model.dto.response.DriverResponseDto;
import delivery.management.system.model.dto.response.UserResponseDto;
import delivery.management.system.service.AdminService;
import delivery.management.system.service.CustomerService;
import delivery.management.system.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admins/")
public class AdminController {

    private final AdminService adminService;
    private final DriverService driverService;
    private final CustomerService customerService;

    @GetMapping("dashboard")
    ResponseEntity<DashboardResponse> dashboard() {
        return adminService.dashboard();
    }

    @GetMapping("drivers")
    ResponseEntity<List<DriverResponseDto>> drivers() {
        return driverService.drivers();
    }

    @GetMapping("drivers-appeals")
    ResponseEntity<List<DriverResponseDto>> driversAppeals() {
        return driverService.driversAppeals();
    }

    @PatchMapping("drivers-active/{id}")
    ResponseEntity<Void> driversActive(@PathVariable long id) {
        return driverService.driversActive(id);
    }

    @PatchMapping("drivers-block/{id}")
    ResponseEntity<Void> driversBlock(@PathVariable long id) {
        return driverService.driversBlock(id);
    }

    @GetMapping("drivers-find-by-id/{id}")
    ResponseEntity<DriverResponseDto> driverFindById(@PathVariable long id) {
        return driverService.findById(id);
    }

    @PutMapping("drivers-updated/{id}")
    ResponseEntity<Void> driversBlock(@RequestBody UserRequestDto userRequest, @PathVariable long id) {
        return driverService.driversUpdate(id, userRequest);
    }

    @DeleteMapping("drivers-deleted/{id}")
    ResponseEntity<Void> driversDelete(@PathVariable long id) {
        return driverService.driversDelete(id);
    }

    @GetMapping("customers")
    ResponseEntity<List<UserResponseDto>> customers() {
        return customerService.findAll();
    }

    @GetMapping("customer-find-by-id/{id}")
    ResponseEntity<UserResponseDto> customerFindById(@PathVariable long id) {
        return customerService.findById(id);
    }

    @DeleteMapping("customer-deleted/{id}")
    ResponseEntity<Void> customerDelete(@PathVariable long id) {
        return customerService.delete(id);
    }
}