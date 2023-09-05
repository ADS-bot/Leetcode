/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // Create a hashmap to store the mappings between the nodes in the original list and the nodes in the new list
        Map<Node, Node> map = new HashMap<>();
        
        // Create a new node for the head of the new list and add it to the hashmap
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        
        Node current = head.next;
        Node newCurrent = newHead;
        
        // Copy the remaining nodes in the list
        while (current != null) {
            // Create a new node for the current node in the original list and add it to the hashmap
            Node newNode = new Node(current.val);
            map.put(current, newNode);
            
            // Set the next pointer of the previous node in the new list to the new node
            newCurrent.next = newNode;
            
            // Move to the next node in the original list and the new list
            current = current.next;
            newCurrent = newNode;
        }
        
        // Set the random pointers of the new nodes using the mappings in the hashmap
        current = head;
        newCurrent = newHead;
        while (current != null) {
            newCurrent.random = map.get(current.random);
            current = current.next;
            newCurrent = newCurrent.next;
        }
        
        return newHead;
    }
}
