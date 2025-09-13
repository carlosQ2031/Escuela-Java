package org.example.pediosms.service.pedido;

import org.example.pediosms.model.pedido.Pedido;

import java.util.List;

public interface PedidoService {
    //Crear nuevo pedido
    Pedido save(Pedido pedido);

    //Obtener una lista de Pedido
    List<Pedido> listAll();

    //Buscar un Pedido por su id
    Pedido findById(Long id);

    //Actualizar completo un Pedido por su id
    Pedido updateById(Long id, Pedido pedido);

    //Eliminar Pedido por id
    void deleteById(Long id);

    //Modificar partes de un Pedido
    Pedido updatePatch(Long id, Pedido pedidoParcial);

}
