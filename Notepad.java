import javax.swing.*;
import java.awt.*;
public class Notepad
{
	public static void main(String [] args)
	{
		NotepadFrame f1=new NotepadFrame();
		//f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f1.setExtendedState(f1.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		/*for(int i=0;i<1000;i++)
		{
			System.out.println(f1.getSize());
			try
			{
				Thread.sleep(2000);
			}
			catch(Exception e)
			{
			}
		}*/
	}
}
