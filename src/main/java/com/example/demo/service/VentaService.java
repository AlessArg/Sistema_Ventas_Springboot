/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Producto;
import com.example.demo.model.Venta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 *
 * @author alear
 */

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Venta guardar(Venta venta) {
        Cliente cliente = clienteRepository.findById(venta.getCliente().getId())
                            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Producto producto = productoRepository.findById(venta.getProducto().getId())
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        venta.setCliente(cliente);
        venta.setProducto(producto);

        venta.setTotal(venta.getCantidad() * producto.getPrecio());
        
        System.out.println("Cliente ID recibido: " + venta.getCliente().getId());
        System.out.println("Producto ID recibido: " + venta.getProducto().getId());


        return ventaRepository.save(venta);
    }

    public Venta buscarPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}