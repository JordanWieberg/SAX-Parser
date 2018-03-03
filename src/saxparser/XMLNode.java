/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author JordanWieberg
 */
public class XMLNode {
    private int depth;
    private String name = "";
    private String content = "";
    private HashMap<String, String> attributes;
    private ArrayList<XMLNode> children;
    
    public XMLNode() {
        attributes = new HashMap<>();
        children = new ArrayList<>();
    }
   
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setAttributes(String key, String value) {
        this.attributes.put(key, value);
    }
    
    public void addChild(XMLNode node) {
        children.add(node);
    }
    
    public int getDepth() {
        return depth;
    }
    
    public String getName() {
        return name;
    }
    
    public String getContent() {
        return content;
    }
    
    public HashMap<String, String> getAttributes() {
        return attributes;
    }
    
    public ArrayList<XMLNode> getChildren() {
        return children;
    }
}
