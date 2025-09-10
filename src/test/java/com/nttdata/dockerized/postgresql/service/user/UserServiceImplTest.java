package com.nttdata.dockerized.postgresql.service.user;

import com.nttdata.dockerized.postgresql.excepcionPer.UsuarioExeption;
import com.nttdata.dockerized.postgresql.model.user.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private UserRepository userRepository;
    private UserService userService;


    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    //Save
    @Test
    void testSave(){
        User u = new User();
        u.setName("Carlos");
        u.setEmail("carlos@test.com");

        when(userRepository.save(u)).thenReturn(u);

        User resultado =userService.save(u);

        assertNotNull(resultado);
        assertEquals("Carlos",resultado.getName());
        verify(userRepository, times(1)).save(u);
    }


    //ListAll
    @Test
    void testListAll() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> resultado = userService.listAll();

        assertEquals(2, resultado.size());
        verify(userRepository, times(1)).findAll();
    }

    //FindById
    @Test
    void testFindById() {
        User u = new User();
        u.setId(1L);
        u.setName("Carlos");

        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        User resultado = userService.findById(1L);

        assertEquals("Carlos", resultado.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    //updateById
    @Test
    void testUpdateById() {
        User existente = new User();
        existente.setId(1L);
        existente.setName("Old");
        existente.setEmail("old@test.com");
        existente.setActive(true);

        User actualizacion = new User();
        actualizacion.setName("New");
        actualizacion.setEmail("new@test.com");
        actualizacion.setActive(false);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(userRepository.save(existente)).thenReturn(existente);

        User resultado = userService.updateById(1L, actualizacion);

        assertEquals("New", resultado.getName());
        assertEquals("new@test.com", resultado.getEmail());
        assertFalse(resultado.getActive());
        verify(userRepository, times(1)).save(existente);
    }


    //deleteById
    @Test
    void testDeleteById() {
        User u = new User();
        u.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        userService.deleteById(1L);

        verify(userRepository, times(1)).delete(u);
    }

    //findById exception
    @Test
    void testFindByIdException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UsuarioExeption exception = assertThrows(UsuarioExeption.class, () -> userService.findById(1L));

        assertEquals("Usuario no encontrado", exception.getMessage());
        assertEquals(404, exception.getCodigoDeError());
    }

    //updateById exception
    @Test
    void testUpdateByIException() {
        User u = new User();
        u.setName("Carlos");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.updateById(null, u));

        assertEquals("El id y el user no pueden ser nulos", exception.getMessage());
    }

}
