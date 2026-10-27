/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.padaria.controller;

import br.com.padaria.view.ConsultaClienteView;
import br.com.padaria.view.ConsultaProdutos;
import br.com.padaria.view.MenuView;

/**
 *
 * @author erick
 */
public class MenuController {
    private MenuView menuView;
    
    public MenuController(){
        this.menuView = new MenuView();
        this.menuView.getBtnClientes().addActionListener(e -> viewClientes());
        this.menuView.getBtnProdutos().addActionListener(e -> viewProdutos());
    }
    
    public void menu(){
        this.menuView.setVisible(true);
    }
    
    public void viewClientes(){
        ConsultaClienteView viewCliente = new ConsultaClienteView();
        viewCliente.setVisible(true);
    }
    public void viewProdutos(){
        ConsultaProdutos viewProdutos = new ConsultaProdutos();
        viewProdutos.setVisible(true);
        
    }
}
