import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class clientStub
{ 
    //private static Socket socket;
    public static String[] Tree;
    
    public static void main(String[] args)
    {
    	
    	
    	try
        {
    		
            int port = 8504;
           // ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Client Started and listening to the port 8504");
                                                                                                                                                               
            
            
            
            	ServerSocket MyServer = new ServerSocket(port);
                Socket socket = MyServer.accept();
                
                DataInputStream input = new DataInputStream(socket.getInputStream());
                PrintStream output = new PrintStream(socket.getOutputStream());
                
                BufferedWriter brwnew = new BufferedWriter(new FileWriter("client"));
                String filein = new String();
                
                while(true)
                {
                	filein =input.readLine();
                	if(filein.equals("EOFILE"))
                		break;
                	
                	System.out.println("in:"+filein);
                	brwnew.write(filein+"\n");
                }
                
                brwnew.close();
                //System.out.println("line:"+br.readLine());
                String strn =input.readLine();  
                Tree =strn.split(",");
                
                
                System.out.println("Tree created at client....."+strn);
                //System.out.println("Message received from client is "+number);
                int i=0;
                while(true)
                {
                	
                	
                	
                	System.out.println("Get Respnse From Server");
                	
                	String change = new String();
                	change=input.readLine();
                	//System.out.println("Earlier:"+Tree[i]);
                	//change=change.substring(0, change.length()-2);
                    System.out.println("change:"+change);
                    if(change.contains(":"))
                    {
                    	updateFile(change.split(":")[1],Integer.parseInt(change.split(":")[0]));
                    	i=0;
                    	System.out.println("Updated Tree:");
                    	for(int k=0;k<Tree.length;k++)
                    		System.out.println(Tree[k]+",");
                    	continue;
                    }
                    if(i==0)
                    {	
                    	System.out.println("Here");
                    	if(Tree[i].equals(change))
                    	{
                    		System.out.println("2");
                    		output.println("2");
                    	}
                    	else
                    	{
                    		System.out.println("3");
                    		output.println("3");
                    		Tree[i]=change;
                    		i++;
                    	}
                    }
                    else
                    {
                    	if(!Tree[i].equals(change.split(",")[0]))
                    	{
                    		output.println("0");
                    		Tree[i]=change.split(",")[0];
                    		i=2*i+1;
                    	}
                    	else
                    	{
                    		output.println("1");
                    		int m=i;
                    		m++;
                    		Tree[m]=change.split(",")[1];
                    		i=2*(i+1)+1;
                    		
                    	}
                    }
                }
            /*    
                try
                {
                    int numberInIntFormat = Integer.parseInt(number);
                    int returnValue = numberInIntFormat*2;
                    returnMessage = String.valueOf(returnValue) + "\n";
                }
                catch(NumberFormatException e)
                {
                    returnMessage = "Please send a proper number\n";
                }
 
                
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                System.out.println("Message sent to the client is "+returnMessage);
                bw.flush();
            }*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //socket.close();
            }
            catch(Exception e){}
        }
    }
	private static void updateFile(String string,int line) 
	{
		System.out.println("Line Changed:"+line);
		try {
			File f =new File("client");
			BufferedReader br = new BufferedReader(new FileReader(f));
			File File_new = new File("client1");
			BufferedWriter brw = new BufferedWriter(new FileWriter(File_new));
			
			while(line!=0)
			{
				brw.write(br.readLine()+"\n");
				line--;
			}
			br.readLine();
			brw.flush();
			brw.write(string+"\n");
			String str = new String();
			while((str=br.readLine())!=null)
			{
				brw.write(str+"\n");
			}
			brw.flush();
			brw.close();
			br.close();
			
			f.delete();
			File_new.renameTo(new File("client"));
			
			
		
			
		} 
		catch (FileNotFoundException e) 
		{
		
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}