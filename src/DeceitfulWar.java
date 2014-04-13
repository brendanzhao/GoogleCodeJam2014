import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeceitfulWar
{
	public static void main(String[] args)
	{
		InputStream fileStream;
		BufferedReader fileReader;
		String[] linePartOne;
		String[] linePartTwo;
		int numEntries;
		int numBlocks;
		
		List<Double> naomiBlocks;
		List<Double> kenBlocks;
		
		try
		{
			fileStream = new FileInputStream("D-small.in");
			fileReader = new BufferedReader(new InputStreamReader(fileStream, Charset.forName("UTF-8")));
			numEntries = Integer.parseInt(fileReader.readLine());
			
			for (int i = 1; i <= numEntries; i++)
			{
				numBlocks = Integer.parseInt(fileReader.readLine());
				naomiBlocks = new ArrayList<Double>();
				kenBlocks = new ArrayList<Double>();
				linePartOne = fileReader.readLine().split(" ");
				linePartTwo = fileReader.readLine().split(" ");
				
				for (int j = 0; j < numBlocks; j++)
				{
					naomiBlocks.add(Double.parseDouble(linePartOne[j]));
					kenBlocks.add(Double.parseDouble(linePartTwo[j]));
				}
				
				Collections.sort(naomiBlocks);
				Collections.sort(kenBlocks);
				
				System.out.println(String.format("Case #%d: %d %d", i,
						NaomiPlaysDeceitfulWar(naomiBlocks, kenBlocks, numBlocks),
						NaomiPlaysWar(naomiBlocks, kenBlocks, numBlocks)));
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
	
	// assumes ken and naomi are sorted
	private static int NaomiPlaysWar(List<Double> naomi, List<Double> ken, int blocks, int initialPoints, int increment)
	{
		List<Double> naomiCopy;
		List<Double> kenCopy;
		double chosenNaomi;
		int chosenKenIndex;
		int naomiPoints;
		
		naomiCopy = new ArrayList<Double>(naomi);
		kenCopy = new ArrayList<Double>(ken);
		naomiPoints = initialPoints;
		
		for (int i = 0; i < blocks; i++)
		{
			chosenNaomi = naomiCopy.get(0);
			chosenKenIndex = 0;
			
			for (int j = 0; j < kenCopy.size(); j++)
			{
				if (kenCopy.get(j) > chosenNaomi)
				{
					chosenKenIndex = j;
					naomiPoints += increment;
					break;
				}
			}
			
			naomiCopy.remove(0);
			kenCopy.remove(chosenKenIndex);
		}
		
		return naomiPoints;
	}
	
	public static int NaomiPlaysWar(List<Double> naomi, List<Double> ken, int blocks)
	{
		return NaomiPlaysWar(naomi, ken, blocks, naomi.size(), -1);
	}
	
	public static int NaomiPlaysDeceitfulWar(List<Double> naomi, List<Double> ken, int blocks)
	{
		return NaomiPlaysWar(ken, naomi, blocks, 0, 1);
	}
}