package org.example.productoms.service.categoria;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.productoms.mapper.CategoriaMapper;
import org.example.productoms.model.category.Categoria;
import org.example.productoms.model.category.CategoriaRequestDto;
import org.example.productoms.model.category.CategoriaResponseDto;
import org.example.productoms.model.category.CategoriaUpdateDto;
import org.example.productoms.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public CategoriaResponseDto save(CategoriaRequestDto categoriaRequestDto) {
        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.save(categoriaMapper.toEntity(categoriaRequestDto)));

    }

    @Override
    public List<CategoriaResponseDto> listAll() {
        // Devuelve solo activas
        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.findByActiveTrue());
    }

    public List<CategoriaResponseDto> listAllInactive() {
        // Devuelve solo inactivas
        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.findByActiveFalse());
    }

    public List<CategoriaResponseDto> listAllComplete() {
        // Devuelve todas (true + false)
        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.findAll());
    }
    @Override
    public void deleteById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Categoria no encontrada"));

        categoria.setActive(false);
        categoriaRepository.save(categoria);

        categoria.getProductos().forEach(producto -> producto.setCategoria(null));
    }


    @Override
    public CategoriaResponseDto findById(Long id) {
        if(id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Categoria no encontrada")));
    }

    @Override
    public CategoriaResponseDto updateById(Long id, CategoriaUpdateDto categoriaUpdateDto) {
        if (id == null || categoriaUpdateDto == null)
            throw new IllegalArgumentException("El id y el usuario no pueden ser nulos");

        Categoria categoriaEncontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Categoria no encontrada"));

        Categoria categoriaUpdate = categoriaMapper.toEntity(categoriaUpdateDto);

        categoriaEncontrada.setNombre(categoriaUpdate.getNombre());
        categoriaEncontrada.setActive(categoriaUpdate.getActive());

        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.save(categoriaEncontrada));
    }



    @Override
    public CategoriaResponseDto updatePatch(Long id, CategoriaUpdateDto categoriaUpdateDto) {
        if (id == null || categoriaUpdateDto == null)
            throw new IllegalArgumentException("El id y el usuario no pueden ser nulos");

        Categoria categoriaEncontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Categoria no encontrada"));

        Categoria categoriaUpdate = categoriaMapper.toEntity(categoriaUpdateDto);

        if (categoriaUpdate.getNombre() != null) categoriaEncontrada.setNombre(categoriaUpdate.getNombre());
        if (categoriaUpdate.getActive() != null)  categoriaEncontrada.setActive(categoriaUpdate.getActive());

        return categoriaMapper.toCategoriaResponseDto(categoriaRepository.save(categoriaEncontrada));
    }


}
