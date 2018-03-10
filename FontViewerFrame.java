import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import java.io.File;
public class FontViewerFrame extends JFrame implements ChangeListener,ActionListener,ItemListener
{
	public JLabel welcomeLabel;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JPanel mainPanel1,mainPanel2,colorPanel,stylePanel;
	JPanel colortoPanel,sizePanel,radioPanel,t1,t2,t3,t4,t5,p1;
	JRadioButton bgRadioButton,fgRadioButton,small,medium,large;
	ButtonGroup rbGroup,rbGroup1;
	JCheckBox boldCheckBox,normalCheckBox,italicCheckBox;
	JComboBox fontsComboBox;
	JSlider r,g,b;
	public void setDefaultProperties()
	{
		setVisible(true);
		setSize(600,500);
		setLocation(300,250);
		setResizable(true);
		setTitle("Font Viewer");
		try {
			setIconImage(ImageIO.read(new File("icon.png")));
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	public void addLabel1()
	{
		t1=new JPanel();
		l1=new JLabel("Color to");
		l1.setFont(new Font(l1.getName(), Font.BOLD,20));
		t1.add(l1);
		mainPanel2.add(t1);
	}
	public void addLabel2()
	{
		t2=new JPanel();
		l2=new JLabel("Style");
		l2.setFont(new Font(l2.getName(), Font.BOLD,20));
		t2.add(l2);
		mainPanel2.add(t2);
	}
	public void addLabel3()
	{
		t3=new JPanel();
		l3=new JLabel("Font Family");
		l3.setFont(new Font(l3.getName(),Font.BOLD,20));
		t3.add(l3);
		mainPanel2.add(t3);
	}
	public void addLabel4()
	{
		t4=new JPanel();
		l4=new JLabel("Size");
		l4.setFont(new Font(l4.getName(), Font.BOLD,20));
		t4.add(l4);
		mainPanel2.add(t4);
	}
	public void addLabel5()
	{
		t5=new JPanel();
		l5=new JLabel("Color");
		l5.setFont(new Font(l5.getName(), Font.BOLD,20));
		t5.add(l5);
		mainPanel2.add(t5);
	}
	public void addColorToPanel()
	{
		radioPanel=new JPanel();
		rbGroup=new ButtonGroup();
		fgRadioButton=new JRadioButton("Foreground",true);
		bgRadioButton=new JRadioButton("Background");
		rbGroup.add(fgRadioButton);
		rbGroup.add(bgRadioButton);
		radioPanel.add(fgRadioButton);
		radioPanel.add(bgRadioButton);
		mainPanel2.add(radioPanel);
	}
	public void addStylePanel()
	{
		stylePanel=new JPanel();
		boldCheckBox=new JCheckBox("Bold");
		normalCheckBox=new JCheckBox("Normal");
		italicCheckBox=new JCheckBox("Italic");
		stylePanel.add(boldCheckBox);
		stylePanel.add(normalCheckBox);
		stylePanel.add(italicCheckBox);
		mainPanel2.add(stylePanel);
	}
	public void addSizePanel()
	{
		small=new JRadioButton("Small");
		medium=new JRadioButton("Medium",true);
		large=new JRadioButton("Large");
		rbGroup1=new ButtonGroup();
		rbGroup1.add(small);
		rbGroup1.add(medium);
		rbGroup1.add(large);
		sizePanel=new JPanel();
		sizePanel.add(small);
		sizePanel.add(medium);
		sizePanel.add(large);
		mainPanel2.add(sizePanel);
	}
	public void addFontsPanel()
	{
		p1=new JPanel();
		fontsComboBox=new JComboBox();
		GraphicsEnvironment local=GraphicsEnvironment.getLocalGraphicsEnvironment();
		String []fonts=local.getAvailableFontFamilyNames();
		for(int i=0;i<fonts.length;i++)
			fontsComboBox.addItem(fonts[i]);
		p1.add(fontsComboBox);
		mainPanel2.add(p1);
	}
	public void addColorPanel()
	{
		colorPanel=new JPanel(new GridLayout(3,2));
		r=new JSlider(0,255,0);
		g=new JSlider(0,255,0);
		b=new JSlider(0,255,0);
		colorPanel.add(new JLabel("Red"));
		colorPanel.add(r);
		colorPanel.add(new JLabel("Green"));
		colorPanel.add(g);
		colorPanel.add(new JLabel("Blue"));
		colorPanel.add(b);
		mainPanel2.add(colorPanel);
	}
	public FontViewerFrame()
	{	
		setDefaultProperties();
		mainPanel1=new JPanel();
		mainPanel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		//mainPanel1.setBackground(new Color(255,255,255));
		welcomeLabel=new JLabel("Sample Text");
		welcomeLabel.setFont(new Font(welcomeLabel.getName(), Font.PLAIN, 50));
		welcomeLabel.setOpaque(true);
		mainPanel1.add(welcomeLabel);
		mainPanel1.setPreferredSize(new Dimension(350, 140));
		add(mainPanel1,BorderLayout.NORTH);
		mainPanel2=new JPanel(new GridLayout(5,2));
		addLabel1();
		addColorToPanel();
		addLabel2();
		addStylePanel();
		addLabel3();
		addFontsPanel();
		addLabel4();
		addSizePanel();
		addLabel5();
		addColorPanel();
		add(mainPanel2);
		small.addItemListener(this);
		medium.addItemListener(this);
		large.addItemListener(this);
		r.addChangeListener(this);
		g.addChangeListener(this);
		b.addChangeListener(this);
		r.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me)
			{
				setCursor(Cursor.HAND_CURSOR);
			}
			public void mouseExited(MouseEvent me)
			{
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		g.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me)
			{
				setCursor(Cursor.HAND_CURSOR);
			}
			public void mouseExited(MouseEvent me)
			{
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		b.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent me)
			{
				setCursor(Cursor.HAND_CURSOR);
			}
			public void mouseExited(MouseEvent me)
			{
				setCursor(Cursor.DEFAULT_CURSOR);
			}
		});
		boldCheckBox.addActionListener(this);
		normalCheckBox.addActionListener(this);
		italicCheckBox.addActionListener(this);
		fontsComboBox.addItemListener(this);
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==small || ie.getSource()==medium || ie.getSource()==large)
		{
			float size=(float)0.0;
			if(small.isSelected())
				size=(float)20.0;
			if(medium.isSelected())
				size=(float)50.0;
			if(large.isSelected())
				size=(float)70.0;
			Font f1=welcomeLabel.getFont().deriveFont(size);
			welcomeLabel.setFont(f1);
		}
		if(ie.getSource()==fontsComboBox)
		{
			String font=(String)fontsComboBox.getSelectedItem();
			Font currentFont=welcomeLabel.getFont();
			Font f1=Font.decode(font).deriveFont(currentFont.getStyle(),currentFont.getSize());
			welcomeLabel.setFont(f1);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		int style=0;
		if(boldCheckBox.isSelected())
			style+=Font.BOLD;
		if(normalCheckBox.isSelected())
			style+=Font.PLAIN;
		if(italicCheckBox.isSelected())
			style+=Font.ITALIC;
		Font f1=welcomeLabel.getFont().deriveFont(style);
		welcomeLabel.setFont(f1);
	}
	public void stateChanged(ChangeEvent ce)
	{
		Color c=new Color(r.getValue(),g.getValue(),b.getValue());
		if(fgRadioButton.isSelected())
			welcomeLabel.setForeground(c);
		if(bgRadioButton.isSelected())
			welcomeLabel.setBackground(c);
	}
}