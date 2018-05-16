import java.util.ArrayList;
import java.util.List; class Node {
public Node left, right, parent; 
public int height = 1;
public int value;

public Node (int val) 
{ this.value = val;
}
}
public class AVLTree {
private Node insertNode(Node root, int key) 
{ root = insertRec(root, key); 
PrintTreeStructure(root); 
System.out.println();
return root;
}


private Node insertRec(Node root, int key) {

if (root == null) {
root = new Node(key);

return root;
}
/* Otherwise, recur down the tree */ if (key < root.value){
root.left = insertRec(root.left, key);
root.height = Math.max(TreeHeight(root.left), TreeHeight(root.right)) + 1;
}
else if (key > root.value){
root.right = insertRec(root.right, key);
root.height = Math.max(TreeHeight(root.left), TreeHeight(root.right)) + 1;
}

return root;
}
//getting the height of the tree  




private static int TreeHeight (Node N) {
if (N == null) return 0;
return N.height;
}



//Balancing the tree
private Node insertBalneced(Node node, int value) 
{ if (node == null) {
return(new Node(value));
}

if (value < node.value) 
	{node.left	= insertNode(node.left,value);}

else {node.right=insertNode(node.right, value);}


/* 2. Update height of this ancestor node */
node.height = Math.max(TreeHeight(node.left), TreeHeight(node.right)) + 1;

/* 3. Get the balance factor of this ancestor node to check whether this node became unbalanced */

int balance = getBalance(node);
 

if (balance > 1 && value < node.left.value) return rightRotate(node);

if (balance < -1 && value > node.right.value) return leftRotate(node);

if (balance > 1 && value > node.left.value)
{
node.left =	leftRotate(node.left);
return rightRotate(node);
}

if (balance < -1 && value < node.right.value)
{
	node.right =	rightRotate(node.right);
	return leftRotate(node);
}
return node;
}







private Node rightRotate(Node y) 
{ Node x = y.left;
Node T2 = x.right;
x.right = y;
y.left = T2;

y.height = Math.max(TreeHeight(y.left), TreeHeight(y.right))+1;
x.height = Math.max(TreeHeight(x.left), TreeHeight(x.right))+1;

// Return new root
return x;
}







private Node leftRotate(Node x) 
{ Node y = x.right;
Node T2 = y.left;

y.left = x; 
x.right =T2;

x.height = Math.max(TreeHeight(x.left), TreeHeight(x.right))+1;
y.height = Math.max(TreeHeight(y.left), TreeHeight(y.right))+1;

return y;
}





private static int getBalance(Node N) { if (N == null)
return 0;
return TreeHeight(N.left) - TreeHeight(N.right);
}






private static Boolean BalencedOrNot(Node N) {
if (getBalance(N) == 0 || getBalance(N) == 1 || getBalance(N) == -1)
	return true;
 
else

return false;
}





private Node minValueNode(Node node) { Node current = node;
while (current.left != null) current = current.left;
return current;
}



private Node deleteNode(Node root, int value)
{ if (root == null)
	
return root;

if ( value < root.value )
	
root.left = deleteNode(root.left, value);

else if( value > root.value )
	
root.right = deleteNode(root.right,value);

else {
if( (root.left == null) || (root.right == null) )
{ Node temp;
if (root.left != null) temp = root.left;
 
else
temp = root.right;
if(temp == null) 
{ temp = root; root = null;
}
else
root = temp;
temp = null;
}
else {
Node temp = minValueNode(root.right);
root.value = temp.value;

root.right = deleteNode(root.right, temp.value);
}
}

if (root == null) return root;

root.height = Math.max(TreeHeight(root.left), TreeHeight(root.right)) + 1; int balance = getBalance(root);
if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);

if (balance > 1 && getBalance(root.left) < 0) { root.left =	leftRotate(root.left);
return rightRotate(root);
}

if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);

if (balance < -1 && getBalance(root.right) > 0) { root.right = rightRotate(root.right);
return leftRotate(root);
}

return root;
}








public void PrintTreeStructure(Node root) {

if(root == null) { System.out.println("(Empty AVL Tree)"); 
return;
}
int height = root.height, width = (int)Math.pow(2, height-1);
List<Node> current = new ArrayList<Node>(1),
next = new ArrayList<Node>(2);
current.add(root);

final int maxHalfLength = 4;
int elements = 1;

StringBuilder sb = new StringBuilder(maxHalfLength*width);
for(int i = 0; i < maxHalfLength*width; i++)
sb.append(' ');
String textBuffer;

for(int i = 0; i < height; i++) {

sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));
textBuffer = sb.toString();
for(Node n : current)
{ System.out.print(textBuffer); if(n == null) {
System.out.print("	");
next.add(null); next.add(null);
 
} else {

System.out.printf("(%6d)", n.value);
next.add(n.left);
next.add(n.right);

}

System.out.print(textBuffer);

}

System.out.println(); if(i < height - 1) {

for(Node n : current) 
{ System.out.print(textBuffer);
if(n == null) {
System.out.print("	 ");
}
else {
 

System.out.printf("%s   %s",
n.left == null ? " " : "/", n.right == null ? " " : "\\");
 
}
System.out.print(textBuffer);

}

System.out.println();

}

elements *= 2; current = next;
next = new ArrayList<Node>(elements);

}

}








public void printTree (Node root ,int level)	
{ if (root == null)
return;
if (level == 1) {
System.out.println( root.value);}
else if (level > 1)
{
printTree(root.left, level-1);
printTree(root.right, level-1);
}
}






public void printTree(Node root)	
{ int h = TreeHeight(root);
int i;
for (i=1; i<=h; i++) {
printTree(root, i);

}}




public static void main(String args[])
{ AVLTree tree = new AVLTree();
Node root = null;
root = tree.insertNode(root, 20);
root = tree.insertNode(root, 50);
root = tree.insertNode(root, 70);
root = tree.insertNode(root, 90);
root = tree.insertNode(root, 10);
root = tree.insertNode(root, 30);
System.out.println("// tree created ");
System.out.println(TreeHeight(root));
System.out.println(getBalance(root));
System.out.println(BalencedOrNot(root));
root = tree.deleteNode(root, 20);
root = tree.deleteNode(root, 50);
root = tree.deleteNode(root, 70);
root = tree.deleteNode(root, 90);
root = tree.deleteNode(root, 10);
root = tree.deleteNode(root, 30);

System.out.println("// tree deleted for insterting in balance");

root = tree.insertBalneced(root, 20);
root = tree.insertBalneced(root, 50);
root = tree.insertBalneced(root, 70);
root = tree.insertBalneced(root, 90);
root = tree.insertBalneced(root, 10);
root = tree.insertBalneced(root, 30);
tree.PrintTreeStructure(root);
System.out.println(" tree insereted in balance");
root = tree.deleteNode(root, 50);
tree.PrintTreeStructure(root);
System.out.println("50's deleted");




}
}
