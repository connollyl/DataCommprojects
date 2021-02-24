
import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Logan
 */
public class Client_UI extends javax.swing.JFrame {

    private Socket clientSocket;
    private BufferedReader Buffer;
    /**
     * Creates new form Client_UI
     */
    public Client_UI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtfld_IP = new javax.swing.JTextField();
        txtfld_Port = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtfld_intoServer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        scrllpne_pane = new javax.swing.JScrollPane();
        txtarea_fromServer = new javax.swing.JTextArea();
        btn_Connect = new javax.swing.JButton();
        btn_send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP Address");

        jLabel2.setText("Port");

        txtfld_IP.setText("127.0.0.1");

        txtfld_Port.setText("5764");

        jLabel3.setText("Send to Server");

        jLabel4.setText("Output");

        txtarea_fromServer.setEditable(false);
        txtarea_fromServer.setColumns(20);
        txtarea_fromServer.setRows(5);
        scrllpne_pane.setViewportView(txtarea_fromServer);

        btn_Connect.setText("Connect");
        btn_Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConnectActionPerformed(evt);
            }
        });

        btn_send.setText("Send");
        btn_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(scrllpne_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(txtfld_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(txtfld_Port, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtfld_intoServer)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(btn_Connect)
                    .addComponent(btn_send))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfld_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfld_Port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Connect)
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfld_intoServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_send)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrllpne_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConnectActionPerformed
        if (btn_Connect.getText().equals("Connect"))
        {
            try
            {
                int Port = Integer.parseInt(txtfld_Port.getText());
                clientSocket = new Socket (txtfld_IP.getText(), Port);
                Buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                btn_Connect.setText("Disconnect");
                txtarea_fromServer.append("Connected to Server\n");
            }
            catch (UnknownHostException e)
            {
                txtarea_fromServer.append("Failed to Connect to Server\n");
            }
            catch (IOException e)
            {
                txtarea_fromServer.append("Failed to Create Socket\n");
            }
            catch (IllegalArgumentException e)
            {
                txtarea_fromServer.append("Port is outside of valid range\n");
            }
        }
        else
        {
            try
            {
                clientSocket.close();
                btn_Connect.setText("Connect");
                txtarea_fromServer.append("Disconnected!\n");
            }
            catch (SocketException e)
            {
                txtarea_fromServer.append("Thread blocked in IO operation.\n");
            }
            catch (IOException e)
            {
                txtarea_fromServer.append("IO error while closing.\n");
            }
            
        }
    }//GEN-LAST:event_btn_ConnectActionPerformed

    private void btn_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendActionPerformed
        try 
        {
            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
            txtarea_fromServer.append("Client: " + txtfld_intoServer.getText() + "\n");
            toServer.writeBytes(txtfld_intoServer.getText() + '\n');
            String lastMessage = Buffer.readLine();
            txtarea_fromServer.append("Server: " + lastMessage + "\n");
            if (lastMessage.equals("Good Bye!"))
            {
		try
                {
                    clientSocket.close();
                    btn_Connect.setText("Connect");
                    txtarea_fromServer.append("Disconnected!\n");
                }
                catch (SocketException e)
                {
                    txtarea_fromServer.append("Thread blocked in IO operation.\n");
                }
                catch (IOException e)
                {
                    txtarea_fromServer.append("IO error while closing.\n");
                }
            }
            txtfld_intoServer.setText("");
        }
        catch (IOException e)
        {
            txtarea_fromServer.append("Error connecting to server.\n");
        }
        catch (Exception e)
        {
            txtarea_fromServer.append("Error.\n");
        }
    }//GEN-LAST:event_btn_sendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client_UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Connect;
    private javax.swing.JButton btn_send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane scrllpne_pane;
    private javax.swing.JTextArea txtarea_fromServer;
    private javax.swing.JTextField txtfld_IP;
    private javax.swing.JTextField txtfld_Port;
    private javax.swing.JTextField txtfld_intoServer;
    // End of variables declaration//GEN-END:variables
}
