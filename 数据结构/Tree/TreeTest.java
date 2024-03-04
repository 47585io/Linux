package 数据结构.Tree;

import java.util.List;

public class TreeTest 
{
    public static void testTrieTree(){
        TrieTreeTest.testTrieTree();
    }
}

class TrieTreeTest
{
    public static final CharSequence[] keyword = new String[]{
        "goto","const",
        "enum","assert",
        "package","import",
        "final","static","this","super",
        "try","catch","finally","throw","throws",
        "public","protected","private","friendly",
        "native","strictfp","synchronized","transient","volatile",
        "class","interface","abstract","implements","extends","new",
        "byte","char","boolean","short","int","float","long","double","void",
        "if","else","while","do","for","switch","case","default","break","continue","return","instanceof",
    };

    public static void testTrieTree()
    {
        TrieTree tree = new TrieTree();
        initTree(tree);
        TrieNode node = tree.startsWith("a");
        List<String> list = tree.postWords(node, "a");
        System.out.println(list);
    }

    private static void initTree(TrieTree tree){
        for(int i = 0; i < keyword.length; i++){
            tree.insert(keyword[i]);
        }
    }
}
