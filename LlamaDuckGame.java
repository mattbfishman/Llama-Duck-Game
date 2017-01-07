// Matthew Fishman
// SER 210 
//
// Version: 1.0 
// Creates the Llama Duck game where the users are to hit a button
//saying if the image shown is of a llama or a duck. 

package images;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class LlamaDuckGame extends JPanel {
	
	//instance variables
	private final int DELAY = 1000;
	private final JLabel label,timeLabel;
	private JButton llamaDuck, llamaButton, duckButton;
	private int score = 0;
	private int counter = 0;
	private int time = 20;
	private boolean gameOver = false;
	
	//constructor
	public LlamaDuckGame(){
		super();
		
		setLayout(new BorderLayout());

		//sets the grid
		GridLayout labelGrid = new GridLayout(2,1);
		labelGrid.setVgap(10);
		
		//label panel
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(labelGrid);
		
		label = new JLabel("Score: " + score , SwingConstants.CENTER);
		labelPanel.add(label, BorderLayout.NORTH);
		timeLabel = new JLabel("Time: " + time, SwingConstants.CENTER);
		labelPanel.add(timeLabel, BorderLayout.CENTER);
		
		
		JPanel panel = new JPanel();
		
		//creating all the images used
		ImageIcon llama1 = createImageIcon("llama1.png","llama");
		ImageIcon llama2 = createImageIcon("llama2.png","llama");
		ImageIcon llama3 = createImageIcon("llama3.png","llama");
		ImageIcon llama4 = createImageIcon("llama4.png","llama");
		ImageIcon llama5 = createImageIcon("llama5.png","llama");
		ImageIcon duck1 = createImageIcon("duck1.png","duck");
		ImageIcon duck2 = createImageIcon("duck2.png","duck");
		ImageIcon duck3 = createImageIcon("duck3.png","duck");
		ImageIcon duck4 = createImageIcon("duck4.png","duck");
		ImageIcon duck5 = createImageIcon("duck5.png","duck");
		
		//reset button image
		ImageIcon reset = createImageIcon("reset.png", "reset");
		
		//makes an arrayList of imageIcons and adds all the duck and llama images to it
		final ArrayList<ImageIcon> llamaDuckImages = new ArrayList<ImageIcon>();
		llamaDuckImages.add(llama1);
		llamaDuckImages.add(llama2);
		llamaDuckImages.add(llama3);
		llamaDuckImages.add(llama4);
		llamaDuckImages.add(llama5);
		llamaDuckImages.add(duck1);
		llamaDuckImages.add(duck2);
		llamaDuckImages.add(duck3);
		llamaDuckImages.add(duck4);
		llamaDuckImages.add(duck5);
		
		//shuffles the list of images before the game starts 
		Collections.shuffle(llamaDuckImages);
		
		//sets the llama duck button to one of the images
		llamaDuck = new JButton(llamaDuckImages.get(counter));
		
		
		panel.add(llamaDuck);
		
		
		JPanel buttonPanel = new JPanel();
		llamaButton = new JButton("Llama");
		duckButton = new JButton("Duck");
		
		//sets the sizes of the buttons
		llamaButton.setPreferredSize(new Dimension(50,40));
		duckButton.setPreferredSize(new Dimension(50,40));
		
		//new Layout for the panel of buttons
		GridLayout grid = new GridLayout(1,2);
		grid.setHgap(10);//sets gap between buttons
		
		//sets the layout and adds the buttons
		buttonPanel.setLayout(grid);
		buttonPanel.add(llamaButton);
		buttonPanel.add(duckButton);
		
		//adds the panels to the main frames border layout
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(labelPanel,BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		
		//makes the timer and when the time runs out it will show the reset button
		Timer t = new Timer(DELAY, new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if (time > 0){
				time-=1;
				timeLabel.setText("Time: " + time);
				}
				else{
				llamaDuck.setIcon(reset);//changes the duck or llama picture to a reset button
				label.setText("GAME OVER! " + " " + "Final Score: " + score);//shows Game Over and player's final score
				gameOver = true;
				}
			}
		});
		
		//starts and stops the timer
		if(time > 0){
			t.start();
		}
		else{
			t.stop();
		}
		
		/*
		 * When the game it will allows the player to click the reset button
		 * which takes over the llama duck pictures. It also reset the game 
		 * back to the beginning
		 */
		llamaDuck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(gameOver == true){
					Collections.shuffle(llamaDuckImages);//shuffle images
					counter = 0;
					score = 0;
					time = 20;
					label.setText("Score: " + score);
					timeLabel.setText("Time: " + time);
					llamaDuck.setIcon(llamaDuckImages.get(counter));//set image
				}
				repaint();
				gameOver = false;
			}
		});
		
		/*
		 * When the llama button is clicked it will check if the game is over. If it is nothing will happen. If it's not a game over
		 * it will check if the array of images has cycled through all the images if it has it will reshuffle the array of images.
		 * then it will give a player a point if they clicked right and go to the next image if they clicked wrong it will just go to the
		 * next image. 
		 */
		llamaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(gameOver == false){
					if (counter == 9){
						Collections.shuffle(llamaDuckImages);
						counter = 0;
					}
					ImageIcon temp = llamaDuckImages.get(counter); 
					if(temp.getDescription() == "llama"){
						score++;
					}
					else if(temp.getDescription() == "duck"){
						score--;
					}
					if(time > 0){
						counter++;		
						llamaDuck.setIcon(llamaDuckImages.get(counter));
						label.setText("Score: " + score);
						}
					repaint();
				}
			}
		});
		
		/*
		 * When the duck button is clicked it will check if the game is over. If it is nothing will happen. If it's not a game over
		 * it will check if the array of images has cycled through all the images if it has it will reshuffle the array of images.
		 * then it will give a player a point if they clicked right and go to the next image if they clicked wrong it will just go to the
		 * next image. 
		 */
		duckButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (gameOver == false){
					if (counter == 9){
						Collections.shuffle(llamaDuckImages);
						counter = 0;
					}
					ImageIcon temp = llamaDuckImages.get(counter); 
					if(temp.getDescription() == "duck"){
						score++;
					}
					else if(temp.getDescription() == "llama"){
						score--;
					}
					if(time > 0){
					counter++;		
					llamaDuck.setIcon(llamaDuckImages.get(counter));
					label.setText("Score: " + score);
					}
				repaint();
				}
			}
		});
		
	}
	/** Returns an ImageIcon, or null if the path was invalid. 
	    * A good encapsulation for the validation and retrival of the image file. error */
	 private static ImageIcon createImageIcon(String path, String desc) {
	       java.net.URL imgURL = LlamaDuckGame.class.getResource(path);
	       if (imgURL != null) {
	           return new ImageIcon(imgURL,desc);
	       } else {
	           System.err.println("Couldn't find file: " + path);
	           return null;
	       }
	   }
}//end class
