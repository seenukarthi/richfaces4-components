package org.richfaces.renderkit;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;



import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * @author abelevich
 * 
 */
public class InplaceSelectRendererTest extends InplaceRendererTestBase  {
    
    
    public static final String PAGE_NAME = "inplaceSelectTest";
    
    public static final String BASE_ID = "form:inplaceSelect"; 
    
    
    @Test
    public void testDefaultEncode() throws IOException, SAXException {
        doTestDefaultEncode(PAGE_NAME, BASE_ID);
    }
    
    @Test
    public void testDefaultWithControlsEncode() throws IOException, SAXException {
        doTestDefaultWithControlsEncode(PAGE_NAME, BASE_ID);
    }

    @Test
    public void testEditEncode() throws IOException, SAXException {
        doTestEditEncode(PAGE_NAME, BASE_ID);
    }
    
    @Test
    public void testEdit() throws Exception {
        HtmlPage page =  environment.getPage("/inplaceSelectTest.jsf");
        String defaultComponentId = BASE_ID + DEFAULT; 
        edit(page, defaultComponentId, 2);  
        
        HtmlElement input = page.getFirstByXPath("//*[@id = '" + defaultComponentId + "Input']");
        assertNotNull(input);
        
        String label = input.getAttribute(HtmlConstants.VALUE_ATTRIBUTE);
        assertTrue("Label#3".equals(label));
    }
    
    private void edit(HtmlPage page, String inplaceSelectId, int selectIndex) throws Exception {
        HtmlElement span = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "']");
        assertNotNull(span);
        span.click();
        
        HtmlElement edit = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "Edit']");
        assertNotNull(edit);
        assertEquals("rf-is-edit", edit.getAttribute(HtmlConstants.CLASS_ATTRIBUTE));
        
        HtmlElement list = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "List']");
        assertNotNull(list);
        assertFalse(list.isDisplayed());
        
        span = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "']");
        assertNotNull(span);
        span.click();
        
        list = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "List']");
        assertNotNull(list);
        assertTrue(list.isDisplayed());
        
        HtmlElement item = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "Item"+selectIndex+"']");
        assertNotNull(item);
        item.click();
        
        list = page.getFirstByXPath("//*[@id = '" + inplaceSelectId + "List']");
        assertNotNull(list);
        assertFalse(list.isDisplayed());
    }

    
    public void TestEditWithControls() {
    }
}
