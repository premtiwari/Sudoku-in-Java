import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;



@SuppressWarnings("serial")
public class SudokuFrame extends JFrame implements ItemListener{

	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	static JComboBox cb; 
	TextArea area; 



    // time view class for run the time in the gui 
    class timeview extends Thread{  

        //  time panel
        JPanel Time;
		int timeinsec;

        // geting time panel for suduko panel
        public timeview(JPanel t)
        {
            Time = t;
        }

        public void reset()
		{
			timeinsec=0;
		}

        // run funtion for runing theread continues 
        public void run(){  
            System.out.println("thread is running..."); 

            timeinsec=0;

            JLabel t = new JLabel("Time");

            Time.add(t);

            while(true)
            {   
                
                t.setText(Integer.toString(timeinsec));
            
                t.setBounds(10,5,20,20);

                

                try {
                    
                    sleep(1000);// Setting up time for splash screen.

                } catch (Exception e) {

                    e.printStackTrace();

                }

                timeinsec++;
            
            }
        } 
    }

	
	public SudokuFrame() {


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setSize(830,600); 
		this.setMinimumSize(new Dimension(800,600));

		   
        setLayout(null);
		setVisible(true); 
		
		JMenuBar menuBar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenu help = new JMenu("Help");
		JMenuItem color = new JMenuItem("Color");
		JMenuItem about = new JMenuItem("About");
		JMenuItem newGame = new JMenuItem("New");
		JMenuItem solution = new JMenuItem("Solution");
		JMenuItem eixt = new JMenuItem("Exit");
		newGame.setIcon(new ImageIcon("img/new.PNG"));
		color.setIcon(new ImageIcon("img/col.PNG"));
		solution.setIcon(new ImageIcon("img/sol.PNG"));
		eixt.setIcon(new ImageIcon("img/exit.PNG"));
		about.setIcon(new ImageIcon("img/about.PNG"));
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// this few line of code runing image part of code or starting part of code
				JFrame aboutwindo=new JFrame(); 
				//aboutwindo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aboutwindo.setBounds(100, 100, 1000, 639); // setting bounds of the frame.
				aboutwindo.add(new JLabel(new ImageIcon("img\\gameimg.jpg")));
				aboutwindo.setVisible(true);


			}
		});
		eixt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//your actions
				removeAll();
				setVisible(false);
				return;
			}
		});
		//JMenuItem sixBySixGame = new JMenuItem("6 By 6 Game");
		//sixBySixGame.addActionListener(new NewGameListener(SudokuPuzzleType.SIXBYSIX,30));
		//JMenuItem nineByNineGame = new JMenuItem("9 By 9 Game");
		//nineByNineGame.addActionListener(new NewGameListener(SudokuPuzzleType.NINEBYNINE,26));
		//JMenuItem twelveByTwelveGame = new JMenuItem("12 By 12 Game");
		//twelveByTwelveGame.addActionListener(new NewGameListener(SudokuPuzzleType.TWELVEBYTWELVE,20));
		
		/*
		 * need to include this when solving algorithm is improved
		 JMenuItem sixteenBySizteenGame = new JMenuItem("16 By 16 Game");
		sixteenBySizteenGame.addActionListener(new NewGameListener(SudokuPuzzleType.SIXTEENBYSIXTEEN,16));
		*/
		//newGame.add(sixBySixGame);
		//newGame.add(nineByNineGame);
		//newGame.add(twelveByTwelveGame);
		//newGame.add(sixteenBySizteenGame);
		game.add(newGame);
		game.add(solution);
		game.add(eixt);
		help.add(color);
		help.add(about);
		menuBar.add(game);
		menuBar.add(help);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setBounds(0,0,570,600);  
		//windowPanel.setBackground(Color.CYAN);   
		//windowPanel.setPreferredSize(new Dimension(600,600));
		
		
		buttonSelectionPanel = new JPanel();
	//	buttonSelectionPanel.setBounds(00,1000,0,0); 
		buttonSelectionPanel.setPreferredSize(new Dimension(700,100));

		sPanel = new SudokuPanel();

		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);

		rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);


		// mode buttor
		JLabel label1 = new JLabel("Mode");
		label1.setText("Mode");
		label1.setBounds(585,80,80,50);
		this.add(label1);
        // designing buttons 
        JRadioButton rb1,rb2;
        rb1=new JRadioButton("Design");    
        rb1.setBounds(630,80,80,50);   
        rb1.setBackground(Color.CYAN);    
        rb2=new JRadioButton("Play");    
        rb2.setBounds(720,80,80,50);   
        rb2.setBackground(Color.CYAN);     
        ButtonGroup bg=new ButtonGroup();    
        bg.add(rb1);bg.add(rb2);    
        this.add(rb1);this.add(rb2);

        // dimention option button 
        JLabel label2 = new JLabel("Dim");
        label2.setText("Dim");
        label2.setBounds(580,130,50,30);
        this.add(label2);
        String dim_list[]={"2","3","4"};   
        cb=new JComboBox(dim_list);    
        cb.setBounds(605,130,50,30);    
        cb.addItemListener(this);
        this.add(cb);
        // show button
        JButton show=new JButton("Show");  
        show.setBounds(660,130,70,30); 
        this.add(show);  
        // hide button 
        JButton hide=new JButton("Hide");  
        hide.setBounds(740,130,70,30); 
        this.add(hide); 


        // save button 
        JButton Save=new JButton("Save");  
        Save.setBounds(575,180,70,30); 
        this.add(Save);  

        // load button 
        JButton Load=new JButton("Load");  
        Load.setBounds(655,180,70,30); 
        this.add(Load);  

        // rand button
        JButton Rand=new JButton("Rand");  
        Rand.setBounds(735,180,70,30); 
        this.add(Rand); 

        // reset button
        JButton Reset=new JButton("Reset");  
        Reset.setBounds(705,700,70,30); 
        this.add(Reset); 



        // level option button 
        JLabel Level = new JLabel("Level");
        Level.setText("Level");
        Level.setBounds(580,230,80,30);
        this.add(Level);
        String Level_list[]={"[3elect]","[2elect]","[1elect]"};   
        JComboBox Ll=new JComboBox(Level_list);    
        Ll.setBounds(615,230,80,30);    
        this.add(Ll);

        // finish button
        JButton Finish=new JButton("Finish");  
        Finish.setBounds(700,230,70,30); 
        this.add(Finish); 
		Finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//your actions
				removeAll();
				setVisible(false);
				return;
			}
		});

		// point text box
		JLabel Points = new JLabel("Points.");
		Points.setText("Points.");
		Points.setBounds(575,280,80,30);
		this.add(Points);

		JPanel pabel_point = new JPanel();
		pabel_point.setBackground(Color.white);
		pabel_point.setBounds(620,280,50,30);
		this.add(pabel_point);
		pabel_point.setLayout(null);
		// Making a button of message box.
		
        // time text box
        JLabel Time = new JLabel("Time");
        Time.setText("Time");
        Time.setBounds(680,280,60,30);
        this.add(Time);


        
        // making time panel
		JPanel pabel_Time = new JPanel();
		pabel_Time.setBackground(Color.white);
		pabel_Time.setBounds(720,280,50,30);
		this.add(pabel_Time);
        //start time runing 
        timeview t1 = new timeview( pabel_Time );
        t1.start();  
		pabel_Time.setLayout(null);

		
        area = new TextArea();    
        area.setBounds (580, 330, 230, 150);    
        this.add(area);

		
        // reset button
        JButton reset=new JButton("Reset");  
        reset.setBounds(650,500,70,30); 
        this.add(reset); 
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//your actions
				rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);
				t1.reset();
			}
		});


		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//your actions
				rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);
				t1.reset();
			}
		});





















		// makeing 2nd panel for right side buttons and for icon
		JPanel panel2=new JPanel();  
		panel2.setLayout(new FlowLayout());
		panel2.setBounds(550,0,260,600);    
		panel2.setBackground(Color.CYAN);  
		JLabel icon = new JLabel(new ImageIcon("img\\icon.jpg"));
		icon.setPreferredSize(new Dimension(180,80));
		// panel2.add(icon);
		// this.add(panel2);
		// icon.addActionListener(new ActionListener() {
		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		//your actions
		// 		removeAll();
		// 		setVisible(false);
		// 		return;
		// 	}
		// });


		JButton iconbutton=new JButton();  
		iconbutton.setSize(50,50);
        iconbutton.setBounds(620,05,0,0); 
       // this.add(iconbutton); 
		iconbutton.setSize(150,75);
		iconbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// this few line of code runing image part of code or starting part of code
				JFrame aboutwindo=new JFrame(); 
				//aboutwindo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				aboutwindo.setBounds(100, 100, 1000, 639); // setting bounds of the frame.
				aboutwindo.add(new JLabel(new ImageIcon("img\\gameimg.jpg")));
				aboutwindo.setVisible(true);


			}
		});
		iconbutton.add(icon);
		this.add(iconbutton);
		this.add(panel2);







  











	}
		

	public void rebuildInterface(SudokuPuzzleType puzzleType,int fontSize) {
		SudokuPuzzle generatedPuzzle = new SudokuGenerator().generateRandomSudoku(puzzleType);
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		for(String value : generatedPuzzle.getValidValues()) {
			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(42,50));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}
	
	private class NewGameListener implements ActionListener {

		private SudokuPuzzleType puzzleType;
		private int fontSize;
		
		public NewGameListener(SudokuPuzzleType puzzleType,int fontSize) {
			this.puzzleType = puzzleType;
			this.fontSize = fontSize;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(puzzleType,fontSize);
		}
	}
	
	public static void main(String[] args) {

        // // this few line of code runing image part of code or starting part of code
		// JFrame logo=new JFrame(); ;
		// logo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// logo.setBounds(100, 100, 1000, 639); // setting bounds of the frame.
		// logo.add(new JLabel(new ImageIcon("img\\gameimg.jpg")));
		// logo.setVisible(true);

		// try {
		// 	Thread.sleep(4000);// Setting up time for splash screen.

		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }


		// logo.removeAll();
		// logo.setVisible(false);



		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SudokuFrame frame = new SudokuFrame();
				frame.setVisible(true);
			}
		});
	}


    // item state change for change the grid of suduko like 9*9, 16*16 and 4*4
	public void itemStateChanged(ItemEvent e)
	{
		
		if (e.getSource() == cb) {

		int n=Integer.parseInt((String) cb.getSelectedItem());
         // for n == 2
        if(n==2)
        {  
			rebuildInterface(SudokuPuzzleType.SIXBYSIX, 26);

        }
        // for  n==3 
        if(n==3){
            rebuildInterface(SudokuPuzzleType.NINEBYNINE, 26);
        }

        // for n==4 
        if(n==4){
            
			rebuildInterface(SudokuPuzzleType.TWELVEBYTWELVE, 26);
		
		}


		}

		

	}

}