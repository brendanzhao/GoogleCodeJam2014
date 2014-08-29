import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class CookieClickerAlpha
{
	public static void main(String[] args)
	{
		InputStream fileInputStream;
		BufferedReader fileReader;
		OutputStream fileOutputStream;
		BufferedWriter fileWriter;
		String[] lineParts;
		int numEntries;
		double cookiesPerSec;
		double costPerFarm;
		double cookieFarmPerSec;
		double cookiesToWin;
		double currentTime;

		try
		{
			fileInputStream = new FileInputStream("B-small.in");
			fileReader = new BufferedReader(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
			fileOutputStream = new FileOutputStream("B-small.out");
			fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8")));
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
				fileWriter.write(String.format("Case #%d: %.7f\r\n", i, currentTime));
			}

			fileWriter.close();
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