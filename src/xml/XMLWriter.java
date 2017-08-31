package xml;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class XMLWriter {
	public static final String WORKSPACE = "Workspace";
	public static final String LANGUAGE = "Language";
	public static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	
	private Element root;
	private Document doc;
	private String address;
	
	public XMLWriter(String pAdd) throws XMLException {
		doc = DOCUMENT_BUILDER.newDocument();
		address = pAdd;
	}
	protected abstract void writeElements() throws XMLException;
	protected void writeRoot(String lan) {
		root = doc.createElement(WORKSPACE);
		Attr attr = doc.createAttribute(LANGUAGE);
		attr.setValue(lan);
		root.setAttributeNode(attr);
		doc.appendChild(root);
	}
	protected void addElement(Element cRoot, String name, String value) {
		Element newElement = doc.createElement(name);
		newElement.appendChild(doc.createTextNode(value));
		cRoot.appendChild(newElement);
	}
	protected void addElement(String name, String value) {
		addElement(root, name, value);
	}
	protected Element createElement(String el) {
		return doc.createElement(el);
	}	
	
	protected void appendChild(Element el) {
		root.appendChild(el);
	}
	
	protected void saveFile() throws XMLException {
		try{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(address));
			transformer.transform(source, result);
		} catch (Exception e) {
			throw new XMLException("Couldn't create file at: " + address);
		} 
	}
	private static DocumentBuilder getDocumentBuilder () {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}	
	}
}