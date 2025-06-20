/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alear
 */
@Service
public class LoginService {
    public boolean validarCredenciales(String usuario, String password) {
        String url = "jdbc:mysql://localhost:3306/sistema_ventas";

        try (Connection conn = DriverManager.getConnection(url, usuario, password)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
