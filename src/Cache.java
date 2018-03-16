
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.*;



public class Cache 
{  
    
    String t="",s="",s1="";
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    String sqt ;
    int flag=0;
    File f=new File("src");
    URLConnection ucon=null;
    static File fg=null;
    static int a[]=new int[3];
    static int a1[]=new int[3];

    Cache(){
		
	   //("https://stackoverflow.com/questions/13829159/java-and-mysql-query-check-if-result-is-empty");
    	}


     public static void main(String ar[]){new Cache();}     	
 

	 public void exist(){
		 try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
		    String jdbcurl="jdbc:mysql://localhost/roger";
		    String user="root";
		    String password;
		    if(System.getProperty("os.name").startsWith("Windows"))
		          password="onhax";
		    else  password="hoax";
		    con=DriverManager.getConnection(jdbcurl,user,password);
		    System.out.println("connection created");
		    
			t=E.getText().replace("/", "");
			t=t.replace(":", "");
			t+=".html";
		    
		    String s="select * from net where sa "+"= "+'"'+E.getText()+'"';
		    System.out.println(s);
		    boolean k;
		    st=con.prepareStatement(s);
		    try{rs=st.executeQuery();k=rs.next();}
		    catch(Exception e){
		         k=false;}
		    fg=new File("src"+"/html/"+t);
		    if((k==false)||(!fg.exists()))
		    	{con.close();flag=0; fupdate();
		    	}  
		    else
		    {	a[0]=Integer.parseInt(rs.getString(3));
		    	a[1]=Integer.parseInt(rs.getString(4));
		    	a[2]=Integer.parseInt(rs.getString(5)); 
		    	con.close();
			    Date dNow = new Date( );
			    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
			    StringTokenizer sqt=new StringTokenizer(ft.format(dNow),".");
			    a1[0]=Integer.parseInt(sqt.nextToken());
			    a1[1]=Integer.parseInt(sqt.nextToken());
			    a1[2]=Integer.parseInt(sqt.nextToken());
			    System.out.println(""+a1[0]+"/"+a1[1]);
			    
			     if(a[0]+3<a1[0]||a[1]<a1[1]||a[1]<a1[1])
			     {   System.out.println("eh3");
		    	     flag=1;
			         fupdate();
			         }
			     else
			      {  System.out.println("eh5");
			         localrun();
			    	 }
		      }
		    }
		 catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		 catch (SQLException e) {
				e.printStackTrace();
			}
       }


	public void localrun(){
		 System.out.println("\n Local run \n");
		 System.out.println(f.getAbsolutePath()+"/"+t);
		 ProcessBuilder pb=null;
		 if(System.getProperty("os.name").startsWith("Windows"))
		          pb=new ProcessBuilder("C:/Program Files/Internet Explorer/iexplore.exe",f.getAbsolutePath()+"/html/"+t);
		 else
			 pb=new ProcessBuilder("opera",E.getText());
	     try {
			pb.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
	}

	public void fupdate(){
	     	     
			System.out.println("\n Update Started \n");
			URL u=null;
			InputStream is;
			int ch=0;
			String sr="",sy="";
			
			try {u=new URL(E.getText());} 
			catch (MalformedURLException e) {
				   E.setText("invalid url");
				   return;}
		    if(System.getProperty("os.name").startsWith("Windows"))
			 {ProcessBuilder m=new ProcessBuilder("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe",E.getText());
		      try {m.start();} catch (IOException e1) {e1.printStackTrace();} }
		     else
		     { ProcessBuilder m1=new ProcessBuilder("opera",E.getText());
		       try {m1.start();} catch (IOException e1) {e1.printStackTrace();}}


			try {
				ucon=u.openConnection();
				System.out.println("connected.........");
				is=ucon.getInputStream();
				System.out.println("stream created.........");

				while((ch=is.read())!=-1)
				{
					sr+=(char)ch;}
				} 
			catch (IOException e) {	
				e.printStackTrace();
		        }
			new Logs(this);
			if(flag==1)
				try {int chh; 
		        	 
		        	 FileInputStream fi=null;
		        	 if(fg.isFile())
		        	 {  fi=new FileInputStream(fg);
		        	 	while((chh=fi.read())==-1)
		        		     sy+=(char)chh;}
		        	  	fi.close();
		        	 } 
					catch (Exception e1) {
						 e1.printStackTrace();
					 }
			if (sy.equals(sr))
			{   return;
			}
			else{ FileOutputStream fo=null;
			   
			   try {
				fo=new FileOutputStream(f.getAbsolutePath()+"/html/"+t);
				System.out.println("file name :"+t +"\n nabspath:"+f.getAbsolutePath()+"/"+t);
				byte buf[]=sr.getBytes();
				System.out.println("buff add : "+buf);
				for(int i=0;i<buf.length;i++)
					fo.write(buf[i]);
				    fo.close();
			    }
			   catch (Exception e) {
				e.printStackTrace();
			    }
			 }
			if(flag==0){
				qupdate();
			}
	}
	
	public void qupdate(){
		System.out.println("\n Qupdate Started");
		try {
    		System.out.println("\n Query start \n");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
		    String jdbcurl="jdbc:mysql://localhost/roger";
		    String user="root";
		    String password;
		    if(System.getProperty("os.name").startsWith("Windows"))
		          password="onhax";
		    else  password="hoax";
		    con=DriverManager.getConnection(jdbcurl,user,password);
		    System.out.println("connection created");
		    
		   
		    st=con.prepareStatement("select max(id) from net ");
		    rs=st.executeQuery();
		    boolean k=rs.next();
		    int max;
		    if(!k)
		    	max=0;
		    else
		       max=rs.getInt(1);
		    System.out.println("\n"+max);
		    max++;	
		    Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
		    StringTokenizer sqt=new StringTokenizer(ft.format(dNow),".");
		    a1[0]=Integer.parseInt(sqt.nextToken());
		    a1[1]=Integer.parseInt(sqt.nextToken());
		    a1[2]=Integer.parseInt(sqt.nextToken());
		    System.out.println(""+a1[0]+"/"+a1[1]);
		    String s="insert into net values("+max+","+'"'+E.getText()+'"'+","+'"'+a1[0]+'"'+","+'"'+a1[1]+'"'+","+'"'+a1[2]+'"'+")";
		    st=con.prepareStatement(s);
		    try{
		    st.executeUpdate();}
		    catch(Exception e){}
		    con.close();
		    }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }


}

