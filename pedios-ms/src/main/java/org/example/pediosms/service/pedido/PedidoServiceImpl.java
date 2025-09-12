package org.example.pediosms.service.pedido;


import lombok.AllArgsConstructor;
import org.example.pediosms.model.pedido.Pedido;
import org.example.pediosms.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido save(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getCliente().isBlank()) {
            throw new IllegalArgumentException("El cliente no puede ser nulo ni vacío");
        }
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
    public Pedido updateById(Long id, Pedido pedido) {
        Pedido existente = findById(id);
        System.out.println(pedido.getCliente());
        System.out.println(pedido.getEstado());

        existente.setCliente(pedido.getCliente());
        existente.setEstado(pedido.getEstado());
        return pedidoRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        Pedido existente = findById(id);
        pedidoRepository.delete(existente);
    }

    @Override
    public Pedido updatePatch(Long id, Pedido pedidoParcial) {
        Pedido pedidoExistente = findById(id);

        if (pedidoParcial.getCliente() != null) {
            pedidoExistente.setCliente(pedidoParcial.getCliente());
        }

        if (pedidoParcial.getEstado() != null) {
            pedidoExistente.setEstado(pedidoParcial.getEstado());
        }

        // Aquí podrías actualizar detalles parciales si ya implementas helpers

        return pedidoRepository.save(pedidoExistente);
    }

}