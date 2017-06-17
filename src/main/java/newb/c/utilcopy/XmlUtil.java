package newb.c.utilcopy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import newb.c.webmodule.exception.XmlException;

public final class XmlUtil {

	private static final String LOGIC_YES = "yes";
	private static final String DEFAULT_ENCODE = "UTF-8";
	private static final String REG_INVALID_CHARS = "&#\\d+;";

	/**
	 * 初始化Document对象
	 * @return
	 * @throws XmlException
	 */
	public static Document newDocument() throws XmlException {
		Document doc = null;

		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.newDocument();
		} catch (ParserConfigurationException e) {
			throw new XmlException(e);
		}

		return doc;
	}
	
	/**
	 * 主要用于将输入流转化成document对象
	 * @param in
	 * @return
	 * @throws XmlException
	 */
	public static Document getDocument(InputStream in) throws XmlException {
		Document doc = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			doc = builder.parse(in);
		} catch (ParserConfigurationException e) {
			throw new XmlException(e);
		} catch (SAXException e) {
			throw new XmlException(XmlException.XML_PARSE_ERROR, e);
		} catch (IOException e) {
			throw new XmlException(XmlException.XML_READ_ERROR, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// nothing to do
				}
			}
		}

		return doc;
	}

	/**
	 * 将xml文件转换成domcument对象
	 * @param file
	 * @return
	 * @throws XmlException
	 */
	public static Document getDocument(File file) throws XmlException {
		InputStream in = getInputStream(file);
		return getDocument(in);
	}
	
	/**
	 * 将xml文件转换成document对象
	 * @param xml
	 * @return
	 * @throws XmlException
	 * @throws UnsupportedEncodingException
	 */
	public static Document getDocument(String xml) throws XmlException,
			UnsupportedEncodingException {
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(
				xml.getBytes("UTF-8"));
		return getDocument(byteInputStream);
	}
	
	/**
	 * 得到root节点，xml文件
	 * @param in
	 * @return
	 * @throws XmlException
	 */
	public static Element getRootElementFromFile(InputStream in)
			throws XmlException {    
		return getDocument(in).getDocumentElement();
	}
	
	/**
	 * 得到root节点，xml字符串
	 * @param xml
	 * @return
	 * @throws XmlException
	 * @throws UnsupportedEncodingException
	 */
	public static Element getRootElementFromXmlStr(String xml)
			throws XmlException, UnsupportedEncodingException {
		return getDocument(xml).getDocumentElement();
	}
	
	/**
	 * 创建根节点
	 * @param tagName
	 * @return
	 * @throws XmlException
	 */
	public static Element createRootElement(String tagName) throws XmlException {
		Document doc = newDocument();
		Element root = doc.createElement(tagName);
		doc.appendChild(root);
		return root;
	}

	/**
	 * 获取某个节点下的所有子节点集合 
	 * @param parent
	 * @param tagName
	 * @return
	 */
	public static List<Element> getChildElements(Element parent, String tagName) {
		NodeList nodes = parent.getElementsByTagName(tagName);
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element && node.getParentNode() == parent) {
				elements.add((Element) node);
			}
		}
		return elements;
	}

	/**
	 * 获取某个节点下面的指定节点
	 * @param parent
	 * @param tagName
	 * @return
	 */
	public static Element getChildElement(Element parent, String tagName) {
		List<Element> children = getChildElements(parent, tagName);
		if (children.isEmpty()) {
			return null;
		} else {
			return children.get(0);
		}
	}

	/**
	 * 获取某个节点的值
	 * @param parent
	 * @param tagName
	 * @return
	 */
	public static String getElementValue(Element parent, String tagName) {
		String value = null;

		Element element = getChildElement(parent, tagName);
		if (element != null) {
			value = element.getTextContent();
		}

		return value;
	}

	/**
	 * 添加节点
	 * @param parent
	 * @param tagName
	 * @return
	 */
	public static Element appendElement(Element parent, String tagName) {
		Element child = parent.getOwnerDocument().createElement(tagName);
		parent.appendChild(child);
		return child;
	}

	/**
	 * 添加节点并设置value
	 * @param parent
	 * @param tagName
	 * @param value
	 * @return
	 */
	public static Element appendElement(Element parent, String tagName,
			String value) {
		Element child = appendElement(parent, tagName);
		child.setTextContent(value);
		return child;
	}

	/**
	 * 添加节点,并设置节点中包含的节点
	 * @param parent
	 * @param child
	 */
	public static void appendElement(Element parent, Element child) {
		Node tmp = parent.getOwnerDocument().importNode(child, true);
		parent.appendChild(tmp);
	}

	/**
	 * 添加节点，并给节点的值加上：<![CDATA[]!>
	 * @param parent
	 * @param tagName
	 * @param value
	 * @return
	 */
	public static Element appendCDATAElement(Element parent, String tagName,
			String value) {
		Element child = appendElement(parent, tagName);
		if (value == null) {
			value = "";
		}

		Node cdata = child.getOwnerDocument().createCDATASection(value);
		child.appendChild(cdata);
		return child;
	}

	/**
	 * 输出xml字符串不进行格式化
	 * @param node
	 * @return
	 * @throws XmlException
	 */
	public static String childNodeToString(Node node) throws XmlException {
		String payload = null;

		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();

			Properties props = tf.getOutputProperties();
			props.setProperty(OutputKeys.OMIT_XML_DECLARATION, LOGIC_YES);
			tf.setOutputProperties(props);

			StringWriter writer = new StringWriter();
			tf.transform(new DOMSource(node), new StreamResult(writer));
			payload = writer.toString();
			payload = payload.replaceAll(REG_INVALID_CHARS, " ");
		} catch (TransformerException e) {
			throw new XmlException(XmlException.XML_TRANSFORM_ERROR, e);
		}

		return payload;
	}

	/**
	 * 输出xml字符串并格式化
	 * @param node
	 * @return
	 * @throws XmlException
	 */
	public static String nodeToString(Node node) throws XmlException {
		String payload = null;

		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();

			Properties props = tf.getOutputProperties();
			props.setProperty(OutputKeys.INDENT, LOGIC_YES);
			props.setProperty(OutputKeys.ENCODING, DEFAULT_ENCODE);
			tf.setOutputProperties(props);

			StringWriter writer = new StringWriter();
			tf.transform(new DOMSource(node), new StreamResult(writer));
			payload = writer.toString();
			payload = payload.replaceAll(REG_INVALID_CHARS, " ");
		} catch (TransformerException e) {
			throw new XmlException(XmlException.XML_TRANSFORM_ERROR, e);
		}

		return payload;
	}

	private static InputStream getInputStream(File file) throws XmlException {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new XmlException(XmlException.FILE_NOT_FOUND, e);
		}
		return in;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		String xmlStr = "<root><id>对对方答复</id><test><id>bbbb</id><id2>bbbb</id2></test><test><id>aaaa</id><id2>cccc</id2></test></root>";
//		Element root = Xmls.getRootElementFromXmlStr(xmlStr);
//		String id = Xmls.getElementValue(root, "id");
//		List<Element> childIds = Xmls.getChildElements(root, "test");
//		for (Element element : childIds) {
//			String str1 = Xmls.getElementValue(element, "id2");
//			System.out.println(str1);
//		}
		
		Element root = XmlUtil.createRootElement("user");   
		XmlUtil.appendElement(root, "id", "100000");   
		XmlUtil.appendElement(root, "nick", "carver.gu");   
		XmlUtil.appendElement(root, "email", "carver.gu@gmail.com");   
		XmlUtil.appendElement(root, "gender", "male");   
		
		Element contactE = XmlUtil.appendElement(root, "contact");   
		XmlUtil.appendCDATAElement(contactE, "address", "hangzhou");   
		XmlUtil.appendCDATAElement(contactE, "post-code", "310019");   
		XmlUtil.appendCDATAElement(contactE, "telephone", "88888888");   
		  
		System.out.println(XmlUtil.nodeToString(root));
		
	}
}