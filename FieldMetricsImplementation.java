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
import java.io.File;
import java.io.FileOutputStream;

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
	   *@param total number of records and total number of unique values in that field
	   *@return averageFrequency 
	  **/
	public static double calculateAverageFrequency(int totalRecords,int UqVal)
	{
		double Favg = totalRecords/UqVal;
		return Favg;
	}


	/**
	  *function to calcuate maximum entropy
	  *@param total number of records, total number of unique values in the field, frequency average of the values  
	   *@return HMax
	  **/
	public static double calculateHMax(int totalRecords, int UqVal, double Favg)
	{
		double HMax = -((Favg/totalRecords)*Math.log(Favg/totalRecords)/Math.log(2)*UqVal)	;
		return HMax;	
	}


	/**
	  *function to calcuate closed-form u-value
	  *@param data of the field/column in the form of list of String and total number of records
	   *@return UVal
	  **/
	public static double calculateUVal(List<String> values,int totalRecords)
	{
		double UVal=0;
		Map<String, Integer> map = new HashMap<String, Integer>();

		// count the occurrences of each value
		for (String sequence : values) {
			if (!map.containsKey(sequence)) {
				map.put(sequence, 0);
			}
			map.put(sequence, map.get(sequence) + 1);
		}

		HashSet<String> uniqueValues = new HashSet<String>(values);
		for (String v : uniqueValues)
		{
			UVal = UVal+((double)map.get(v)/(double)totalRecords)*((double)map.get(v)/(double)totalRecords);
		}
		return UVal;
	}
	
	/**
	  *function to calcuate pairs formed if used as single blocking variable
	  *@param data of the field/column in the form of list of String
	   *@return pairs
	  **/
	public static int calculatePairs(List<String> values)
	{
		int i,j;
		int pairs=0;
		for(i=0;i<values.size();i++)
		{
			for(j=i+1;j<values.size();j++)
			{
				if((!values.get(i).equals(""))&&(values.get(i).equals(values.get(j))))
				{
					pairs++;
				}
			}
		}
		return pairs;
	}
	
	/**
	  *function to calcuate logPairs 
	  *@param pairs
	   *@return logPairs
	  **/
	public static double calculateLogPairs(int pairs)
	{
		double logPairs = Math.log(pairs)/Math.log(10);
		return logPairs;
	}

	/**
	  *function to calculate HMax%
	  *@param H,HMax
	  *@return HMax%
	  **/
	public static double calculateHMaxPercent(double H,double HMax)
	{
		return H/HMax;
	}
	
	/**
	  *function to calculate N%
	  *@param N,total number of records
	  *@return N%
	  **/
	public static double calculateNPercent(int N,int totalRecords)
	{
		return (double)N/(double)totalRecords;
	}

	//to print field metrics 
	public static void printFieldMetrics(List<Field> fields)
	{
		int i;
		System.out.println("Col|H|Hmax|Hmax%|UqVal|Favg|N|N%|Uval|pairs|log(pairs)");
		for(i=0;i<fields.size();i++)
		{
			System.out.println(fields.get(i).getFieldName()+"|"+fields.get(i).getH()+"|"+fields.get(i).getHMax()+"|"+fields.get(i).getHMaxPercent()+"|"+fields.get(i).getUqVal()+"|"+fields.get(i).getFavg()+"|"+fields.get(i).getN()+"|"+fields.get(i).getNPercent()+"|"+fields.get(i).getUVal()+"|"+fields.get(i).getPairs()+"|"+fields.get(i).getLogPairs());			
		}
	}

	public static void main(String args[])
	{
		int i,j;
		List<Field> fields;

		
		//getting field data from file
		fields=getDataFromFile("testDataLakh.txt");
		
		//to time time taken by the code to run
		long start=System.nanoTime();

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
			
			//getting UVal
			fields.get(i).setUVal(calculateUVal(fields.get(i).fieldData,fields.get(i).fieldData.size()));
			
			//getting pairs
			fields.get(i).setPairs(calculatePairs(fields.get(i).fieldData));

			//getting log(pairs)
			fields.get(i).setLogPairs(calculateLogPairs(fields.get(i).getPairs()));	

			//getting HMaxPercent
			fields.get(i).setHMaxPercent(calculateHMaxPercent(fields.get(i).getH(),fields.get(i).getHMax()));	
			
			//getting NPercent			
			fields.get(i).setNPercent(calculateNPercent(fields.get(i).getN(),fields.get(i).fieldData.size()));	
		}
		
		//to time time taken by the code to run
		long end=System.nanoTime();

		//printing field metrics
		printFieldMetrics(fields);
		System.out.println("\nTime taken by code to run: "+(end-start));
	}
}
