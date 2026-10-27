package br.com.padaria.view;

import br.com.padaria.dao.ProdutoDao;
import br.com.padaria.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ConsultaProdutos extends javax.swing.JFrame {

    private List<Produto> produtos = new ArrayList<>();
    
    //objeto do dao, a tele pede os dados do banco por ele
    private ProdutoDao dao = new ProdutoDao();

    public ConsultaProdutos() {
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 245, 220));
        carregarProduto();

        tabelaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    editarProduto(); //duplo clique abre a edição
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVoltar1 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarProduto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        btnEditarProduto = new javax.swing.JButton();
        btnExcluirProduto = new javax.swing.JButton();
        btnVoltar2 = new javax.swing.JButton();

        btnVoltar1.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnVoltar1.setForeground(new java.awt.Color(102, 51, 0));
        btnVoltar1.setText("<");
        btnVoltar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVoltar1.setContentAreaFilled(false);
        btnVoltar1.addActionListener(this::btnVoltar1ActionPerformed);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titulo.setBackground(new java.awt.Color(102, 51, 0));
        titulo.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 153));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("PADARIA COFFEE (CONSULTA DE PRODUTOS)");
        titulo.setOpaque(true);

        lblBuscar.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(102, 51, 0));
        lblBuscar.setText("Nome do Produto:");

        txtBuscar.addActionListener(this::txtBuscarActionPerformed);

        btnBuscarProduto.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnBuscarProduto.setForeground(new java.awt.Color(102, 51, 0));
        btnBuscarProduto.setText("Buscar");
        btnBuscarProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuscarProduto.setContentAreaFilled(false);
        btnBuscarProduto.addActionListener(this::btnBuscarProdutoActionPerformed);

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Descrição", "Qtde", "Preço", "Fabricação", "Validade", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaProdutos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tabelaProdutos);

        btnEditarProduto.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnEditarProduto.setForeground(new java.awt.Color(102, 51, 0));
        btnEditarProduto.setText("Editar selecionado");
        btnEditarProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEditarProduto.setContentAreaFilled(false);
        btnEditarProduto.addActionListener(this::btnEditarProdutoActionPerformed);

        btnExcluirProduto.setFont(new java.awt.Font("Liberation Sans", 3, 15)); // NOI18N
        btnExcluirProduto.setForeground(new java.awt.Color(102, 51, 0));
        btnExcluirProduto.setText("Excluir selecionado");
        btnExcluirProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExcluirProduto.setContentAreaFilled(false);
        btnExcluirProduto.addActionListener(this::btnExcluirProdutoActionPerformed);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarProduto)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVoltar2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluirProduto)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarProduto)
                        .addGap(7, 7, 7))))
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
                    .addComponent(btnBuscarProduto))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirProduto)
                    .addComponent(btnEditarProduto))
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdutoActionPerformed
        carregarProduto();
    }//GEN-LAST:event_btnBuscarProdutoActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        carregarProduto();
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProdutoActionPerformed
        editarProduto();
        int linha = tabelaProdutos.getSelectedRow();

    
    if (linha < 0) {
        javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecione um produto na tabela para editar.");
        return;
    }

    Produto produtoSelecionado = produtos.get(linha);

    
    ProdutoView tela = new ProdutoView(null, true);
    tela.preencherParaEdicao(produtoSelecionado);
    tela.setLocationRelativeTo(this);
   
    tela.setVisible(true);
    
   
     // 
    }//GEN-LAST:event_btnEditarProdutoActionPerformed

    private void btnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutoActionPerformed
        excluirProduto();
    }//GEN-LAST:event_btnExcluirProdutoActionPerformed

    private void btnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnVoltar1ActionPerformed

    private void btnVoltar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltar2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnVoltar2ActionPerformed

    private void excluirProduto() {
        int row = tabelaProdutos.getSelectedRow();

        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(
                null,                             
                "Deseja mesmo excluir o Produto?",                         
                "Atenção",                           
                JOptionPane.YES_NO_OPTION,        
                JOptionPane.WARNING_MESSAGE       
        );

        if (resposta == JOptionPane.YES_OPTION) {
            System.out.println("- Usuário confirmou a exclusão");

            ProdutoDao dao = new ProdutoDao();
            long id = Long.parseLong(tabelaProdutos.getModel().getValueAt(row, 0).toString());

            try{
                dao.excluir(id);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            carregarProduto();

            JOptionPane.showMessageDialog(null, "Produto Excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProduto;
    private javax.swing.JButton btnEditarProduto;
    private javax.swing.JButton btnExcluirProduto;
    private javax.swing.JButton btnVoltar1;
    private javax.swing.JButton btnVoltar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void carregarProduto() {
        try {
            
            String busca =(txtBuscar.getText().trim());

            if (busca.isEmpty()){
                produtos = dao.listar();
            } else {
                produtos = dao.buscarPorNome(busca);
            }
            
            
            //limpa linhas antigas
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
            modelo.setRowCount(0);

            for (Produto p : produtos) {
                modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getDescricao(),
                    p.getQuantidade(),
                    p.getPreco(),
                    p.getDataFabricacao(),
                    p.getDataValidade(),
                    p.getStatus()
                });
            }
        } catch (RuntimeException ex) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Nao foi possivel carregar os produtos.\n" + ex.getMessage()
            );
        }
    }

    private void editarProduto() {
        int linha = tabelaProdutos.getSelectedRow(); //-1 = nenhuma linha selecionada

        if (linha < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto.");
            return;
        }
        
        Produto selecionado = produtos.get(linha); //produto da linha clicada
        javax.swing.JOptionPane.showMessageDialog(this, "Selecionado" + selecionado.getNome()); 
}
}