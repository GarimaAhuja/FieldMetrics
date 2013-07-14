package FieldMetricsCalculation.DataStructure;
import java.util.List;
import java.util.ArrayList;

/*
   Class to represent a Field/Column 
   and the calculated field Metrics
   */
public class Field
{
	private String fieldName;
	public List<String> fieldData;
	//to store Shannon's entropy
	private double H;
	//to store number of unique values;
	private int UqVal;
	//to store number of nulls
	private int N;
	//to store average frequency of values
	private double Favg;
	//to store maximum entropy
	private double HMax;
	//to store closed form u-value
	private double UVal;
	//to store potential pairs formed if used as single blocking variable
	private int pairs;
	//to store logPairs
	private double logPairs;
	//to store HMax%
	private double HMaxPercent;
	//to store N%
	private double NPercent;
	//to store target: target = 1 if field is good for matching, 0 otherwise
	private int target;

	public void Field()
	{
		fieldName="";
		fieldData = new ArrayList<String>();
	}
	public String getFieldName()
	{
		return fieldName;
	}
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}
	public double getH()
	{
		return H;
	}
	public void setH(double H)
	{
		this.H = H;
	}
	public int getUqVal()
	{
		return UqVal;
	}
	public void setUqVal(int UqVal)
	{
		this.UqVal=UqVal;
	}
	public int getN()
	{
		return N;
	}
	public void setN(int N)
	{
		this.N=N;
	}
	public double getFavg()
	{
		return Favg;
	}
	public void setFavg(double Favg)
	{
		this.Favg=Favg;
	}
	public double getHMax()
	{
		return HMax;
	}
	public void setHMax(double HMax)
	{
		this.HMax=HMax;
	}
	public double getUVal()
	{
		return UVal;
	}
	public void setUVal(double UVal)
	{
		this.UVal=UVal;
	}
	public int getPairs()
	{
		return pairs;
	}
	public void setPairs(int pairs)
	{
		this.pairs=pairs;
	}
	public double getLogPairs()
	{
		return logPairs;
	}
	public void setLogPairs(double logPairs)
	{
		this.logPairs=logPairs;
	}
	public double getHMaxPercent()
	{
		return HMaxPercent;
	}
	public void setHMaxPercent(double HMaxPercent)
	{
		this.HMaxPercent=HMaxPercent;
	}
	public double getNPercent()
	{
		return NPercent;
	}
	public void setNPercent(double NPercent)
	{
		this.NPercent=NPercent;
	}
	public int getTarget()
	{
		return target;
	}
	public void setTarget(int target)
	{
		this.target = target;
	}
}
