package br.com.padaria.view;

import br.com.padaria.dao.ClienteDao;
import br.com.padaria.model.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultaClienteView extends javax.swing.JFrame {

    private List<Cliente> clientes = new ArrayList<>();

    public ConsultaClienteView() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 245, 220));
        carregarClientes();

        tabelaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    editarCliente();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        btnVoltar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titulo.setBackground(new java.awt.Color(102, 51, 0));
        titulo.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 153));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("PADARIA COFFEE (CONSULTA DE CLIENTES)");
        titulo.setOpaque(true);

        lblBuscar.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(102, 51, 0));
        lblBuscar.setText("Nome ou CPF:");

        txtBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtBuscar.addActionListener(this::txtBuscarActionPerformed);

        btnBuscar.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(102, 51, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tabelaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "CPF", "Telefone", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabelaClientes);

        btnEditar.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(102, 51, 0));
        btnEditar.setText("Editar selecionado");
        btnEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar.setContentAreaFilled(false);
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnEditar1.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnEditar1.setForeground(new java.awt.Color(102, 51, 0));
        btnEditar1.setText("Excluir selecionado");
        btnEditar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditar1.setContentAreaFilled(false);
        btnEditar1.addActionListener(this::btnEditar1ActionPerformed);

        btnVoltar2.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnVoltar2.setForeground(new java.awt.Color(102, 51, 0));
        btnVoltar2.setText("<");
        btnVoltar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVoltar2.setContentAreaFilled(false);
        btnVoltar2.addActionListener(this::btnVoltar2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditar1)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnEditar1)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVoltar2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        carregarClientes();
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        carregarClientes();
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       editarCliente(); 
       
       
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        excluirCliente();
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnVoltar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnVoltar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnVoltar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void carregarClientes() {
        try {
            ClienteDao dao = new ClienteDao();
            clientes = dao.listar(txtBuscar.getText().trim());

            DefaultTableModel modelo = (DefaultTableModel) tabelaClientes.getModel();
            modelo.setRowCount(0);

            for (Cliente cliente : clientes) {
                String nomeCompleto = cliente.getNome();
                if (cliente.getSobrenome() != null && !cliente.getSobrenome().isBlank()) {
                    nomeCompleto += " " + cliente.getSobrenome();
                }

                modelo.addRow(new Object[]{
                    cliente.getIdCliente(),
                    nomeCompleto,
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    cliente.getCidade()
                });
            }
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Nao foi possivel carregar os clientes."
            );
        }
    }

    private void excluirCliente() {
        int row = tabelaClientes.getSelectedRow();

        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(
                null,                             
                "Deseja mesmo excluir o cliente?",                         
                "Atenção",                           
                JOptionPane.YES_NO_OPTION,        
                JOptionPane.WARNING_MESSAGE       
        );

        if (resposta == JOptionPane.YES_OPTION) {
            System.out.println("- Usuário confirmou a exclusão");

            ClienteDao dao = new ClienteDao();
            long id = Long.parseLong(tabelaClientes.getModel().getValueAt(row, 0).toString());

            try{
                dao.deletar(id);
            }
            catch(SQLException e){
                e.printStackTrace();
            }

            carregarClientes();

            JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    private void editarCliente() {
        int linha = tabelaClientes.getSelectedRow();

        if (linha < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um cliente.");
            return;
        }

        ClienteView tela = new ClienteView(clientes.get(linha));
        tela.setLocationRelativeTo(this);
        tela.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                carregarClientes();
            }
        });
        tela.setVisible(true);
    }
}
