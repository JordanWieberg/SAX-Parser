/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author JordanWieberg
 */
public class SAXXMLLoader {
    
    private static XMLNode dom = new XMLNode();
    
    public static XMLNode load(File xmlCourseFile) throws Exception {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
            XMLNode node = null;
            Stack<XMLNode> stack = new Stack<>();
            Boolean first = false;
            int depth = 0;
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    
                    if(!first) {
                        first = true;
                        dom.setName(qName);
                        stack.push(dom);
                        node = dom;
                        return;
                    }
                    depth++;
                    XMLNode temp = new XMLNode();
                    temp.setDepth(depth);
                    temp.setName(qName);
                    for(int i = 0; i < attributes.getLength(); i++) {
                        temp.setAttributes(attributes.getQName(i), attributes.getValue(i));
                    }
                    
                    node.addChild(temp); 
                    stack.push(temp);
                    node = temp;
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    XMLNode end = stack.pop();
                    depth--;
                    if(end != null)
                        end.setContent(end.getContent().trim());
                    if(!stack.empty())
                        node = stack.peek();
                    else 
                        node = null;
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    if(node != null)
                        node.setContent(new String (ch, start, length));
                }
            };
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
        } catch (Exception e) {
            throw e;
        }
        return dom;
    }
}
