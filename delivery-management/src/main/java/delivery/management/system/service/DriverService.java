package delivery.management.system.service;

import delivery.management.system.model.dto.request.DriverRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.dto.response.DriverResponseDto;
import delivery.management.system.model.dto.response.DriverTypeResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverService {
    ResponseEntity<List<DriverResponseDto>> drivers();

    ResponseEntity<Void> registration(DriverRequestDto driverRequest);

    ResponseEntity<List<DriverResponseDto>> driversAppeals();

    ResponseEntity<List<DriverTypeResponseDto>> getAllDriverType();

    ResponseEntity<Void> driversActive(long id);

    ResponseEntity<Void> driversBlock(long id);

    ResponseEntity<DriverResponseDto> findById();

    ResponseEntity<Void> update(UserRequestDto userRequest);

    ResponseEntity<Void> driversUpdate(long id, UserRequestDto userRequest);

    ResponseEntity<Void> driversDelete(long id);

    ResponseEntity<DriverResponseDto> findById(long id);
}
