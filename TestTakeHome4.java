// Matthew Fishman
// SER 210 
//
// Version: 1.0 
// Main class to implement the Llama Duck Game
package images;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TestTakeHome4 extends JFrame {
	
	public TestTakeHome4(){
		super("Llama or Duck Game");//sets the title of the frame
		
		LlamaDuckGame app = new LlamaDuckGame();
		this.setSize(200,200);
		this.add(app);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
	}
	
	//main
	public static void main(String[] args){
		new TestTakeHome4();
	}

}
