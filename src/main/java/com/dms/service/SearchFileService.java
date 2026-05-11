package com.dms.service;

import java.util.List;


import java.util.Map;
import java.util.HashMap;

import java.io.InputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.core.io.ClassPathResource;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.NodeList;

import com.CIS.model.CivilT;
import com.CIS.model.CivilTA;
import com.dms.model.AllowEfiling;
import com.dms.model.CaseFileDetail;
import com.dms.model.CaseType;

@Service
public class SearchFileService 
{
/*	@PersistenceContext
	private EntityManager em;*/
	@PersistenceContext(unitName="persistenceUnitEfiling")
	@Qualifier(value = "entityManagerFactoryEfiling")
	private EntityManager em;
	
	
	@PersistenceContext(unitName="persistenceUnitCIS")
	@Qualifier(value = "entityManagerFactoryCIS")
	private EntityManager emCIS;
	
	@Transactional
	public List<CaseFileDetail> getCaseFile(Long caseyear,Long casetype,String caseno) {
		List<CaseFileDetail> result=null;
	    String query=" SELECT cfd from CaseFileDetail cfd where cfd.fd_case_year="+caseyear+" and cfd.fd_case_type="+casetype+" and cfd.fd_case_no='"+caseno+"' "
	    		+ "";
		result= em.createQuery(query).getResultList();
		
		return result;
	}
	
