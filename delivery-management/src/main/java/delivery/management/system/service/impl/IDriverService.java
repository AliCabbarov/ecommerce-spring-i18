package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.DriverMapper;
import delivery.management.system.mapper.DriverTypeMapper;
import delivery.management.system.mapper.UserMapper;
import delivery.management.system.model.dto.request.DriverRequestDto;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.dto.response.DriverResponseDto;
import delivery.management.system.model.dto.response.DriverTypeResponseDto;
import delivery.management.system.model.entity.Driver;
import delivery.management.system.model.entity.DriverType;
import delivery.management.system.model.entity.Role;
import delivery.management.system.model.entity.User;
import delivery.management.system.model.enums.DriverStatus;
import delivery.management.system.repository.DriverRepository;
import delivery.management.system.repository.DriverTypeRepository;
import delivery.management.system.repository.UserRepository;
import delivery.management.system.service.AuthService;
import delivery.management.system.service.DriverService;
import delivery.management.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static delivery.management.system.model.enums.DriverStatus.*;
import static delivery.management.system.model.enums.RoleType.DRIVER;


@Service
@RequiredArgsConstructor
@Slf4j
public class IDriverService implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final DriverTypeRepository driverTypeRepository;
    private final ExceptionService exceptionService;
    private final DriverTypeMapper driverTypeMapper;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public ResponseEntity<List<DriverResponseDto>> drivers() {
         return ResponseEntity.ok(driverRepository.findAll()
                .stream()
                .map(driverMapper::map)
                .toList());

    }

    @Override
    public ResponseEntity<Void> registration(DriverRequestDto driverRequest) {
        Driver driver = driverMapper.map(driverRequest);
        Role driverRole = roleService.findByRole(DRIVER.name());
        driver.getUser().setRole(driverRole);
        driver.getUser().setPassword(passwordEncoder.encode(driver.getUser().getPassword()));
        driver.setDriverType(findByStatus(MUST_SHOW));
        driverRepository.save(driver);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<DriverResponseDto>> driversAppeals() {
        return ResponseEntity.ok(driverRepository
                .findDriversByDriverType_Status(MUST_SHOW).stream()
                .map(driverMapper::map)
                .toList());
    }

    @Override
    public ResponseEntity<List<DriverTypeResponseDto>> getAllDriverType() {
        return ResponseEntity.ok(driverTypeRepository.findAll().stream()
                .map(driverTypeMapper::map)
                .toList());
    }

    @Override
    @Transactional
    public ResponseEntity<Void> driversActive(long id) {
        Driver driver = getById(id);
        DriverType status = findByStatus(ACTIVE);
        driver.setDriverType(status);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> driversBlock(long id) {
        Driver driver = getById(id);
        DriverType status = findByStatus(BLOCK);
        driver.setDriverType(status);
        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<DriverResponseDto> findById() {
        User user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(driverMapper.map(getById(user.getId())));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> update(UserRequestDto userRequest) {
        User user = authService.getAuthenticatedUser();
        User beforeDriver = getById(user.getId()).getUser();
        User afterDriver = userMapper.map(beforeDriver, userRequest);
        afterDriver.setPassword(passwordEncoder.encode(afterDriver.getPassword()));
        userRepository.save(afterDriver);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> driversUpdate(long id, UserRequestDto userRequest) {
        User beforeUser = getById(id).getUser();
        User afterUser = userMapper.map(beforeUser, userRequest);
        userRepository.save(afterUser);

        return ResponseEntity.noContent().build();

    }

    @Override
    @Transactional
    public ResponseEntity<Void> driversDelete(long id) {
        Driver driver = getById(id);
        driver.setBusy(false);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DriverResponseDto> findById(long id) {
        Driver driver = getById(id);
        return ResponseEntity.ok(driverMapper.map(driver));
    }

    private DriverType findByStatus(DriverStatus driverStatus) {
       return driverTypeRepository.findByStatus(driverStatus)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("exception.driver.status.not.found", HttpStatus.NOT_FOUND)));
    }


    private Driver getById(long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("exception.driver.not.found", HttpStatus.NOT_FOUND)));
    }
}
