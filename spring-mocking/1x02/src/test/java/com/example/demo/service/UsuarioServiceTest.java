package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(1L))
                        .thenReturn(Optional.of(usuario));
        assertEquals(usuario, usuarioService.buscarPorId(1L));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Mockito.when(usuarioRepository.findById(1L))
                .thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> usuarioService.buscarPorId(1L));
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.save(usuario))
                .thenReturn(usuario);
        assertEquals(usuario, usuarioService.salvarUsuario(usuario));

    }
}
