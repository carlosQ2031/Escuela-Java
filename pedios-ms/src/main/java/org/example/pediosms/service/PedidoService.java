package org.example.pediosms.service;

import org.example.pediosms.model.Pedido;

import java.util.List;

public interface PedidoService {
    //Crear
    Pedido save(Pedido pedido);

    //Obtener
    List<Pedido> listAll();

    //ObtenerPorId
    Pedido findById(Long id);

    //Actualizar
    Pedido updateById(Long id, Pedido pedido);

    //Eliminar
    void deleteById(Long id);
}
