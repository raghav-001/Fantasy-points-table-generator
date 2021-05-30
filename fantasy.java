package Mathe;
import java.io.*;
public class fantasy {
	public static final String delimiter = ",";
	public static void main(String[] args) {
		String filename="C:\\Users\\Raghav\\Desktop\\Fantasy.csv";
		String names[]=new String[9];
		int penalties[]=new int[9];
		for (int i=0;i<9;i++) penalties[i]=0;
        int total_matches[]=new int[100];
        int matches_attempt[]=new int[100];
        System.out.println("IPL FANTASY POINTS TABLE");
        System.out.println("------------------------");
		try 
		{
			File file=new File(filename);
	        FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        String line = "";
	        String[] tempArr;
	        int count=0,total,line_index,string_index=0,match_index=0,matches_attempted;
	        while((line = br.readLine()) != null) 
	        {
	        	count++;
	        	matches_attempted=0;
	        	if (count==1) continue;
	        	line_index=0;
	            tempArr = line.split(delimiter);
	            total=0;
	            for(String tempStr : tempArr) 
	            {
	            	line_index++;
	            	if (line_index==1) continue;
	            	else if (line_index==2) 
	            	{
	            		names[string_index]=tempStr;
	            		string_index++;
	            	}
	            	else 
	            	{
	            		if (tempStr!="") matches_attempted++;
	            		else 
	            		{
	            			tempStr="2";
	            			penalties[string_index-1]++;
	            		}
	            		total+=Integer.parseInt(tempStr);
	            	}
	            }
	            matches_attempt[string_index-1]=matches_attempted;
	            total_matches[match_index]=total;
	            match_index++;
	         }
	         br.close(); 
	     } 
		catch(IOException ioe) 
		{
	            ioe.printStackTrace();
	    }
		float avg_points[]=new float[9];
		for (int i=0;i<9;i++)
			avg_points[i]=(float)total_matches[i]/matches_attempt[i];
		
		System.out.println("NAME\tMATCHES");
		System.out.println("---------------------");
		for (int i=0;i<9;i++)
		{
			System.out.println(names[i]+"\t  "+matches_attempt[i]);
		}
		System.out.println();
		System.out.println("NAME\tUNATTEMPTED MATCHES\tPENALTY COST");
		System.out.println("---------------------------------------------");
		for (int i=0;i<9;i++) 
		{
			System.out.println(names[i]+"\t\t"+penalties[i]+"\t\t"+penalties[i]*2+" points");
		}
		System.out.println();
		String temp; float temp1;
		for (int i=0;i<9;i++)
		{
			for (int j=0;j<9;j++)
			{
				if (avg_points[i]<avg_points[j])
				{
					temp1=avg_points[i];
					avg_points[i]=avg_points[j];
					avg_points[j]=temp1;
					temp=names[i];
					names[i]=names[j];
					names[j]=temp;
				}
			}
		}
		
		System.out.println();
		System.out.println("Ranklist");
		System.out.println("--------");
		int counter[]=new int[100];
		float temp3,temp4;
		int x=0,y=0,count=0;
		while(x<9)
		{	
			count=0;
			temp3=avg_points[x];
			temp4=avg_points[x];
			while(temp3==temp4)
			{
				count++;
				x++;
				if (x>8) break;
				temp4=avg_points[x];
			}
			counter[y]=count;
			y++;
		}
		
		int rank=1,ind=0;
		System.out.println("NAME\tRANK\tAVERAGE RANK PER MATCH");
		System.out.println("-------------------------------------");
		for (int i=0;i<y;i++)
		{
			for (int j=0;j<counter[i];j++)
			{
				System.out.println(names[ind]+"\t"+rank+"\t"+avg_points[ind]);
				ind++;
			}
			rank+=counter[i];		
		}
	}
}














     