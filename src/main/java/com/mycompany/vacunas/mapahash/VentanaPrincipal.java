package com.mycompany.vacunas.mapahash;

import classes.Vacuna;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends javax.swing.JFrame {
    private static Gson convertidor;
    private File db = new File("Datos.txt");
    protected int keyActual;
    protected static VentanaPrincipal ventanaprincipal;
    private NuevoRegistroForm ventanaRegistro;
    private NuevaVacunaForm nuevavacunaform;
    protected static HashMap<Integer, ArrayList<Vacuna>> registros;
    String[] titulos1 = {"Vacuna","Fecha"};
    DefaultTableModel mod = new DefaultTableModel(null, titulos1);
    public VentanaPrincipal() {
        initComponents();
        convertidor = new GsonBuilder().setPrettyPrinting().create();
        ventanaRegistro = new NuevoRegistroForm();
        nuevavacunaform = new NuevaVacunaForm();
        TablaVacunas.setModel(mod);
        NuevaVacunaButton.setEnabled(false);
    }
    private HashMap<Integer, ArrayList<Vacuna>> CargarDatos() throws IOException{
        String json = JsonConvert();
        HashMap<Integer, ArrayList<Vacuna>> registro = convertidor.fromJson(json, HashMap.class);
        if(registro!=null){           
            HashMap<Integer,ArrayList<Vacuna>> recorrer = (HashMap<Integer,ArrayList<Vacuna>>)registro.clone();
            ArrayList<Vacuna> x;
            ArrayList<Vacuna> nuevas;
            Iterator<Integer> y = recorrer.keySet().iterator();
            Vacuna nueva;
            int llave;
            while(y.hasNext()){
                    nuevas = new ArrayList<>();
                    llave = Integer.parseInt(String.valueOf(y.next()));
                    x = recorrer.get(String.valueOf(llave));
                    for(LinkedTreeMap va:x){
                        nueva = new Vacuna(va.get("vacuna").toString(),va.get("fecha").toString());
                        nuevas.add(nueva);
                    }
                    registro.remove(String.valueOf(llave));
                    registro.put(Integer.parseInt(String.valueOf(llave)), nuevas);
            }
        }else{
            return new HashMap<>();
        }
        return registro;
    }
    private String JsonConvert() throws IOException{
        String json = "";
        String linea = "";
        try {
            FileReader lector = new FileReader(db);
            BufferedReader datos = new BufferedReader(lector);
            while(linea!=null){
                linea = datos.readLine();
                if(linea!=null){
                    json = json + linea;
                }
            }
            datos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
    private void GuardarDatos() throws IOException{
        String str = convertidor.toJson(VentanaPrincipal.registros);
        FileWriter escribir = new FileWriter(db);
        escribir.write(str);
        escribir.close();
    }
    public static void limpiartabla(javax.swing.table.DefaultTableModel mo, javax.swing.JTable t){
        int n = t.getRowCount();
        for(int x = n-1; x >= 0; x--){
            mo.removeRow(x);
        }
    }
    protected void MostrarVacunas(ArrayList<Vacuna> vac){
        limpiartabla(mod,TablaVacunas);
        String[] agg = new String[2];
        for(Vacuna va:vac){
            agg[0]=va.getVacuna();
            agg[1]=va.getFecha();
            mod.addRow(agg);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EntradaCUI = new javax.swing.JTextField();
        Buscarbutton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        NuevaVacunaButton = new javax.swing.JButton();
        warningtext = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaVacunas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        exitbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        Buscarbutton.setText("BUSCAR");
        Buscarbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuscarbuttonMouseClicked(evt);
            }
        });
        Buscarbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarbuttonActionPerformed(evt);
            }
        });

        jLabel1.setText("REGISTRO DE VACUNAS MIUMG");

        NuevaVacunaButton.setText("NUEVA VACUNA");
        NuevaVacunaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NuevaVacunaButtonMouseClicked(evt);
            }
        });
        NuevaVacunaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevaVacunaButtonActionPerformed(evt);
            }
        });

        TablaVacunas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaVacunas);

        jButton1.setText("NUEVO REGISTRO");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        exitbutton.setText("SALIR");
        exitbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitbuttonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(NuevaVacunaButton)
                        .addGap(34, 34, 34))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitbutton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(EntradaCUI, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Buscarbutton))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(warningtext)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EntradaCUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscarbutton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NuevaVacunaButton)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(warningtext)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(exitbutton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NuevaVacunaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevaVacunaButtonActionPerformed
        this.setVisible(false);
        nuevavacunaform.setVisible(true);
    }//GEN-LAST:event_NuevaVacunaButtonActionPerformed

    private void BuscarbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarbuttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarbuttonActionPerformed

    private void BuscarbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscarbuttonMouseClicked
        limpiartabla(mod,TablaVacunas);
        ArrayList<Vacuna> vacunas;
        try{
            keyActual = Integer.parseInt(EntradaCUI.getText());
            if(!registros.containsKey(keyActual)){
                NuevaVacunaButton.setEnabled(false);
                warningtext.setText("CUI no encontrado en el registro");
                EntradaCUI.setText("");
            }else{
                warningtext.setText("");
                vacunas = registros.get(keyActual);
                NuevaVacunaButton.setEnabled(true);
                MostrarVacunas(vacunas);
            }
        }catch(NumberFormatException e){
            EntradaCUI.setText("");
            NuevaVacunaButton.setEnabled(false);
            warningtext.setText("CUI Ingresado no Válido");
        }
    }//GEN-LAST:event_BuscarbuttonMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.setVisible(false);
        EntradaCUI.setText("");
        warningtext.setText("");
        limpiartabla(mod,TablaVacunas);
        NuevaVacunaButton.setEnabled(false);
        ventanaRegistro.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void NuevaVacunaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevaVacunaButtonMouseClicked

    }//GEN-LAST:event_NuevaVacunaButtonMouseClicked

    private void exitbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitbuttonMouseClicked
        try {
            GuardarDatos();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_exitbuttonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ventanaprincipal = new VentanaPrincipal();
                    if(!ventanaprincipal.db.exists()){
                        ventanaprincipal.db.createNewFile();
                    }
                    registros = ventanaprincipal.CargarDatos();
                    ventanaprincipal.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscarbutton;
    private javax.swing.JTextField EntradaCUI;
    private javax.swing.JButton NuevaVacunaButton;
    private javax.swing.JTable TablaVacunas;
    private javax.swing.JButton exitbutton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel warningtext;
    // End of variables declaration//GEN-END:variables
}