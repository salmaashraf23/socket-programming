/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalsokcetclint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class chatClinet extends javax.swing.JFrame {

 static DataInputStream dataIn;
static DataOutputStream dataOut;
static Socket socket;
static Connection c;
static Statement ss;
static String  query;
static ResultSet result;
    public chatClinet() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        send_button = new javax.swing.JButton();
        the_massage = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        massage_area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        send_button.setText("send");
        send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_buttonActionPerformed(evt);
            }
        });

        the_massage.setName(""); // NOI18N

        massage_area.setColumns(20);
        massage_area.setRows(5);
        jScrollPane1.setViewportView(massage_area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(the_massage))
                .addGap(18, 18, 18)
                .addComponent(send_button, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(send_button)
                    .addComponent(the_massage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_buttonActionPerformed
        try{String msgOut="";
        msgOut= the_massage.getText().trim();
        dataOut.writeUTF(msgOut);
        }
        catch(Exception e )
        {
            
        }
    }//GEN-LAST:event_send_buttonActionPerformed

    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatClinet().setVisible(true);
            }
        });
      conc  getConnection = new conc();
    
try{
    c=getConnection.getCon();
       ss=c.createStatement();
      query="select * from massage";
      result=ss.executeQuery(query);
      while(result.next())
      {
          massage_area.setText(massage_area.getText()+"\n"+result.getString("msg"));
      }
      

            }
catch(SQLException e)
{
    System.out.println(e.getMessage());
    
}
         String msgIn="";
        try{
            // add= InetAddress.getLocalHost();
            socket=new Socket("192.168.43.160",1222);
            dataIn= new DataInputStream(socket.getInputStream());
            dataOut=new DataOutputStream(socket.getOutputStream());
            while(!msgIn.equals("exit"))
            {
                msgIn= dataIn.readUTF();
                
                 ss=c.createStatement();
                  query= "INSERT INTO massage VALUES ('"+ msgIn + "')";
                   ss.execute(query);
           
                massage_area.setText( massage_area.getText()+"\n"+msgIn);
            }
                 
        }
        catch(Exception e)
        {
            System.out.println(
           e.getMessage() );
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea massage_area;
    private javax.swing.JButton send_button;
    private javax.swing.JTextField the_massage;
    // End of variables declaration//GEN-END:variables
}
