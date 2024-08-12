package com.tetris.juan1639.principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
	
	private static final long serialVersionUID = -6182367842422944102L;
	
	public Main() {
        
        inicializa();
    }
    
    private void inicializa() {
    	
        getContentPane().add(new Ventana());
        
        setResizable(false);
        pack();
        
        setTitle("T3TR1S Java by Juan Eguia");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				try {
					JFrame principal = new Main();
					principal.setVisible(true);
	                
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e.getMessage());
	            }
			}
        });
	}
}

