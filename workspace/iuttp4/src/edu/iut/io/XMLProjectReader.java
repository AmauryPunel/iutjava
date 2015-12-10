package edu.iut.io;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import edu.iut.app.*;
import edu.iut.app.Person.PersonFunction;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// EX 1 Completer la classe

public class XMLProjectReader {
	public XMLProjectReader() {

	}

	public ArrayList<ExamEvent> load(java.io.File xmlfile) throws IOException {
		ArrayList<ExamEvent> data = new ArrayList<ExamEvent>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlfile);
			// EX1: Lire un Document XML
			Element root = document.getDocumentElement();

			NodeList rootChildren = root.getElementsByTagName("event");
			for (int ci = 0; ci < rootChildren.getLength(); ci++) {
				if (rootChildren.item(ci).getNodeType() == Node.ELEMENT_NODE) {
					Node child = (Element) rootChildren.item(ci);
					if (child.hasAttributes()) {
						NamedNodeMap attributes = child.getAttributes();
						for (int att_i = 0; att_i < attributes.getLength(); att_i++) {
							Attr attribute = (Attr) attributes.item(att_i);
						}
					}
					// On récupère la date
					Element dateElement = (Element) child;
					String dateTempS = dateElement.getAttribute("date");
					//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat ft= new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.US);
					Date dateTemp = null;
					try {
						dateTemp = ft.parse(dateTempS);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*
					// On récupère l'étudiant
					NodeList students = ((Element) child).getElementsByTagName("student");
					Person student;
					Node subNodeStud = students.item(0);
					Element studentElement = (Element) subNodeStud;
					String fnameStud = studentElement.getAttribute("firstname");
					String lnameStud = studentElement.getAttribute("lastname");
					String mailStud = studentElement.getAttribute("email");
					String phoneStud = studentElement.getAttribute("phone");
					String fonctionStud = studentElement.getAttribute("function");
					student = new Person(PersonFunction.STUDENT, fnameStud, lnameStud, mailStud, phoneStud);
					// On récupère la liste des profs
					ArrayList<Person> listeProf = new ArrayList<Person>();
					NodeList jurys = ((Element) child).getElementsByTagName("jury");
					for (int si = 0; si < jurys.getLength(); si++) {
						Node subNode = jurys.item(si);
						Element juryElement = (Element) subNode;
						String fname = juryElement.getAttribute("firstname");
						String lname = juryElement.getAttribute("lastname");
						String mail = juryElement.getAttribute("email");
						String phone = juryElement.getAttribute("phone");
						String fonction = juryElement.getAttribute("function");
						Person jurytemp = new Person(PersonFunction.JURY, fname, lname, mail, phone);
						listeProf.add(jurytemp);
					}
					// On récupère le numéro de la classe
					NodeList rooms = ((Element) child).getElementsByTagName("classroom");
					Node subNodeRoom = rooms.item(0);
					Element roomElement = (Element) subNodeRoom;
					String roomNumber = roomElement.getAttribute("number");
					Classroom classTemp = new Classroom(roomNumber);
					// On récupère la liste des documents
					ArrayList<edu.iut.app.Document> listeDocuments = new ArrayList<edu.iut.app.Document>();
					NodeList documents = ((Element) child).getElementsByTagName("document");
					for (int si = 0; si < documents.getLength(); si++) {
						Node subNode = documents.item(si);
						Element documentElement = (Element) subNode;
						String URI = documentElement.getAttribute("URI");
						edu.iut.app.Document docTemp = new edu.iut.app.Document(URI);
						listeDocuments.add(docTemp);
					}
					*/
					//ExamEvent examTemp = new ExamEvent(dateTemp, student, listeProf, classTemp, listeDocuments);
					ExamEvent examTemp = new ExamEvent(dateTemp, null, null, null, null);
					data.add(examTemp);
				}
			}

		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}
}
