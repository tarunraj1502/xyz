package com.capg;

import com.capg.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.dto.UsersDto;
import com.capg.entity.Users;
import com.capg.exception.IdNotFoundException;
import com.capg.exception.InvalidEmailException;
import com.capg.exception.InvalidNameException;
import com.capg.exception.InvalidPasswordException;
import com.capg.exception.PasswordMismatchException;
import com.capg.exception.UsersAlreadyExistsException;
import com.capg.repository.UsersRepository;
import com.capg.service.UsersServiceImpl;
import com.capg.utility.AppConstant;

@SpringBootTest
public class UsersServiceImplTest {

    @Mock
    private UsersRepository userRepo;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UsersDto usersDto = new UsersDto();
        usersDto.setEmailId("test@example.com");
        usersDto.setUserName("John Doe");
        usersDto.setPassword("password123");

        when(userRepo.findByEmailId(usersDto.getEmailId())).thenReturn(null);
        when(userRepo.save(any())).thenReturn(new Users());

        Users result = usersService.createUser(usersDto);

        assertNotNull(result);
        assertEquals(usersDto.getEmailId(), result.getEmailId());
        assertEquals(usersDto.getUserName(), result.getUserName());
        // Add more assertions based on your specific logic
    }

    @Test
    public void testCreateUserWithExistingEmail() {
        UsersDto usersDto = new UsersDto();
        usersDto.setEmailId("test@example.com");

        when(userRepo.findByEmailId(usersDto.getEmailId())).thenReturn(new Users());

        assertThrows(UsersAlreadyExistsException.class, () -> usersService.createUser(usersDto));
    }

    @Test
    public void testCreateUserWithInvalidName() {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserName("123");

        assertThrows(InvalidNameException.class, () -> usersService.createUser(usersDto));
    }

    @Test
    public void testCreateUserWithInvalidEmail() {
        UsersDto usersDto = new UsersDto();
        usersDto.setEmailId("invalid-email");

        assertThrows(InvalidEmailException.class, () -> usersService.createUser(usersDto));
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;

        when(userRepo.findByUserId(userId)).thenReturn(new Users());
        doNothing().when(userRepo).deleteById(userId);

        String result = usersService.deleteUsers(userId);

        assertEquals("Id deleted Successfully", result);
    }

    @Test
    public void testDeleteUserWithInvalidId() {
        int userId = 1;

        when(userRepo.findByUserId(userId)).thenReturn(null);

        assertThrows(IdNotFoundException.class, () -> usersService.deleteUsers(userId));
    }

    @Test
    public void testGetAllUsers() {
        List<Users> userList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(userList);

        List<Users> result = usersService.getAllUsers();

        assertNotNull(result);
        assertEquals(userList, result);
    }

    @Test
    public void testCheckUserByEmail() {
        UsersDto userDto = new UsersDto();
        userDto.setEmailId("test@example.com");
        userDto.setPassword("password123");

        Users check_user = new Users();
        check_user.setEmailId(userDto.getEmailId().toLowerCase());
        check_user.setPassword(userDto.getPassword());

        when(userRepo.findByEmailId(userDto.getEmailId().toLowerCase())).thenReturn(check_user);

        String result = usersService.checkUserByEmail(userDto);

        assertEquals("Welcome User", result);
    }

    @Test
    public void testCheckUserByEmailWithInvalidEmail() {
        UsersDto userDto = new UsersDto();
        userDto.setEmailId("invalid-email");
        userDto.setPassword("password123");

        assertThrows(InvalidEmailException.class, () -> usersService.checkUserByEmail(userDto));
    }

    @Test
    public void testCheckUserByEmailWithMismatchedPassword() {
        UsersDto userDto = new UsersDto();
        userDto.setEmailId("test@example.com");
        userDto.setPassword("wrong-password");

        Users check_user = new Users();
        check_user.setEmailId(userDto.getEmailId().toLowerCase());
        check_user.setPassword("password123");

        when(userRepo.findByEmailId(userDto.getEmailId().toLowerCase())).thenReturn(check_user);

        assertThrows(InvalidPasswordException.class, () -> usersService.checkUserByEmail(userDto));
    }

    // Add more test methods for other scenarios and methods in UsersServiceImpl

}
