import java.util.Stack;
public class BinaryTreeAlgo {//Division, Remainder -- Modulus % : 10%3 = 1 ;  3%10 = 3 ; 3%5  = 3 ; 10/3 = 3 ;
	public static void main(String[] args) {  // test save
		BinaryTree bt = new BinaryTree();
		bt.addNode(6);
		bt.addNode(8);
		bt.addNode(7);
		bt.addNode(9);
		bt.addNode(4);
		bt.addNode(5);
		bt.addNode(3);
		bt.addNode(2);
		bt.printInOrder();
	}
}
class BinaryTree
{
	Node rootNode = null;
//	(a) Inorder (Left, Root, Right) : 
//	(b) Preorder (Root, Left, Right) : 
//	(c) Postorder (Left, Right, Root) : 	
	void printInOrder()
	{
		printInOrder(rootNode);
	}
	void printInOrderNR(Node rootNode) // Non Recursive / Iterative Algorithm
	{
		if (rootNode == null)
			return;
		Stack <Node> stackOfNodes = new Stack<>(); 
		Node currentNode = rootNode; 
		while (!stackOfNodes.isEmpty() || currentNode != null) 
		{
			if (currentNode != null) 
			{ 
				stackOfNodes.push(currentNode); 
				currentNode = currentNode.leftNode; 
			} 
			else 
			{ 
				Node node = stackOfNodes.pop(); 
				System.out.print( node.nData + ", " );
				currentNode = node.rightNode; 
			}
		}
	}	
	void printInOrder(Node rootNode)
	{
		if (rootNode == null)
			return;
		printInOrder(rootNode.leftNode);
		System.out.print( rootNode.nData + ", " );  // syso + Cntrl + Space 
		printInOrder(rootNode.rightNode);
	}	
	void addNode( int x )
	{
		Node newNode = new Node(x);
		addNode(newNode);
	}
	void addNode(Node newNode)
	{
		if (rootNode == null)
		{
			rootNode = newNode;
			return;
		}		
		addNodeNR(rootNode, newNode);
		//addNode(rootNode, newNode);		
	}
	private void addNodeNR(Node currentNode, Node newNode) // NOT RECURSIVE
	{
		if (newNode == null)
			return;
		if (currentNode == null)
		{ // This code will never get executed. 
			currentNode = newNode;
			return;
		}
		while(true)
		{
			if (currentNode == null)
				return;
			if (newNode.nData > currentNode.nData)
			{
				if (currentNode.rightNode == null)
				{
					currentNode.rightNode = newNode;
					return;
				}
				else
					currentNode = currentNode.rightNode;
			}
			else
			{
				if (currentNode.leftNode == null)
				{
					currentNode.leftNode = newNode;
					return;
				}
				else
					currentNode =  currentNode.leftNode;
			}
		}
	}
	private void addNode(Node currentNode, Node newNode)
	{
		if (currentNode == null)
		{ // This code will never get executed. 
			currentNode = newNode;
			return;
		}
		if (newNode.nData > currentNode.nData)
		{
			if (currentNode.rightNode == null)
			{
				currentNode.rightNode = newNode;
				return;
			}
			else
				addNode( currentNode.rightNode,  newNode);
		}
		else
		{
			if (currentNode.leftNode == null)
			{
				currentNode.leftNode = newNode;
				return;
			}
			else
				addNode( currentNode.leftNode,  newNode);
		}
	}
	public static int heightoftree(Node root)  // return the height of tree
	{
		if(root==null)
			return -1;    
		else
		{	
		int left =heightoftree( root.leftNode );   // return the height of leftsubtree
		int right =heightoftree( root.rightNode ); // return the height of rightsubtree  
		 if (left > right)          // compare the height of left and right subtree
			 return left + 1; 
		 else
		 	return right + 1;
		}
	}
	public static int heightoftreeNR(Node rootNode)  // return the height of tree
	{  // Algo not correct - Work in Progress. 09/24/20.
		int leftHt = 0; int rightHt = 0;
		if(rootNode==null)
			return -1;    
		Node leftNode = rootNode.leftNode;
		Node rightNode = rootNode.rightNode ;
		while ((leftNode != null) || (rightNode !=null) )
		{
			if (leftNode != null)
			{
				leftHt++;
				leftNode = leftNode.leftNode;
			}
			if (rightNode != null)
			{
				leftHt++;
				rightNode = rightNode.rightNode;
			}					
		}
		 if (leftHt > rightHt)          // compare the height of left and right subtree
			 return leftHt + 1; 
		 else
		 	return rightHt + 1;
	}
}
class Node           // construct a node for binary tree 
{
	Node leftNode;    //left pointer
	Node rightNode;   //right pointer
	int nData;     // key
	Node(int nData)
	{
		this.nData = nData;
		leftNode = null;
		rightNode = null;
	}
}