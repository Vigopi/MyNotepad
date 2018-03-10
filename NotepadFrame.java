import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javafx.event.Event;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.*;
public class NotepadFrame extends JFrame
{
	int position=0;
	String str="";
	Color fore,back;
	static int x=10,y=10;
	static String buffer;
	PrintWriter pw;
	FontViewerFrame f11;
	String path;
	public static int padCount=0;
	public JTextArea ta=new JTextArea();
	JScrollPane scrollPan=new JScrollPane(ta,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	JMenuBar menuBar=new JMenuBar();
	JMenu fileMenu,editMenu,helpMenu,fontMenu;
	JMenuItem fontSizeMI,fontStyleMItem;
	JMenuItem newMI,openMI,saveMI,saveAsMI;
	JMenuItem copyMI,cutMI,pasteMI,findMI,copy1MI,cut1MI,paste1MI,find1MI;
	JMenuItem aboutMI,exitMI;
	JCheckBoxMenuItem setNightModeMI,invertMI;
	JRadioButtonMenuItem redMI,greenMI;
	ButtonGroup themeGroup;
	
	JPopupMenu popup=new JPopupMenu();
	int col=ta.getColumns();
	NotepadFrame()
	{
		padCount++;
		setVisible(true);
		setSize(1000,700);
		setResizable(true);
		setTitle("Untitled "+padCount+".txt");
		path=getTitle();
		add(scrollPan);
		try {
			setIconImage(ImageIO.read(new File("index.jpg")));
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		//menuBar
		fileMenu=new JMenu("File");
		editMenu=new JMenu("Edit");
		helpMenu=new JMenu("Help");
		
		//menu items
		newMI=new JMenuItem("New",'N');
		openMI=new JMenuItem("Open",'O');
		saveMI=new JMenuItem("Save",'S');
		saveAsMI=new JMenuItem("Save as");
		exitMI=new JMenuItem("Exit");
		exitMI.setAccelerator(KeyStroke.getKeyStroke("alt"+KeyEvent.VK_F4));
		fileMenu.add(newMI);
		fileMenu.add(openMI);
		fileMenu.addSeparator();
		fileMenu.add(saveMI);
		fileMenu.add(saveAsMI);
		setNightModeMI=new JCheckBoxMenuItem("Night Mode");
		setNightModeMI.setAccelerator(KeyStroke.getKeyStroke("ctrl I"));
		invertMI=new JCheckBoxMenuItem("Invert");
		invertMI.setAccelerator(KeyStroke.getKeyStroke("ctrl alt I"));
		setNightModeMI.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					fore=ta.getForeground();
					back=ta.getBackground();
					ta.setBackground(Color.BLACK);
					ta.setForeground(Color.WHITE);
				}
				else
				{
					ta.setBackground(back);
					ta.setForeground(fore);
				}
			}
		});
		invertMI.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
					fore=ta.getForeground();
					back=ta.getBackground();
					ta.setBackground(new Color(255-back.getRed(),255-back.getGreen(),255-back.getBlue()));
					ta.setForeground(new Color(255-fore.getRed(),255-fore.getGreen(),255-fore.getBlue()));
			}
		});

		fileMenu.addSeparator();
		fileMenu.add(setNightModeMI);
		fileMenu.add(invertMI);
		fileMenu.addSeparator();
		fileMenu.add(exitMI);
		
		fontMenu=new JMenu("font");
		fontStyleMItem=new JMenuItem("font style");
		fontSizeMI=new JMenuItem("font size");
		copyMI=new JMenuItem("Copy",'C');
		cutMI=new JMenuItem("Cut",'X');
		pasteMI=new JMenuItem("Paste",'V');
		findMI=new JMenuItem("find",'F');
		
		copy1MI=new JMenuItem("Copy",'C');
		cut1MI=new JMenuItem("Cut",'X');
		paste1MI=new JMenuItem("Paste",'V');
		find1MI=new JMenuItem("find",'F');
		
		fontMenu.add(fontSizeMI);
		fontMenu.add(fontStyleMItem);
		
		editMenu.add(copyMI);
		editMenu.add(cutMI);
		editMenu.add(pasteMI);
		editMenu.add(fontMenu);
		editMenu.add(findMI);	

		aboutMI=new JMenuItem("About notepad");
		helpMenu.add(aboutMI);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		setJMenuBar(menuBar);
		
		popup.add(copy1MI);
		popup.add(cut1MI);
		popup.add(paste1MI);
		popup.add(find1MI);
		
		
		//Menu event handler
		openMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				//System.out.println("Clicked open");
				try
				{
					JFileChooser openFileChooser=new JFileChooser();
					openFileChooser.setCurrentDirectory(new File("."));
					openFileChooser.showOpenDialog(null);
					File f1=openFileChooser.getSelectedFile();
					path=f1.getAbsolutePath();
					if(ta.getText().length()!=0)
					{
						NotepadFrame np=new NotepadFrame();
						Scanner fin=new Scanner(f1);
						np.setTitle(f1.getName());
						while(fin.hasNext())
						{
							String x=fin.nextLine();
							np.ta.append(x);
							np.ta.append("\n");
						}
						fin.close();
					}
					else
					{
						setTitle(f1.getName());
						Scanner fin=new Scanner(f1);
					while(fin.hasNext())
					{
						String x=fin.nextLine();
						ta.append(x);
						ta.append("\n");
					}
					fin.close();
					}
				}
				catch(Exception e)
				{}
			}
		});
		saveMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					File f2=new File(path);
					PrintWriter pw1=new PrintWriter(f2);
					pw1.write(ta.getText());
					pw1.close();
					JOptionPane.showMessageDialog(null,f2.getName()+" is saved");
				}
				catch(Exception e)
				{}
			}
		});
		saveAsMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				//System.out.println("Clicked save");
				JFileChooser saveFileChooser=new JFileChooser();
				
				int v=saveFileChooser.showSaveDialog(null);
				try
				{
					if(v==0)
					{
				int val=JOptionPane.showConfirmDialog(null,"Do you want to save it");
				if(val==0)
				{
				File f1=saveFileChooser.getSelectedFile();
				path=f1.getAbsolutePath();
				setTitle(f1.getName());
				pw=new PrintWriter(f1);
				pw.write(ta.getText());
				pw.close();
				JOptionPane.showMessageDialog(null,f1.getName()+" is saved");
				}
				else
					JOptionPane.showMessageDialog(null,"file is not saved");
				}
				else
					JOptionPane.showMessageDialog(null,"Operation cancelled by the user");
				
				}
				catch(Exception e)
				{}
			}
		});
		//mnemoni
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		helpMenu.setMnemonic('H');
		
		//accelerator
		openMI.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		newMI.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		saveMI.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		saveAsMI.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
		
		copyMI.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		cutMI.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		pasteMI.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
		findMI.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		
		fileMenu.setToolTipText("This is file menu");
		editMenu.setToolTipText("This is edit menu");
		helpMenu.setToolTipText("This is help menu");
		newMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				NotepadFrame f1=new NotepadFrame();
				f1.setLocation(x+10,y+10);
				x+=10;
				y+=10;
				//f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//f1.setExtendedState(f1.getExtendedState()|JFrame.MAXIMIZED_BOTH);
			}
		});
		findMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String value=JOptionPane.showInputDialog("Find");
				if(value!=null)
				{
					if(ta.getText().contains(value))
					{
						if(!str.equals(value))
							position=0;
						int val;
						String text=ta.getText();
						str=value;
						position=text.indexOf(value,position);
						
							try
							{
								Rectangle viewRect = ta.modelToView(position);
                                // Scroll to make the rectangle visible
                                ta.scrollRectToVisible(viewRect);
                                // Highlight the text
                                ta.setCaretPosition(position + value.length());
                                ta.moveCaretPosition(position);
								position+=value.length();
							}
							catch(BadLocationException e)
							{
								System.out.println("Bad location Exception");
							}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null,"String not found");
					}
				}
			}
		});
		fontStyleMItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				f11=new FontViewerFrame();
				

				f11.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    ta.setFont(f11.welcomeLabel.getFont());
					ta.setForeground(f11.welcomeLabel.getForeground());
					ta.setBackground(f11.welcomeLabel.getBackground());
                    //System.exit(0);//cierra aplicacion
					JOptionPane.showMessageDialog(null,"Font is applied");
                }
            });
		

			}
		});
		aboutMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				JOptionPane.showMessageDialog(null,"This program is free software;\n you can redistribute it and/or modify it under the terms of the\nGNU General Public License as\n published by the Free Software\n Foundation; either version 2\n of the License, or (at your option)\n any later version.\n\nThis program is distributed in the\n hope that it will be useful,\n but WITHOUT ANY WARRANTY;\n without even the implied warranty\nof MERCHANTABILITY or FITNESS\nFOR A PARTICULAR PURPOSE.\n\nSee the GNU General Public License\nfor more details.","Notepad V3",JOptionPane.INFORMATION_MESSAGE);
			}
		});
	
		
		ta.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me)
			{
				if(me.isPopupTrigger())
					popup.show(me.getComponent(),me.getX(),me.getY());
			}
		});
		
		copyMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String txt=ta.getSelectedText();
				if(txt!=null)
					buffer=txt;
				else
					buffer=ta.getText();
			}
		});
		cutMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String txt=ta.getSelectedText();
				if(txt!=null)
				{
					buffer=txt;
					ta.setText(ta.getText().replace(ta.getSelectedText(),""));
				}
				else
				{
					buffer=ta.getText();
					ta.setText("");
				}
			}
		});
		pasteMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				//ta.append(buffer);
				String txt=ta.getSelectedText();
				if(txt!=null)
				{
					ta.setText(ta.getText().replace(ta.getSelectedText(),buffer));
				}
				else
				{
					ta.insert(buffer,ta.getCaretPosition());
				}
				
			}
		});
		
		copy1MI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String txt=ta.getSelectedText();
				if(txt!=null)
					buffer=txt;
				else
					buffer=ta.getText();
			}
		});
		cut1MI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				String txt=ta.getSelectedText();
				if(txt!=null)
				{
					buffer=txt;
					ta.setText(ta.getText().replace(ta.getSelectedText(),""));
				}
				else
				{
					buffer=ta.getText();
					ta.setText("");
				}
			}
		});
		paste1MI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				//ta.append(buffer);
				String txt=ta.getSelectedText();
				if(txt!=null)
				{
					ta.setText(ta.getText().replace(ta.getSelectedText(),buffer));
				}
				else
				{
					ta.insert(buffer,ta.getCaretPosition());
				}
				
			}
		});
		fontSizeMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				FontSizeFrame fontFrame=new FontSizeFrame();
				//fontFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				fontFrame.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
					Font fnt=ta.getFont();
					//(int)fontFrame.spin.getValue()
                    ta.setFont(fnt.deriveFont(fnt.getStyle(),(int)fontFrame.spin.getValue()));
					JOptionPane.showMessageDialog(null,"Font size is applied");
				}
                });
			}
		});
		
		exitMI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				//System.exit(0);
				int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the program?", "Exit Program Message Box",JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) 
				{
			padCount--;
				dispose();
				}
				if(padCount==0)
					System.exit(0);
			}
		});
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				padCount--;
				  dispose();
				if(padCount==0)
					System.exit(0);
			  }
			});
	}
}
class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
{
	public MyHighlightPainter(Color c)
	{
		super(c);
	}
}