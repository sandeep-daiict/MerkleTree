import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Run 
{
	public Node node;
	public HashMap<Integer,Node> Treeold;
	Run(String path)
	{
		Treeold = new HashMap<Integer, Node>();
		node = initfile(path);
		
	}
	
	/*public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		String input=new String();
		Node root = new Node(false);
		while(!(input=scan.nextLine()).equals("exit"))
		{
			
			if(input.equals("create"))
			{
				System.out.println("Enter Mode:");
				
				input=scan.nextLine();
				
				if(input.equals("file"))
				{
					input=scan.nextLine();
					root = initfile(input);
				}
				else
				{
					input=scan.nextLine();
					root = init(input);
				}
				System.out.println("Tree Created Sucessfully");
				printTree(root);
			}
			if(input.equals("update"))
			{
				System.out.println("Enter Mode:");
				input=scan.nextLine();
				Node root2 = new Node(true);
				root2 = initfile(input);
				int linenum = check();
			}
		}				
	}*/
	
	
	public  Node initfile(String input) 
	{
		LinkedList<Node> Tree = new LinkedList<Node>();
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(input)));
			String Line = new String();
			int numlines=0;
			
			while((Line=br.readLine())!=null)
			{
				int hash = Line.hashCode();
				Node node=new Node(true);
				node.hashvalue=Integer.toString(hash);
				node.parent=null;
				node.leftchild=null;
				node.rightchild=null;
				node.isLeaf=true;
				node.blockaddress=numlines;
				Tree.add(node);
				Treeold.put(numlines,node);
				numlines++;
			}
			br.close();
			System.out.println("Number Of Lines:"+numlines);
			int i=1;
			while(i<numlines)
			{
				i=i*2;
				
			}
			int j=0;
			for(j=numlines;j<i;j++)
			{
				Line="sandeep";
				int hash = Line.hashCode();
				Node node=new Node(true);
				node.hashvalue=Integer.toString(hash);
				node.parent=null;
				node.leftchild=null;
				node.rightchild=null;
				node.isLeaf=true;
				node.blockaddress=j;
				
				Tree.add(node);
				Treeold.put(j,node);
			}
			
			//System.out.println("Tree Size:"+Tree.size());
			while(Tree.size()!=1)
			{
				
				Node nodeleft = new Node(false);
				nodeleft = Tree.removeFirst();
				
				Node noderight = new Node(false);
				noderight = Tree.removeFirst();
				
				Node node = new Node(false);
				String concat=nodeleft.hashvalue + noderight.hashvalue;
				node.hashvalue = Integer.toString(concat.hashCode());
				//System.out.println("hash:"+node.hashvalue);
				nodeleft.parent=node;
				noderight.parent=node;
				
				node.leftchild=nodeleft;
				node.rightchild=noderight;
				node.parent=null;
				Tree.addLast(node);
				//System.out.println("Tree Size:"+ Tree.size());
			}
			System.out.println("TreeoldSize:"+Treeold.size());
			System.out.println("Tree Size"+Tree.size());
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
		
		return Tree.get(0);
	}

	public  String getTree(Node root) 
	{
			if(root==null)
			{
				System.out.println("No Node In Tree");
				return "null";
			}
			LinkedList<Node> Tree = new LinkedList<Node>();
			
			Tree.add(root);
			String str = new String();
			while(!Tree.isEmpty())
			{	
				Node node = new Node(false);
				node = Tree.removeLast();
				//System.out.println("Value:"+node.hashvalue);
				str=str + node.hashvalue + ",";
				
				if(node.leftchild!=null || !node.isLeaf)
				{
					Tree.addFirst(node.leftchild);
				}
				else
				{
					//System.out.println("File"+node.filename);
				}
				if(node.rightchild!=null || !node.isLeaf)
				{
					Tree.addFirst(node.rightchild);
				}
				else
				{
					//System.out.println("File"+node.filename);
				}
			}
			return str;
	}

	public static Node init(String path) 
	{
		File folder = new File(path);
		File[] files = folder.listFiles();
		BufferedReader[] br = new BufferedReader[files.length];
		String[] filename = new String[files.length];
		int i=0;
		for(File f : files)
		{
			
			try 
			{
				br[i] = new BufferedReader(new FileReader(f));
				filename[i]=f.getName();
				i++;
			} 
			catch (FileNotFoundException e) 
			{
			
				e.printStackTrace();
			}
			
		}
		return CreateTree(br,filename);
	}

	private static Node CreateTree(BufferedReader[] br, String[] filename) 
	{
		int size = br.length;
		int i=1;
		while(i<size)
		{
			i=i*2;
		}
		//System.out.println("size:"+size+"i:"+i);
		//Node[] Tree = new Node[i];
		//ArrayList<Node> Tree = new ArrayList<Node>();
		LinkedList<Node> Tree = new LinkedList<Node>();
		for(int x=0;x<i;x++)
		{
			String data=new String();
			if(x<size)
			{
				String line =  new String();
				//System.out.println("x:"+x);
				try 
				{
					while((line=br[x].readLine())!=null)
					{
						//System.out.println("Line"+line);
						data += line;
						
					}
					br[x].close();
				} 
				catch (IOException e) 
				{
					
					e.printStackTrace();
				}
			}
			else
			{
				data="sandeep";
			}
			int hash =data.hashCode();
			Node node = new Node(false);
			node.hashvalue=Integer.toString(hash);
			node.leftchild=null;
			node.rightchild=null;
			node.parent=null;
			node.isLeaf=true;
			if(x<size)
				node.filename=filename[x];
			else
				node.filename="null";
			Tree.addLast(node);
						
		}
		/*for(int j=0;j<Tree.size();j++)
		{
			System.out.println("node:"+Tree.get(j).hashvalue);
		}*/
		//System.out.println("Here");
		while(Tree.size()!=1)
		{
			
			Node nodeleft = new Node(false);
			nodeleft = Tree.removeFirst();
			
			Node noderight = new Node(false);
			noderight = Tree.removeFirst();
			
			Node node = new Node(false);
			String concat=nodeleft.hashvalue + noderight.hashvalue;
			node.hashvalue = Integer.toString(concat.hashCode());
			System.out.println("hash:"+node.hashvalue);
			nodeleft.parent=node;
			noderight.parent=node;
			
			node.leftchild=nodeleft;
			node.rightchild=noderight;
			node.parent=null;
			Tree.addLast(node);
			System.out.println("Tree Size:"+ Tree.size());
		}
		
		return Tree.get(0);
	}

	public Node initfileline(String string,int lineno) 
	{
		Node node = Treeold.get(lineno);
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(new File(string)));
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		String Line = new String();
		int i=lineno;
        try {
			while(i!=0 && br.readLine()!=null)
			{
					i--;
					if(i==0)
						break;
			}
			Line = br.readLine();
			br.close();
			int hash = Line.hashCode();
			node.hashvalue=Integer.toString(hash);
			
			while(node.parent!=null)
			{
				node=node.parent;
				node.hashvalue=node.leftchild.hashvalue+node.rightchild.hashvalue;
			}
			return node;
		} 
        catch (IOException e) 
        {
			
			e.printStackTrace();
		}
		
		return node;
	}
}
	


