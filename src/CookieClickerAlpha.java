import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CookieClickerAlpha
{
	public static void main(String[] args)
	{
		InputStream fileStream;
		BufferedReader fileReader;
		String[] lineParts;
		int numEntries;
		double cookiesPerSec;
		double costPerFarm;
		double cookieFarmPerSec;
		double cookiesToWin;
		double currentTime;
		
		try
		{
			fileStream = new FileInputStream("B-small.in");
			fileReader = new BufferedReader(new InputStreamReader(fileStream, Charset.forName("UTF-8")));
			numEntries = Integer.parseInt(fileReader.readLine());
			
			for (int i = 1; i <= numEntries; i++)
			{
				lineParts = fileReader.readLine().split(" ");
				costPerFarm = Double.parseDouble(lineParts[0]);
				cookieFarmPerSec = Double.parseDouble(lineParts[1]);
				cookiesToWin = Double.parseDouble(lineParts[2]);
				currentTime = 0d;
				cookiesPerSec = 2d;
				
				while (!(cookiesToWin / cookiesPerSec < (costPerFarm / cookiesPerSec)
						+ (cookiesToWin / (cookiesPerSec + cookieFarmPerSec))))
				{
					currentTime += costPerFarm / cookiesPerSec;
					cookiesPerSec += cookieFarmPerSec;
				}
				
				currentTime += cookiesToWin / cookiesPerSec;
				System.out.println(String.format("Case #%d: %.7f", i, currentTime));
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}