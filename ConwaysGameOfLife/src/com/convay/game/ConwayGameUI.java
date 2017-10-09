/**
 * IFrame object to show the previous and updated states of "Conway's Games of Life"
 * 
 * @author Madhu Veldanda
 * Date: 10/08/2017
 */
package com.convay.game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ConwayGameUI extends JFrame
{
	private ConwaysGame game = new ConwaysGame();
    private JLabel updatedStatusLabel;
    private JLabel infoLabel;
    private JButton updateButton;
    private JButton[][] buttons = new JButton[StaticValues.rows][StaticValues.cols];
    int[][] updatedState;
    //Initial input
    int[][] currentState = 
    { { 0, 0, 0, 0, 0, 0, 1, 0},
        { 1, 1, 1, 0, 0, 0, 1, 0},
        { 0, 0, 0, 0, 0, 0, 1, 0},
        { 0, 0, 0, 0, 0, 0, 0, 0},
        { 0, 0, 0, 1, 1, 0, 0, 0},
        { 0, 0, 0, 1, 1, 0, 0, 0},
    };
    
    public ConwayGameUI()
    {
        super("Convay's  Life Of Game");
    }

    /*
     * Create graph and display
     */
    private void createAndDisplayGrid()
    {       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        JPanel leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));    
        updatedStatusLabel = new JLabel("<<< PREVIOUS-UPDATED >>>", JLabel.CENTER);
        updatedStatusLabel.setFont(new Font("Serif", Font.BOLD, 20));
        updatedStatusLabel.setForeground(Color.BLUE);

        infoLabel = new JLabel("        CLICK CELL TO CHANGE THE INPUT >>>", JLabel.CENTER);
        infoLabel.setFont(new Font("Serif", Font.BOLD, 12));
        infoLabel.setForeground(Color.BLUE);

        JPanel buttonLeftPanel = new JPanel();
        updateButton = new JButton("UPDATE");
        updateButton.setFont(new Font("Serif", Font.BOLD, 20));
        updateButton.setForeground(Color.BLUE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));

        JPanel previousStatePanel = new JPanel();
        previousStatePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	if(updatedState == null)
            		updatedState = currentState;
                //int[][] updatedState = currentState;
            	int[][] oldState = new int[StaticValues.rows][StaticValues.cols];
            	System.arraycopy(updatedState, 0, oldState, 0, updatedState.length);
            	drawGraph(oldState, previousStatePanel, false);
            	updatedState = game.updatedStatus(getUpdatedValues(), StaticValues.rows, StaticValues.cols);
            	//currentState = updatedState;
            	//statusLabel.setText("Updated State");
            	drawGraph(updatedState, buttonPanel, true);
            }
        });
        //labelPanel.add(statusLabel);
        buttonLeftPanel.add(updateButton);
        centerPanel.add(updatedStatusLabel);
        centerPanel.add(infoLabel);
        leftPanel.add(buttonLeftPanel);
        contentPane.add(leftPanel);
        previousStatePanel.setBackground(Color.WHITE);
        previousStatePanel.setLayout(new GridLayout(StaticValues.rows, StaticValues.cols, 10, 10));
        drawGraph(currentState, previousStatePanel, false);
        contentPane.add(previousStatePanel);
        contentPane.add(centerPanel);
        buttonPanel.setEnabled(false);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(StaticValues.rows, StaticValues.cols, 10, 10));
        drawGraph(currentState, buttonPanel, true);
        //contentPane.add(updatedStatusLabel);
        //contentPane.add(infoLabel);
        contentPane.add(buttonPanel);
        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    /**
     * Get the cell values from the existing graph/UI to make the updated
     * state as initial to recalculate.
     * @return
     */
    private int[][] getUpdatedValues(){
    	int[][] gridState = new int[StaticValues.rows][StaticValues.cols];
        for (int i = 0; i < StaticValues.rows; i++)
        {
            for (int j = 0; j < StaticValues.cols; j++)
            {
            	JButton jButton = buttons[i][j];
             	String bText = jButton.getText();
            	if(StaticValues.dead.equals(bText)){
            		gridState[i][j] = 0;
             	}
            	else{
            		gridState[i][j] = 1;
            	}
            }
        }
        
        return gridState;
    }
    
    /**
     * method to draw the updated state
     * @param drawState
     * @param buttonPanel
     * @param buttonStatus
     */
    private void drawGraph(int[][] drawState, JPanel buttonPanel, boolean buttonStatus){
    	buttonPanel.removeAll();
    	if(buttonStatus)
    		buttons = new JButton[StaticValues.rows][StaticValues.cols];
        for (int i = 0; i < StaticValues.rows; i++)
        {
            for (int j = 0; j < StaticValues.cols; j++)
            {
            	JButton button = new JButton();
            	button.setName("b_"+i+"_"+j);
            	if(buttonStatus)
            		buttons[i][j] = button;
            	button.setEnabled(buttonStatus);
                if (drawState[i][j] == 0){
                	button.setText(".");
                	button.setBackground(Color.LIGHT_GRAY);
                }
                else{
                	button.setText("o");
                	button.setBackground(Color.GREEN);
                }
                
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                    	String bText = button.getText();
                    	if(StaticValues.dead.equals(bText)){
                    		button.setText(StaticValues.live);
                    		button.setBackground(Color.GREEN);
                    	}
                    	else{
                    		button.setText(StaticValues.dead);
                    		button.setBackground(Color.LIGHT_GRAY);
                    	}
                    }
                });
                buttonPanel.add(button);
            }
            System.out.println();
        }
        buttonPanel.validate();
        buttonPanel.repaint();
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ConwayGameUI().createAndDisplayGrid();
            }
        });
    }
}