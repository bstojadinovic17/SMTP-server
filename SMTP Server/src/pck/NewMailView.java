package pck;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
 

 
/**
 * A Swing application that allows sending e-mail messages from a SMTP server.
 * @author www.codejava.net
 *
 */
/*Klasa koja predstavlja view za korisnika kada zeli da posalje novi mejl drugom korisniku*/

public class NewMailView extends JFrame {
    
    
    private JLabel labelTo = new JLabel("To: ");
    private JLabel labelSubject = new JLabel("Subject: ");
    private JLabel labelFrom=new JLabel("From: ");
    
    private JTextField fieldFrom=new JTextField(30);
    private JTextField fieldTo = new JTextField(30);
    private JTextField fieldSubject = new JTextField(30);
     
    private JButton buttonSend = new JButton("SEND");
     
    
     
    private JTextArea textAreaMessage = new JTextArea(10, 30);
     
    private GridBagConstraints constraints = new GridBagConstraints();
     
    public NewMailView() {
        super("New Mail Window");
         
        // set up layout
        setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
     
        setupForm();
         
        pack();
        setLocationRelativeTo(null);    // center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        //setVisible(true);
    }
 
     
    private void setupForm() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelFrom, constraints);
         
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldFrom, constraints);
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelTo, constraints);
         
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldTo, constraints);
         
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        buttonSend.setFont(new Font("Arial", Font.BOLD, 16));
        add(buttonSend, constraints);
         
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	
            }
        });
         
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelSubject, constraints);
        
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(fieldSubject, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
         
        constraints.gridy = 4;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        add(new JScrollPane(textAreaMessage), constraints);    
    }


	public JTextField getFieldFrom() {
		return fieldFrom;
	}


	public void setFieldFrom(JTextField fieldFrom) {
		this.fieldFrom = fieldFrom;
	}


	public JTextField getFieldTo() {
		return fieldTo;
	}


	public void setFieldTo(JTextField fieldTo) {
		this.fieldTo = fieldTo;
	}


	public JTextField getFieldSubject() {
		return fieldSubject;
	}


	public void setFieldSubject(JTextField fieldSubject) {
		this.fieldSubject = fieldSubject;
	}


	public JButton getButtonSend() {
		return buttonSend;
	}


	public void setButtonSend(JButton buttonSend) {
		this.buttonSend = buttonSend;
	}


	public JTextArea getTextAreaMessage() {
		return textAreaMessage;
	}


	public void setTextAreaMessage(JTextArea textAreaMessage) {
		this.textAreaMessage = textAreaMessage;
	}
     
}