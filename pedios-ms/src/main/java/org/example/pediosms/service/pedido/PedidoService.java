package org.example.pediosms.service.pedido;

import org.example.pediosms.model.pedido.Pedido;

import java.util.List;

public interface PedidoService {
    Pedido save(Pedido pedido);
    List<Pedido> listAll();
    Pedido findById(Long id);
    Pedido updateById(Long id, Pedido pedido);
    void deleteById(Long id);
    Pedido updatePatch(Long id, Pedido pedidoParcial);

}
