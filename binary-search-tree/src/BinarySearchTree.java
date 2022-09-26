public class BinarySearchTree implements IndexInterface<TreeNode> {
  private TreeNode root;

  public BinarySearchTree() {
    root = null;
  }

  public TreeNode search(Comparable searchKey) {
    return searchItem(root, searchKey);
  }

  private TreeNode searchItem(TreeNode node, Comparable key) {
    if (node == null) {
      return null;
    }

    int result = key.compareTo(node.key);

    if (result == 0) {
      return node;
    } else if (result < 0) {
      return searchItem(node.left, key);
    } else {
      return searchItem(node.right, key);
    }
  }

  public void insert(Comparable insertKey) {
    root = insertItem(root, insertKey);
  }

  private TreeNode insertItem(TreeNode tNode, Comparable newItem) {
    if (tNode == null) {
      tNode = new TreeNode(newItem);
    } else if (newItem.compareTo(tNode.key) < 0) {
      tNode.left = insertItem(tNode.left, newItem);
    } else {
      tNode.right = insertItem(tNode.left, newItem);
    }

    return tNode;
  }

  public void delete(Comparable key) {
    root = deleteItem(root, key);
  }

  private TreeNode deleteItem(TreeNode tNode, Comparable key) {
    if (tNode == null) {
      return null;
    }

    int result = key.compareTo(tNode);

    if (result == 0) {
      tNode = deleteNode(tNode);
    } else if (result < 0) {
      tNode.left = deleteItem(tNode.left, key);
    } else {
      tNode.right = deleteItem(tNode.right, key);
    }

    return tNode;
  }

  private TreeNode deleteNode(TreeNode tNode) {
    if (tNode.left == null && tNode.right == null) {
      return null;
    } else if (tNode.left == null) {
      return tNode.right;
    } else if (tNode.right == null) {
      return tNode.left;
    }

    returnPair rPair = deleteMinItem(tNode.right);
    tNode.key = rPair.key;
    tNode.right = rPair.node;

    return tNode;
  }

  private returnPair deleteMinItem(TreeNode tNode) {
    if (tNode.left == null) {
      return new returnPair(tNode.key, tNode.right);
    } else {
      returnPair rPair = deleteMinItem(tNode.left);
      tNode.left = rPair.node;
      rPair.node = tNode;
      return rPair;
    }
  }

  private class returnPair {
    private Comparable key;
    private TreeNode node;

    private returnPair(Comparable it, TreeNode nd) {
      key = it;
      node = nd;
    }
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void clear() {
    root = null;
  }
}
