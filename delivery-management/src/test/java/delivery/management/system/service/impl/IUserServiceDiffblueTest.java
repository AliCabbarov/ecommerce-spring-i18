package delivery.management.system.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import common.exception.model.dto.response.ExceptionResponse;
import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.UserMapper;
import delivery.management.system.model.dto.request.UserRequestDto;
import delivery.management.system.model.entity.Role;
import delivery.management.system.model.entity.User;
import delivery.management.system.repository.UserRepository;
import delivery.management.system.service.OtpService;
import delivery.management.system.service.RoleService;
import delivery.management.system.service.TokenService;
import delivery.management.system.util.MessageUtil;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {IUserService.class})
@ExtendWith(SpringExtension.class)
class IUserServiceDiffblueTest {
    @MockBean
    private ExceptionService exceptionService;

    @Autowired
    private IUserService iUserService;

    @MockBean
    private MessageUtil messageUtil;

    @MockBean
    private OtpService otpService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleService roleService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link IUserService#registration(UserRequestDto)}
     */
    @Test
    void testRegistration() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        role.setPermission(new ArrayList<>());

        User user = new User();
        user.setBirthdate(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber(1L);
        user.setRole(role);
        user.setSurname("Doe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        role2.setPermission(new ArrayList<>());

        User user2 = new User();
        user2.setBirthdate(LocalDate.of(1970, 1, 1));
        user2.setEmail("jane.doe@example.org");
        user2.setEnable(true);
        user2.setId(1L);
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhoneNumber(1L);
        user2.setRole(role2);
        user2.setSurname("Doe");
        when(userMapper.map(Mockito.<UserRequestDto>any())).thenReturn(user2);
        doNothing().when(tokenService).confirm(Mockito.<User>any());

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");
        role3.setPermission(new ArrayList<>());
        when(roleService.findByRole(Mockito.<String>any())).thenReturn(role3);

        // Act
        ResponseEntity<Void> actualRegistrationResult = iUserService.registration(new UserRequestDto());

        // Assert
        verify(userMapper).map(Mockito.<UserRequestDto>any());
        verify(roleService).findByRole(eq("CUSTOMER"));
        verify(tokenService).confirm(Mockito.<User>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertNull(actualRegistrationResult.getBody());
        assertEquals(204, actualRegistrationResult.getStatusCodeValue());
        assertTrue(actualRegistrationResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link IUserService#registration(UserRequestDto)}
     */
    @Test
    void testRegistration2() {
        // Arrange
        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        role.setPermission(new ArrayList<>());

        User user = new User();
        user.setBirthdate(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber(1L);
        user.setRole(role);
        user.setSurname("Doe");
        when(userMapper.map(Mockito.<UserRequestDto>any())).thenReturn(user);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .httpStatus(HttpStatus.CONTINUE)
                .message("An error occurred")
                .build();
        when(roleService.findByRole(Mockito.<String>any())).thenThrow(new ApplicationException(exceptionResponse));

        // Act and Assert
        assertThrows(ApplicationException.class, () -> iUserService.registration(new UserRequestDto()));
        verify(userMapper).map(Mockito.<UserRequestDto>any());
        verify(roleService).findByRole(eq("CUSTOMER"));
    }

    /**
     * Method under test: {@link IUserService#registration(UserRequestDto)}
     */
    @Test
    void testRegistration3() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .httpStatus(HttpStatus.CONTINUE)
                .message("An error occurred")
                .build();
        when(userRepository.save(Mockito.<User>any())).thenThrow(new ApplicationException(exceptionResponse));

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        role.setPermission(new ArrayList<>());

        User user = new User();
        user.setBirthdate(LocalDate.of(1970, 1, 1));
        user.setEmail("jane.doe@example.org");
        user.setEnable(true);
        user.setId(1L);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhoneNumber(1L);
        user.setRole(role);
        user.setSurname("Doe");
        when(userMapper.map(Mockito.<UserRequestDto>any())).thenReturn(user);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        role2.setPermission(new ArrayList<>());
        when(roleService.findByRole(Mockito.<String>any())).thenReturn(role2);
        UserRequestDto.UserRequestDtoBuilder builderResult = UserRequestDto.builder();
        UserRequestDto userRequest = builderResult.birthdate(LocalDate.of(1970, 1, 1))
                .email("jane.doe@example.org")
                .name("Name")
                .password("iloveyou")
                .phoneNumber(1L)
                .surname("Doe")
                .build();

        // Act and Assert
        assertThrows(ApplicationException.class, () -> iUserService.registration(userRequest));
        verify(userMapper).map(Mockito.<UserRequestDto>any());
        verify(roleService).findByRole(eq("CUSTOMER"));
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }
}
