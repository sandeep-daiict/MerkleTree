
public class Node 
{
	public String hashvalue;
	public Node leftchild;
	public Node rightchild;
	public Node parent;
	public boolean isLeaf;
	public boolean isBlock;
	public String filename;
	public int blockaddress;
	Node(boolean block) 
	{
		isLeaf=false;
		isBlock=block;		
	}
}
