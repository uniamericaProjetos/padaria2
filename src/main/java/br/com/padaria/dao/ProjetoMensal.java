package br.com.padaria.dao;


import br.com.padaria.controller.MenuController;

/**
 *
 * @author igoor
 */
public class ProjetoMensal {

    public static void main(String[] args) {

        //abre a tela de cliente (invokeLater e o jeito correto de abrir telas Swing)
        java.awt.EventQueue.invokeLater(() -> {
            MenuController menuController = new MenuController();
            menuController.menu();
        });
    }
}