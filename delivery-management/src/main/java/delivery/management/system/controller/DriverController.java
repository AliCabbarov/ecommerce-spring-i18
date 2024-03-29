package delivery.management.system.controller;

import delivery.management.system.model.dto.request.DriverRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.dto.response.DriverResponseDto;
import delivery.management.system.model.dto.response.DriverTypeResponseDto;
import delivery.management.system.service.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("drivers/")
@RequiredArgsConstructor
@Slf4j
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    ResponseEntity<Void> registration(@RequestBody DriverRequestDto driverRequest) {
        return driverService.registration(driverRequest);
    }

    @GetMapping
    ResponseEntity<List<DriverTypeResponseDto>> getAllDriverType() {
        return driverService.getAllDriverType();
    }

    @GetMapping("find-by-id") //FIXME JWT INITIAL
    ResponseEntity<DriverResponseDto> findById() {
        return driverService.findById();
    }

    @PutMapping
    ResponseEntity<Void> update(@RequestBody UserRequestDto userRequest) {
        log.error("{}",userRequest);
        return driverService.update(userRequest);
    }


}
