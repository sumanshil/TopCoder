package com.staxParser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by sshil on 4/9/2016.
 */
public class Parser {
    private static Stack<String> activeXPaths = new Stack<>();
    private static Stack<List<String>> activeExpectedXpaths = new Stack<>();

    private static Map<String, List<String>> interestingXPaths = new HashMap<>();
    static {
        List<String> list = new ArrayList<>();
        list.add("header/username/@name".intern());
        interestingXPaths.put("header", list);
        list = new ArrayList<>();
        list.add("email".intern());
        interestingXPaths.put("email", list);

        list = new ArrayList<>();
        list.add("order-item/productId".intern());
        interestingXPaths.put("order-item", list);

    }
    public static void main(String[] args) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                factory.createXMLEventReader(
                    new FileReader("C:\\Users\\sshil\\Order.xml"));
            boolean interestedValue = false;
            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (interestingXPaths.containsKey(qName)){
                            activeXPaths.add(qName);
                            activeExpectedXpaths.add(interestingXPaths.get(qName));
                            if (activeExpectedXpaths.peek().contains(qName)){
                                String xpath = qName;
                                interestedValue = true;
                                // get all the validation results from qname. validate them
                            }
                        } else if (activeXPaths.size() > 0) {
                            String top = activeXPaths.pop();
                            String newName = top+"/"+qName;
                            activeXPaths.push(newName);
                            List<String> list = activeExpectedXpaths.peek();
                            for (String str : list){
                                if (newName.equals(str.intern())) {
                                    String xpath = top;
                                    interestedValue = true;
                                    // get all the validation results from qname. validate them
                                    activeExpectedXpaths.remove(qName);
                                }
                            }
                        }
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()){
                            Attribute attribute = attributes.next();
                            String attributeName = attribute.getName().getLocalPart();
                            if (activeXPaths.size() > 0) {
                                String attributeNameWithIdentifier = activeXPaths.peek()+"/@" + attributeName;
                                for (String str : activeExpectedXpaths.peek()){
                                    if (str.equals(attributeNameWithIdentifier)){
                                        // get all the validation result
                                        System.out.println("Found xpath");
                                        String value = attribute.getValue();
                                        System.out.println("value = "+ value +" xpath = "+attributeNameWithIdentifier);
                                    }
                                }
                            }

                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (interestedValue){
                            String currentPath = activeXPaths.peek();
                            System.out.println("xpath "+ activeXPaths.peek()+ " value "+characters);
                            // handle the values
                        }
                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String name = endElement.getName().getLocalPart();
                        if (interestedValue){
                            interestedValue = false;
                        }
                        if (activeXPaths.size() > 0) {
                            String topValue = activeXPaths.peek();
                            if (topValue.equals(name)){
                                activeXPaths.pop();
                                if (activeExpectedXpaths.size() > 0){
                                    // process them as null values
                                }
                                activeExpectedXpaths.pop();
                            } else {
                                String topStackValue = activeXPaths.pop();
                                topStackValue = topStackValue.substring(0, topStackValue.lastIndexOf('/'));
                                activeXPaths.push(topStackValue);
                            }
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

}