	@Transactional
	public AllowEfiling codeValidation(Integer id,char c) {

		AllowEfiling result=null;
	    Query query=null;
		try {
			query = em.createQuery(" SELECT ae from AllowEfiling ae where ae.ae_code=:id and ae.ae_allow_for=:ae_allow_for").setParameter("id", id).
					setParameter("ae_allow_for", c);
			result=(AllowEfiling) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	public CivilT getCaseNoForPendingFfromCis(Short filcase_type,Integer reg_no, Short reg_year) {
		
		CivilT data =null;
		
		String q= "select ct from CivilT ct where ct.regcase_type="+filcase_type+" and ct.reg_no="+reg_no+" and ct.reg_year="+reg_year;
		
		try {
		Query query =emCIS.createQuery(q);
		
		data =(CivilT) query.getSingleResult();
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		return data;	
	}
	
	
	@Transactional
	public CivilTA getCaseNoForPendingFfromCisDisposed(Short filcase_type,Integer reg_no, Short reg_year) {
		
		CivilTA data =null;
		
		String q= "select ct from CivilTA ct where ct.regcase_type="+filcase_type+" and ct.reg_no="+reg_no+" and ct.reg_year="+reg_year;
		
		try {
		Query query =emCIS.createQuery(q);
		
		data =(CivilTA) query.getSingleResult();
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		return data;	
	}
	
	@Transactional
	public Short getCaseTypes(String label) {
		
		Short data =null;
		
		String q= "select case_type from case_type_t where type_name='"+label+"'";
		
		try {
		Query query =emCIS.createNativeQuery(q);
		
		data =(Short) query.getSingleResult();
		}
		catch (Exception e) {
			System.out.println("ggggggggggggggggggggggg"+e);
			
		}
		return data;	
	}
	
	@Transactional
	public AllowEfiling codeValidation(Integer id,String appno,char c) {

		AllowEfiling result=null;
	    Query query=null;
		try {
			query = em.createQuery(" SELECT ae from AllowEfiling ae where ae.ae_code=:id and ae.ae_allow_for=:ae_allow_for and ae.ae_appno=:ae_appno").setParameter("id", id).
					setParameter("ae_allow_for", c).setParameter("ae_appno", appno);
			result=(AllowEfiling) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	public CaseFileDetail getCaseFile(Integer year,String no,String type) {

		CaseFileDetail result=null;
	    Query query=null;
		try {
			query = em.createQuery(" SELECT cfd from CaseFileDetail cfd where cfd.fd_case_no=:fd_case_no and cfd.fd_case_year=:fd_case_year and  cfd.fd_case_type =("
					+ "select ct_id  from CaseType where ct_label=:type)").setParameter("fd_case_no", no).setParameter("type", type).
					setParameter("fd_case_year", year);
			result=(CaseFileDetail) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	public CaseType getCaseTypeById(Long Id) {

		CaseType result=null;
	    Query query=null;
		try {
			query = em.createQuery("select ct  from CaseType ct where ct.ct_id=:Id)").setParameter("Id", Id);
			result=(CaseType) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	public CaseFileDetail saveCaseDetails(CaseFileDetail r) {
		CaseFileDetail result=null;
	    
		  result=em.merge(r);
		
		return result;
	}
	
	
	
	
	   public byte[] generate(Map<String, String> data) throws Exception {

	        InputStream is = new ClassPathResource("template.odt").getInputStream();
	        OdfTextDocument document = OdfTextDocument.loadDocument(is);

	    //    replaceText(document.getContentRoot(), data);
	        replaceText(document.getContentRoot(), data, document);

	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        document.save(out);

	        return out.toByteArray();
	    }

		/*
		 * private void replaceText(Node node, Map<String, String> data) {
		 * 
		 * if (node.getNodeType() == Node.TEXT_NODE) { String text =
		 * node.getTextContent();
		 * 
		 * for (Map.Entry<String, String> entry : data.entrySet()) {
		 * 
		 * System.out.println("DATA MAP = " + entry); text = text.replace("${" +
		 * entry.getKey() + "}", entry.getValue()); }
		 * 
		 * node.setTextContent(text); }
		 * 
		 * NodeList children = node.getChildNodes(); for (int i = 0; i <
		 * children.getLength(); i++) { replaceText(children.item(i), data); } }
		 */
	
		
	   private void replaceText(Node node, Map<String, String> data, OdfTextDocument document) {

		    if (node.getNodeType() == Node.TEXT_NODE) {
		        String text = node.getTextContent();

		        // SPECIAL handling for mainContent
		        if (text.contains("${mainContent}")) {

		            String mainContent = data.get("mainContent");

		            // STEP 1: find the paragraph
		            Node current = node;
		            while (current != null && !(current instanceof TextPElement)) {
		                current = current.getParentNode();
		            }

		            if (current == null) return;

		            TextPElement paragraph = (TextPElement) current;
		            Node container = paragraph.getParentNode();

		            // STEP 2: split lines
		            String[] lines = mainContent.split("\\n");

		            // STEP 3: insert BEFORE removing (important for position)
		            Node refNode = paragraph;

		            for (String line : lines) {
		                try {
		                    TextPElement p = document.getContentDom()
		                            .newOdfElement(TextPElement.class);

		                    p.setTextContent(line.trim());

		                    container.insertBefore(p, refNode);

		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }

		            // STEP 4: remove original placeholder paragraph
		            container.removeChild(paragraph);

		            return;
		        }

		        // normal replacement
		        for (Map.Entry<String, String> entry : data.entrySet()) {
		            text = text.replace("${" + entry.getKey() + "}", entry.getValue());
		        }
		   //     System.out.println("Data Map level outside main content=" +text);
		        node.setTextContent(text);
		    }

		    NodeList children = node.getChildNodes();
		    for (int i = 0; i < children.getLength(); i++) {
		        replaceText(children.item(i), data, document);
		    }
		}
	
	
	
	   /*
		 * private void replaceText(Node node, Map<String, String> data, OdfTextDocument
		 * document) {
		 * 
		 * if (node.getNodeType() == Node.TEXT_NODE) { String text =
		 * node.getTextContent();
		 * 
		 * // SPECIAL handling for mainContent if (text.contains("${mainContent}")) {
		 * 
		 * String mainContent = data.get("mainContent");
		 * 
		 * // STEP 1: find the paragraph Node current = node; while (current != null &&
		 * !(current instanceof TextPElement)) { current = current.getParentNode(); }
		 * 
		 * if (current == null) return;
		 * 
		 * TextPElement paragraph = (TextPElement) current; Node container =
		 * paragraph.getParentNode();
		 * 
		 * // STEP 2: split lines String[] lines = mainContent.split("\\n");
		 * 
		 * // STEP 3: insert BEFORE removing (important for position) Node refNode =
		 * paragraph;
		 * 
		 * for (String line : lines) { try { TextPElement p = document.getContentDom()
		 * .newOdfElement(TextPElement.class);
		 * 
		 * p.setTextContent(line.trim());
		 * 
		 * // ✅ LINE SPACING FIX (important) StyleStyleElement style =
		 * document.getContentDom() .newOdfElement(StyleStyleElement.class);
		 * 
		 * style.setStyleFamilyAttribute("paragraph");
		 * 
		 * StyleParagraphPropertiesElement props =
		 * style.newStyleParagraphPropertiesElement();
		 * 
		 * // 1.5 line spacing props.setFoLineHeightAttribute("150%");
		 * 
		 * // spacing between paragraphs props.setFoMarginBottomAttribute("0.2cm");
		 * 
		 * p.setStyleName(style.getStyleName());
		 * 
		 * container.insertBefore(p, refNode);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } }
		 * 
		 * // STEP 4: remove original placeholder paragraph
		 * container.removeChild(paragraph);
		 * 
		 * return; }
		 * 
		 * // normal replacement for (Map.Entry<String, String> entry : data.entrySet())
		 * { text = text.replace("${" + entry.getKey() + "}", entry.getValue()); }
		 * System.out.println("Data Map level outside main content=" +text);
		 * node.setTextContent(text); }
		 * 
		 * NodeList children = node.getChildNodes(); for (int i = 0; i <
		 * children.getLength(); i++) { replaceText(children.item(i), data, document); }
		 * }
		 */
	
	

}
