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

public class MagicTrick
{
	public static void main(String[] args)
	{
		InputStream fileInputStream;
		BufferedReader fileReader;
		OutputStream fileOutputStream;
		BufferedWriter fileWriter;
		String toPrint;
		String[] lineParts;
		int numEntries;
		int row;
		int card;
		int result;
		boolean[] cards;

		try
		{
			fileInputStream = new FileInputStream("A-small.in");
			fileReader = new BufferedReader(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
			fileOutputStream = new FileOutputStream("A-small.out");
			fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8")));
			numEntries = Integer.parseInt(fileReader.readLine());

			for (int i = 1; i <= numEntries; i++)
			{
				cards = new boolean[16];
				result = -1;

				for (int j = 0; j < 2; j++)
				{
					row = Integer.parseInt(fileReader.readLine());

					for (int k = 1; k < row; k++)
					{
						fileReader.readLine();
					}

					lineParts = fileReader.readLine().split(" ");

					for (int k = 0; k < 4 - row; k++)
					{
						fileReader.readLine();
					}

					for (int k = 0; k < lineParts.length; k++)
					{
						card = Integer.parseInt(lineParts[k]);
						card--;

						if (cards[card])
						{
							result = result == -1 ? card : -2;
						}
						else
						{
							cards[card] = true;
						}
					}
				}

				switch (result)
				{
					case -2:
						toPrint = "Bad magician!";
						break;
					case -1:
						toPrint = "Volunteer cheated!";
						break;
					default:
						toPrint = Integer.toString(++result);
						break;
				}

				fileWriter.write(String.format("Case #%d: %s\r\n", i, toPrint));
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