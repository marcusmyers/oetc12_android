package org.oetc.oetc12_android;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;


public class RSSHandler extends DefaultHandler{
	RSSFeed _feed;
	RSSItem _item;
	String _lastElementName = "";
	StringBuilder builder;
	boolean bFoundChannel = false;
	final int RSS_TITLE = 1;
	final int RSS_DESCRIPTION = 2;
	final int RSS_PUBDATE = 3;
	final int RSS_LINK = 4;
	final int RSS_CATEGORY = 5;
	
	int depth = 0;
	int currentstate = 0;
	/*
	 * Constructor 
	 */
	RSSHandler()
	{
	}
	
	/*
	 * getFeed - this returns our feed when all of the parsing is complete
	 */
	RSSFeed getFeed()
	{
		return _feed;
	}
	
	
	@Override
	public void startDocument() throws SAXException
	{
		// initialize our RSSFeed object - this will hold our parsed contents
		_feed = new RSSFeed();
		// initialize the RSSItem object - we will use this as a crutch to grab the info from the channel
		// because the channel and items have very similar entries..
		_item = new RSSItem();
		builder = new StringBuilder();

	}
	@Override
	public void endDocument() throws SAXException
	{
	}
	@Override
	public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	{
		if (localName.toLowerCase().equals("item"))
		{
			// create a new item
			_item = new RSSItem();
			return;
		}
	}
	
	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		super .endElement(namespaceURI, localName, qName);
		if (localName.equals("item"))
		{
			// add our item to the list!
			_feed.addItem(_item);
			return;
		}
		if(_item != null && builder.length() > 0){
			if(localName.equalsIgnoreCase("title")){
				_item.setTitle(builder.toString().trim());
			} else if (localName.equalsIgnoreCase("link")){
				_item.setLink(builder.toString().trim());
			} else if(localName.equalsIgnoreCase("description")){
				_item.setDescription(builder.toString().trim().replaceAll("\\<.*?\\>", "").replaceAll("&nbsp;", "\n").replaceAll("&ndash;", "-").replaceAll("&rsquo;", "'").replaceAll("&#39;", "'"));
			} else if(localName.equalsIgnoreCase("pubdate")){
				_item.setPubDate(builder.toString().trim());
			}
			builder.setLength(0);
		}
		
	}
	 
	@Override
	public void characters(char ch[], int start, int length) throws SAXException
	{
		super .characters(ch, start, length);
		builder.append(ch, start, length);
	}
}
