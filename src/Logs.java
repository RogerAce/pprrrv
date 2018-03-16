import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Logs implements Runnable
{	Cache c=null;
	public Logs(Cache c){
	    Thread	t=new Thread();
        t = new Thread (this);
        t.start ();
        this.c=c;}
	
	
	//public static void main(String ar[])
	
	public void run()
	{	System.out.println("Log Starts");
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		String hk[]=new String[100];
		String hkv[]=new String[100];
		
		try {
			
			//System.out.println(ucon.getHeaderFields().toString()+"\n \n");
			
			map=c.ucon.getHeaderFields();
			int n=0;
			for (Iterator it = map.keySet().iterator(); it.hasNext();) 
			{	   hk[n] =(String) it.next();
			       List<String> value =  map.get(hk[n]);
			       if( hk[n]==null)
		           		hk[n]="null";
			       hkv[n]=value.toString();
			       n++;}
			System.out.println(n);

			int i;
			for (i=0;i<n;i++)
			{	System.out.print(hk[i]);
				System.out.println("  : "+hkv[i]);}
			String clm = "";
			String blk[]=new String[100]; 
			String temp="";
			int x[]=new int[100];
			String lt=c.E.getText().replace("/", "");
			lt=lt.replace(":", "");
			lt+=".log";
			File f=new File ("src/weblogs/"+lt);
			
			if(!f.exists())
		    {   
		    	for(i=0;i<n;i++)
    			{   x[i]=(hkv[i].length()-hk[i].length());
    				temp="";
		    		for(int j=0;j<x[i]/2;j++) 
    				    temp+=" ";
		    		blk[i]=temp;
    			}
    					
		    	 for(i=0;i<n;i++)    		
		    		 clm+="|"+blk[i]+hk[i]+blk[i]+"|";
			    FileWriter bf=new FileWriter(f);
			    BufferedWriter bw = new BufferedWriter(bf);
			    System.out.println(clm+"\n");
			    bw.write("[000000"+","+n+"]");
			    bw.write("&");
			    for(i=0;i<n;i++)
			    	bw.write(x[i]+",");
			    bw.write("\n"+clm+"\n\n");
			    bw.close();
			    bf.close();}
		    
	         RandomAccessFile bf = new RandomAccessFile(f, "rw");
	         String rf=bf.readLine();
	         System.out.println(rf);

	         int ii=Integer.parseInt(rf.substring(1,(rf.indexOf(","))));
	         n= Integer.parseInt(rf.substring( rf.indexOf(',')+1,rf.indexOf(']')));
	         temp=rf.substring(rf.indexOf('&')+1, rf.length()-1);
	         StringTokenizer tk=new StringTokenizer(temp,",");
	         ii++;
	         System.out.println(temp+"   "+n+"   "+ii);
	         
	         for(i=0;i<n;i++)
	         {	 x[i]=Integer.parseInt(tk.nextToken());System.out.println(x[i]);}
	         String blk1[]=new String[100];
	         for(i=0;i<n;i++)
 			{   temp="";
	    		for(int j=0;j<-x[i]/2;j++) 
			    temp+=" ";
	    		blk1[i]=temp;
 			}

			String sl="";	            
		    for(i=0;i<n;i++)
		     		sl+="|"+blk1[i]+hkv[i]+blk1[i]+"|";
	         
	         rf=rf.substring(1,rf.indexOf(','));
	         int l=rf.length()-(""+ii).length();
	         bf.seek(1+l);
	         bf.writeBytes(ii+"");
	         bf.seek(bf.length());
	         bf.writeBytes(sl+"\n");
	         bf.close();
		
		}
		catch (Exception e) {	
			e.printStackTrace();
		  }		
	  
  }

}
