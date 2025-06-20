/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controlador;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VentaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author alear
 */

@Controller
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public String listarVentas(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }

        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas";
    }

    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        return "nueva_venta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta) {
        ventaService.guardar(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable Integer id, Model model) {
        model.addAttribute("venta", ventaService.buscarPorId(id));
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("productos", productoRepository.findAll());
        return "nueva_venta";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Integer id) {
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }
}
