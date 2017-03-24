package com;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author LiangJianBin E-mail:asix765225181@163.com
 * @version 创建时间：2017-3-17 下午5:27:44 类说明
 */
public class HelloWorld {

	public String sayHello(String msg) {
		if (null == msg || "".equals(msg)) {
			return "您好!";
		} else {
			return "您好 : " + msg + "!";
		}
	}


	public String queryOaBarcodeService(String paramsXML) {
		System.out.println("---------------queryOaBarcodeService接口入参:-------------------- ");
		System.out.println(paramsXML);
		String result = "1111";
		try {
			
			Document doc = DocumentHelper.parseText(paramsXML);
			Element params = doc.getRootElement();
			String oaBarcode = params.element("serverbody").element("services").element("service").elementTextTrim("barcode");
			System.out.println("---------------------------------------------------------------");
			System.out.println(oaBarcode);
			
			String oabarcode = "201702176666";		//OAbarcode
			String name = "asix";							//单据名称
			String applicant = "------applicant------";		//申请人
			String applytime = "------applytime------";		//申请时间
			String applybranch = "----applybranch----";		//申请部门
			
			//拼接字符串
			StringBuffer strb=new StringBuffer();
			strb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			strb.append("<root>");
			
				strb.append("<items>");
					strb.append("<item>");
					
						strb.append("<barcode>"+oabarcode+"</barcode>");
						strb.append("<name>"+name+"</name>");
						strb.append("<applicant>"+applicant+"</applicant>");
						strb.append("<applytime>"+applytime+"</applytime>");
						strb.append("<applybranch>"+applybranch+"</applybranch>");
					
					strb.append("</item>");
				strb.append("</items>");
				
				strb.append("<result><![CDATA["+"0"+"]]></result>");
			strb.append("</root>");
			
			result = strb.toString();
			
			System.out.println("---------------queryOaBarcodeService接口出参----------------");
			System.out.println(strb.toString());
		} catch (Exception e) {
			System.out.println("---------------尊敬的用户：queryOaBarcodeService 接口异常"+e);
			result  = "------------------------尊敬的用户：queryOaBarcodeService 接口异常："+e.getMessage();
		}
		return result;
	}
	
	
	
}
