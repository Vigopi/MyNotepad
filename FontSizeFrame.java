import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import javax.imageio.ImageIO;
import java.io.File;
public class FontSizeFrame extends JFrame
{
	public JSpinner spin;
	FontSizeFrame()
	{
		setVisible(true);
		setSize(100,100);
		setLocation(300,300);
		setResizable(true);
		setTitle("Font Size Chooser");
		spin=new JSpinner();
		add(spin);
	}
}