/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomDecks;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class DeckLoader extends javax.swing.JFrame {

    /*        FIELDS         */
    private static ArrayList<File> savedDecks = new ArrayList<>();
    public CustomDeck chosenDeck;
    /**
     * Creates new form DeckLoader
     */
    public DeckLoader() {
        initComponents();
        this.setVisible(true);
        directoryLabel.setText("Checking: " + System.getProperty("user.dir") + File.separator + "Decks" + File.separator);
        populateCombo();
    }
    
    private void populateCombo(){
        boolean filesFound = false;
        File dir = new File(System.getProperty("user.dir") + File.separator + "Decks" + File.separator); //where we store the dataset files
        if(dir.exists()) System.out.println("found dir");
        else dir.mkdir();
        ArrayList<File> deckFiles = new ArrayList<>();
        for(File f : dir.listFiles()){
            if(!f.getName().endsWith(".deck"))continue; //skip it if it isnt a .ds
            filesFound = true;
            deckFiles.add(f);
        }
        if(filesFound)comboBox.removeAllItems(); //if we have files to display, get rid of that NONE message
        for(File f : deckFiles){
            comboBox.addItem(f.getName());
            savedDecks.add(f);
        }
    }
    /**
     * loads a custom deck based on selected file
     */
    private void loadSelected(){
        try {
            chosenDeck = new CustomDeck(savedDecks.get(comboBox.getSelectedIndex()));
            decknameLabel.setText(chosenDeck.deckName + " - " + chosenDeck.deckClass);
            Canvas canvas = new Canvas();
            canvas.setVisible(true);
            scrollPane.removeAll();
            scrollPane.add(canvas);
            canvas.setBackground(Color.red);
            canvas.setSize(500, 500);
            canvas.createBufferStrategy(3);
            BufferStrategy bs = canvas.getBufferStrategy();
            Graphics gr = bs.getDrawGraphics();
            Graphics2D g = (Graphics2D)gr;
            g.setColor(Color.white);
            g.fillRect(50, 50, 50, 50);
            g.dispose();         
            bs.show();
            canvas.setBackground(Color.yellow);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "IO Error");
            ex.printStackTrace();
        } catch (CorruptFileException ex) {
            JOptionPane.showMessageDialog(null, "Deck could not be loaded without error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HeaderLabel = new javax.swing.JLabel();
        directoryLabel = new javax.swing.JLabel();
        comboBox = new javax.swing.JComboBox<>();
        loadButton = new javax.swing.JButton();
        decknameLabel = new javax.swing.JLabel();
        scrollPane = new java.awt.ScrollPane();
        canvas1 = new java.awt.Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        HeaderLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        HeaderLabel.setText("Select Deck To Load");

        directoryLabel.setText("<FilePath>");

        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Decks Found in Current Directory" }));

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        decknameLabel.setText("<deckname>");

        scrollPane.add(canvas1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(directoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(HeaderLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(loadButton))
                                    .addComponent(decknameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(HeaderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decknameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        if(savedDecks.isEmpty())return;
        loadSelected();
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       DeckLoader dl = new DeckLoader();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HeaderLabel;
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JLabel decknameLabel;
    private javax.swing.JLabel directoryLabel;
    private javax.swing.JButton loadButton;
    private java.awt.ScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
