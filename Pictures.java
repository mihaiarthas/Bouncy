import java.awt.Image;
import java.net.URL;

public class Pictures 
{
	static Image platform, ball;
	URL url;
	static StartingPoint sp;
	public Pictures(StartingPoint sp) 
	{
		try
		{
			url = sp.getDocumentBase();
		}
		catch(Exception e)
		{		
		}
		platform = sp.getImage(url, "Images/platform.png");
		this.sp = sp;
	}
}
