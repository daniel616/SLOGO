package xml;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import exceptions.ParserExceptions.LanguageNotFoundException;
import gui.MenuBar;

public abstract class XMLReader {
	private File file;
	private Element root;

	public XMLReader(File f) throws XMLException {
		changeFile(f);
	}
	
	public abstract void setFile(File f) throws Exception;

	protected void changeFile(File f){
		file = f;
	}

	protected String readRoot() throws LanguageNotFoundException{
		setRoot(getRootElement());
		return readLanguage();
	}

	protected void setRoot(Element nNode) {
		root = nNode;
	}
	
	private String readLanguage() throws LanguageNotFoundException {
		String lan = getAttribute(root, XMLWriter.LANGUAGE);
		if(MenuBar.LANGUAGE_OPTIONS.contains(lan))
			return lan;
		throw new LanguageNotFoundException(lan);
	}

	private Element getRootElement() throws XMLException {
		try {
			XMLWriter.DOCUMENT_BUILDER.reset();
			Document xmlDocument = XMLWriter.DOCUMENT_BUILDER.parse(file);
			return xmlDocument.getDocumentElement();
		}
		catch (Exception e){
			throw new XMLException(e);
		}
	}

	protected NodeList getByName(String name) {
		return root.getElementsByTagName(name);
	}

	protected String getAttribute(Element r, String att) {
		return r.getAttribute(att);
	}

	protected String getTextValue(String att) throws XMLException {
		NodeList nodeList = root.getElementsByTagName(att);
		if (nodeList != null && nodeList.getLength() > 0)
			return nodeList.item(0).getTextContent();
		else
			throw new XMLException("Couldn't get attribute for %s", att);
	}
}