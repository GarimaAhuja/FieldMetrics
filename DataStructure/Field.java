package DataStructure;
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
}
