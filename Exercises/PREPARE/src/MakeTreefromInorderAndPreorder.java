
public class MakeTreefromInorderAndPreorder {

    public static int pIndex = 0;

    public Node makeBTree(int[] inOrder, int[] preOrder, int iStart, int iEnd) {
        if (iStart > iEnd) {
            return null;
        }
        Node root = new Node(preOrder[pIndex]);
        pIndex++;

        if (iStart == iEnd) {
            return root;
        }
        int index = getInorderIndex(inOrder, iStart, iEnd, root.data);
        root.left = makeBTree(inOrder, preOrder, iStart, index - 1);
        root.right = makeBTree(inOrder, preOrder, index + 1, iEnd);
        //}
        return root;
    }

    public int getInorderIndex(int[] inOrder, int start, int end, int data) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public void printINORDER(Node root) {
        if (root != null) {
            printINORDER(root.left);
            printINORDER(root.right);
            System.out.print((char) root.data);
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        char[] myIn = {'A', 'B', 'C', 'D', 'E', 'F', 'J', 'G', 'I', 'H'};
        char[] myPre = {'J', 'C', 'B', 'A', 'D', 'E', 'F', 'I', 'G', 'H'};

        int[] inOrder = new int[100];
        int[] preOrder = new int[100];

        int k = 0;
        for (char x : myIn) {
            inOrder[k++] = x;
        }

        k = 0;
        for (char x : myPre) {
            preOrder[k++] = x;
        }

        MakeTreefromInorderAndPreorder i = new MakeTreefromInorderAndPreorder();
        Node x = i.makeBTree(inOrder, preOrder, 0, inOrder.length - 1);
        System.out.println("Constructed Tree : ");
        i.printINORDER(x);

        System.out.println("");
        i.printRotationLeftByInorder(x, 5, 5);

    }

    public void visit(Node p, int indent, int space) {
        if (p != null) {
            for (int i = space; i < indent; i++) {
                System.out.print("-");
            }
            System.out.println((char) p.data);
        }
    }

    public void printRotationLeftByInorder(Node p, int indent, int space) {
        if (p != null) {
            indent = indent + space;
            printRotationLeftByInorder(p.right, indent, space);
            visit(p, indent, space);
            printRotationLeftByInorder(p.left, indent, space);
        }
    }

}

class Node {

    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
