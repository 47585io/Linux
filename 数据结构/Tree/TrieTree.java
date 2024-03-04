package 数据结构.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class TrieTree
{
    private TrieNode mRoot;
    
    public TrieTree(){
        mRoot = new TrieNode(null);
    }

    public void insert(CharSequence word)
    {
        TrieNode current = mRoot;
        int length = word.length();
        for(int i = 0; i < length; i++)
        {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                node = new TrieNode(current);
                current.children.put(ch, node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }
    
    public void delete(CharSequence word)
    {
        TrieNode node = search(word);
        if(node != null){
            node.isEndOfWord = false;
            condenseTree(word, node);
        }
    }

    private void condenseTree(CharSequence word, TrieNode current)
    {
        for(int i = word.length() - 1; i >= 0; i--)
        {
            char ch = word.charAt(i);
            if(current.isEndOfWord || current.children.size() > 0){
                break;
            }else{
                current.parent.children.remove(ch);
                current = current.parent;
            }
        }
    }

    public TrieNode search(CharSequence word) {
        TrieNode node = searchPrefix(word);
        return (node != null && node.isEndOfWord) ? node : null;
    }

    public TrieNode startsWith(CharSequence prefix) {
        return searchPrefix(prefix);
    }

    private TrieNode searchPrefix(CharSequence prefix)
    {
        TrieNode current = mRoot;
        int length = prefix.length();
        for(int i = 0; i < length; i++)
        {
            TrieNode node = current.children.get(prefix.charAt(i));
            if(node == null){
                return null;
            }
            current = node;
        }
        return current;
    }

    public List<String> postWords(TrieNode current, CharSequence prefix)
    {
        prefix = new StringBuilder(prefix);
        List<String> result = new ArrayList<>();
        postWords(current, (StringBuilder)prefix, result);
        return result;
    }
    
    private void postWords(TrieNode current, final String prefix, final List<String> result)
    {
        if(current.isEndOfWord){
            result.add(prefix);
        }
        current.children.forEach(new BiConsumer<Character, TrieNode>() {
            @Override
            public void accept(Character ch, TrieNode node) {
                postWords(node, prefix + ch, result);
            }
        });
    }

    private void postWords(TrieNode current, final StringBuilder prefix, final List<String> result)
    {
        if(current.isEndOfWord){
            result.add(prefix.toString());
        }
        current.children.forEach(new BiConsumer<Character, TrieNode>() {
            @Override
            public void accept(Character ch, TrieNode node) {
                prefix.append(ch);
                postWords(node, prefix, result);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        });
    }
}

class TrieNode
{
    boolean isEndOfWord; //标记该节点是否为某个单词的结束
    HashMap<Character, TrieNode> children; 
    TrieNode parent;

    public TrieNode(TrieNode parent) {
        isEndOfWord = false;
        children = new HashMap<>();
        this.parent = parent;
    }
}