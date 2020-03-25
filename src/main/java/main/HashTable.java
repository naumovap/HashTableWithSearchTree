package main;

public class HashTable {

    private Node[] table;
    private int level; // Количество заполненных элементов в массиве

    public HashTable(int size) {
        table = new Node[size];
        level = 0;
    }

    public void put(String key, String value) {
        int hashcode = hash(key);
        Node temp = new Node(key, value);
        if (table[hashcode] != null) {
            Tree(table[hashcode], temp);
        } else {
            table[hashcode] = temp;
        }
        level++;
    }

    public String get(String key)  {
        int hashcode = hash(key);
        Node temp = table[hashcode];
        while (true) {
            if (temp == null) {
                return "Такого значения нет!";
            }
            if (key.equals(temp.key)) {
                return temp.value;
            } else {
                if (key.compareTo(temp.key) < 0) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
        }
    }

    public void remove(String key) {
        int hashcode = hash(key);
        Node temp = table[hashcode];
        if (temp != null) {
            while (true) {
                if (!key.equals(temp.key)) {
                    if (key.compareTo(temp.key) < 0) {
                        temp = temp.left;
                    } else {
                        temp = temp.right;
                    }
                } else {
                    // Есть сомнения по поводу избыточности условий
                    if (temp.left == null && temp.right == null) {
                        if (temp.equals(table[hashcode])) {
                            table[hashcode] = null;
                        } else {
                            if (temp.equals(temp.parent.left)) {
                                temp.parent.left = null;
                            } else {
                                temp.parent.right = null;
                            }
                        }
                    } else if (temp.left != null && temp.right != null) {
                        // Поиск максимального значения в левом поддереве
                        Node max = temp.left;
                        while (true) {
                            if (max.right != null) {
                                max = max.right;
                            } else {
                                temp.key = max.key;
                                temp.value = max.value;
                                max.parent.left = max.left;
                                break;
                            }
                        }
                    } else {
                        if (temp.left == null) {
                            temp.value = temp.right.value;
                            temp.key = temp.right.key;
                            temp.left = temp.right.left;
                            temp.right = temp.right.right;
                        } else {
                            temp.value = temp.left.value;
                            temp.key = temp.left.key;
                            temp.right = temp.left.right;
                            temp.left = temp.left.left;
                        }
                    }
                    break;
                }
            }
        } else {
            System.out.print("Такого значения нет!");
        }
    }

    public void print() {
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (count == level) break;
            if (table[i] != null) {
                System.out.print(i + "-ый элемент: ");
                printTree(table[i], 0);
                count++;
            } else continue;
        }
        System.out.println();
    }

    private void printTree(Node first, int level) {
        if (first != null) {
            for (int i = 0; i < level; i++) System.out.print("               ");
            System.out.print("└──");
            System.out.println(first.key + " " + first.value);
            printTree(first.left, level + 1);
            printTree(first.right, level + 1);
        }

    }

    private void Tree(Node first, Node temp) {
        if (temp.key.compareTo(first.key) < 0) {
            if (first.left == null) {
                first.left = temp;
                temp.parent = first;
            } else {
                Tree(first.left, temp);
            }
        } else if (temp.key.compareTo(first.key) > 0) {
            if (first.right == null) {
                first.right = temp;
                temp.parent = first;
            } else {
                Tree(first.right, temp);
            }
        } else if (temp.key.compareTo(first.key) == 0) {
            first.value = temp.value;
        }
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private class Node {
        String key;
        String value;
        Node left;
        Node right;
        Node parent;
        Node(String key, String value)  {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            parent = null;
        }
    }
}
