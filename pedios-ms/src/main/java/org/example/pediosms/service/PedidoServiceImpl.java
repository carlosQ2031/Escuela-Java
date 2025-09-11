package org.example.pediosms.service;


import lombok.AllArgsConstructor;
import org.example.pediosms.model.Pedido;
import org.example.pediosms.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pedido no encontrado"));
    }


    @Override
    public void deleteById(Long id) {
        if(id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        Pedido pedido = findById(id);
        pedidoRepository.delete(pedido);
    }

    @Override
    public Pedido updateById(Long id, Pedido pedido) {
        if(id == null || pedido == null) throw new IllegalArgumentException("El id pueden ser nulos");
        Pedido pedidoEncontrado = findById(id);

        pedidoEncontrado.setEstado(pedido.getEstado());
        return pedidoRepository.save(pedidoEncontrado);
    }
}