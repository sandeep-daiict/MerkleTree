import java.io.BufferedReader;
//import java.io.Bufferedprintlnr;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
//import java.io.OutputStreamprintlnr;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
 
public class serverStub
{
    //private static Socket socket; 
    @SuppressWarnings("deprecation")
	public static void main(String args[])
    {
        try
        {
        	BufferedReader brtransfer = new BufferedReader(new FileReader(new File("server")));
        	
        	String lines = new String();
        	String send = new String();
        	while((lines=brtransfer.readLine())!=null)
        	{
        		send=send+lines+"\n";
        	}
        	
            String host = "localhost";
            int port = 8504;
            InetAddress address = InetAddress.getByName(host);
            Socket socket = new Socket(address, port);; 
            DataInputStream input = new DataInputStream(socket.getInputStream());
 
            PrintStream output = new PrintStream(socket.getOutputStream());
            System.out.println("send"+send);
            output.println(send);
            brtransfer.close();
            output.println("EOFILE");
            Run synchronize = new Run("server");
            String Tree = new String();
            
            Tree = synchronize.getTree(synchronize.node);          
            System.out.println("Tree:"+Tree);
            output.println(Tree);
          
            String  in=new String();
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter update:");
            in=scan.nextLine();
            
            while(!in.equals("exit"))
            {
	           
            	
	            
	            String reply = new String();
	            if(in.equals("update"))
	            {
	            	int flag=1;
	            	
	            	System.out.println("Enter Line Number starting from 0: if not known Enter -1");
	            	int linenum=scan.nextInt();
	            	if(linenum==-1)
	            		synchronize.node=synchronize.initfile("server");
	            	else
	            		synchronize.node=synchronize.initfileline("server",linenum);
	            	System.out.println("UpdateTree:"+synchronize.getTree(synchronize.node));
	            	
	            	
	                output.println(synchronize.node.hashvalue);
	               
	                String str=new String();
	                
	                Node temp = synchronize.node;
	                while(!temp.isLeaf && temp.leftchild!=null && temp.rightchild!=null)
        			{
	                	str=input.readLine();
	                	System.out.println("String Response"+str);
		                if(str.equals("2"))
		                {
		                	flag=0;
		                	System.out.println("Data Not Modified..");
		                	break;
		                }
		                else if(str.equals("3"))
		                {
		                	reply=new String();
		                	reply=temp.leftchild.hashvalue+","+temp.rightchild.hashvalue;
		                }
		                else if(str.equals("0"))
		                {   
		                	temp=temp.leftchild;
		                	System.out.println("isLeaf"+temp.isLeaf+"child"+temp.leftchild);
		                	if(!temp.isLeaf && temp.leftchild!=null)
		                	{
		                		
		                		reply=temp.leftchild.hashvalue+","+temp.rightchild.hashvalue;
		                	}
		                	else
		                		break;
		                }
		                else if(str.equals("1"))
		                {      
		                	temp=temp.rightchild;
		                	System.out.println("isLeaf"+temp.isLeaf+"child"+temp.rightchild);
		                	if(!temp.isLeaf && temp.rightchild!=null)
		                	{
		                		
		                		reply=temp.leftchild.hashvalue+","+temp.rightchild.hashvalue;
		                	}
		                	else
		                		break;
		                	
		                }
		                output.println(reply);
		                
        			}
	                if(flag==1)
	                {
	                	BufferedReader br1 = new BufferedReader(new FileReader(new File("server")));
	                	int i=temp.blockaddress;
	                	System.out.println("i:"+i);
	                	while(i!=0&&br1.readLine()!=null)
	                	{
	                		i--;
	                		if(i==0)
	                			break;
	                	}
	                	
	                	reply=temp.blockaddress+":"+br1.readLine();
	                	System.out.println("Reply"+reply);
	                	output.println(reply);
	                	br1.close();
	           
	                }
	                
	                
	                
	                
	            }
	            in=scan.nextLine();
            }
            input.close();
            output.close();
            socket.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        
    }
}