import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.Math;
import DataStructure.Field;

public class FieldMetricsImplementation
{
	/**
	  *reads data from file and returns the list of Field
	  *data format: first line contains "|" seperated field names, following lines contain "|" seperated data
	  *@return list of Field
	  *@param filename to read the data from
	   **/
	public static List<Field> getDataFromFile(String fileName)
	{
		String firstLine,currLine;
		List<Field> fields =  new ArrayList<Field>();
		
		try
		{
			//getting field Names from the first line of file
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			firstLine=br.readLine();
			StringTokenizer st = new StringTokenizer(firstLine,"|");
			while(st.hasMoreElements())
			{
				Field newField = new Field();
				newField.setFieldName(st.nextElement().toString());
				newField.fieldData = new ArrayList<String>();
				fields.add(newField);
			}

			//getting field data from the rest of the lines of file
			while((currLine=br.readLine())!=null)
			{
				int i;
				StringTokenizer stcurr = new StringTokenizer(currLine,"|");
				for(i=0;i<fields.size();i++)
				{
					fields.get(i).fieldData.add(stcurr.nextElement().toString());
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return fields;	
	}

	/**
	   *function to calculate ShannonEntropy
	   *@param data of the field/column in the form of list of String
	   *@return Shannon Entropy
	   **/
	public static double calculateShannonEntropy(List<String> values) 
	{		 
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		// count the occurrences of each value
		for (String sequence : values) {
			if (!map.containsKey(sequence)) {
				map.put(sequence, 0);
			}
			map.put(sequence, map.get(sequence) + 1);
		}

		// calculate the entropy
		double result = 0.0;
		for (String sequence : map.keySet()) {
			Double frequency = (double) map.get(sequence) / values.size();
			result -= frequency * (Math.log(frequency) / Math.log(2));
		}

		return result;
	}

	/**
	  *function to calcuate number of unique values in the field/column
	   *@param data of the field/column in the form of list of String
	   *@return UniqueValues
	  **/
	public static int calculateUniqueValues(List<String> values)
	{
		//A set is a collection that contains no duplicates, hence the size will give us number of unique values
		HashSet<String> uniqueValues = new HashSet<String>(values);
		return uniqueValues.size();
	}

	/**
	  *function to calcuate number of null values in the field/column
	   *@param data of the field/column in the form of list of String
	   *@return NumberofNulls
	  **/
	public static int calculateNumberOfNulls(List<String> values)
	{
		int N=0;
		int i;
		for(i=0;i<values.size();i++)
		{
			if(values.get(i).equals(""))
			{
				N++;
			}
		}
		return N;
	}
	
	/**
	  *function to calculate average frequency of unique values in the field/column
	   *@param data of the field/column in the form of list of String
	   *@return averageFrequency 
	  **/
	public static double calculateAverageFrequency(int totalRecords,int UqVal)
	{
		double Favg = totalRecords/UqVal;
		return Favg;
	}


	/**
	  *function to calcuate maximum entropy
	  *@param data of the field/column in the form of list of String
	   *@return HMax
	  **/
	public static double calculateHMax(int totalRecords, int UqVal, double Favg)
	{
		double HMax = -((Favg/totalRecords)*Math.log(Favg/totalRecords)/Math.log(2)*UqVal)	;
		return HMax;	
	}


	//public static double calculateUVal(List<String> values,int totalRecords)
	//{
	//}

	public static void main(String args[])
	{
		int i,j;
		List<Field> fields;
		fields=getDataFromFile("testData.txt");

		//calculating field metrics
		for(i=0;i<fields.size();i++)
		{
			//getting Shannon Entropy
			fields.get(i).setH(calculateShannonEntropy(fields.get(i).fieldData));
			
			//getting number of unique values
			fields.get(i).setUqVal(calculateUniqueValues(fields.get(i).fieldData));
		
			//getting number of nulls
			fields.get(i).setN(calculateNumberOfNulls(fields.get(i).fieldData));
		
			//getting average frequency
			fields.get(i).setFavg(calculateAverageFrequency(fields.get(i).fieldData.size(),fields.get(i).getUqVal()));

			//getting HMax
			fields.get(i).setHMax(calculateHMax(fields.get(i).fieldData.size(),fields.get(i).getUqVal(),fields.get(i).getFavg()));
		}
	}
}
