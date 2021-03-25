package util;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Scroll extends JFrame {
	public Scroll(String texto, String cabecalio) {
		JTextArea textArea = new JTextArea(texto);
		JScrollPane scrollPane = new JScrollPane(textArea);  
		
		textArea.setLineWrap(true);  
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
		
		JOptionPane.showMessageDialog(null, scrollPane, cabecalio, JOptionPane.NO_OPTION);
	}
}
