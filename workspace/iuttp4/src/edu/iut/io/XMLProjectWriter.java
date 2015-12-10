package edu.iut.io;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import edu.iut.app.ExamEvent;

//EX 1 Completer la classe 

public class XMLProjectWriter {
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
	public XMLProjectWriter() {		
	}
	
	public void save(ArrayList<ExamEvent> data, java.io.File xmlfile) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = document.createElement("agenda");
			Comment comment	= document.createComment("Agenda backup");
			root.appendChild(comment);
			for(ExamEvent ee : data){
				Element event = document.createElement("event");
				event.setAttribute("date", ee.getDate().toString());
				/*Element student = document.createElement("student");
				student.setAttribute("function", ee.getStudent().getFunction().name());
				student.setAttribute("firstname", ee.getStudent().getFirstname());
				student.setAttribute("lastname", ee.getStudent().getLastname());
				student.setAttribute("email", ee.getStudent().getEmail());
				student.setAttribute("phone", ee.getStudent().getPhone());
				event.appendChild(student);
				for(int i=0; i<ee.getJury().size();i++){
					Element jury = document.createElement("jury");
					jury.setAttribute("function", ee.getJury().get(i).getFunction().name());
					jury.setAttribute("firstname", ee.getJury().get(i).getFirstname());
					jury.setAttribute("lastname", ee.getJury().get(i).getLastname());
					jury.setAttribute("email", ee.getJury().get(i).getEmail());
					jury.setAttribute("phone", ee.getJury().get(i).getPhone());
					jury.appendChild(jury);
				}
				Element classroom = document.createElement("classroom");
				classroom.setAttribute("number", ee.getClassroom().getClassRoomNumber());
				event.appendChild(classroom);
				for(int i=0;i<ee.getDocuments().size();i++){
					Element documents = document.createElement("document");
					documents.setAttribute("URI", ee.getDocuments().get(i).getDocumentURI());
					event.appendChild(documents);
				}*/
				root.appendChild(event);
			}
			document.appendChild(root);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult output = new StreamResult(xmlfile);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, output);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
