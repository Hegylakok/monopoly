package mono;

/**
 * Neptun mező által kiváltott, neptun üzenet felugró ablak, ami megelőzi az üzenet részleteit megjelenítő frame-t
 * @author Hegylakók
 */
public class NeptunUzenetJelzes extends javax.swing.JFrame {
    private int mouseX;
    private int mouseY;
    public NeptunUzenetJelzes() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TovabbGomb = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        TovabbGomb.setText("Tovább");
        TovabbGomb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TovabbGombActionPerformed(evt);
            }
        });
        jPanel1.add(TovabbGomb);
        TovabbGomb.setBounds(10, 140, 69, 23);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Kepek/neptunuzenet.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 0, 440, 170);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TovabbGombActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TovabbGombActionPerformed
        Uzenet t = new Uzenet();
        t.setVisible(true);
        t.setLocationRelativeTo(null);
        setVisible(false);
    }//GEN-LAST:event_TovabbGombActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        setLocation(getX() + evt.getX() - mouseX, getY() + evt.getY() - mouseY);
    }//GEN-LAST:event_formMouseDragged

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NeptunUzenetJelzes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TovabbGomb;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
