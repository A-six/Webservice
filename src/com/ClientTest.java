package com;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author LiangJianBin E-mail:asix765225181@163.com
 * @version 创建时间：2017-3-17 下午5:34:15 类说明
 */
public class ClientTest {
	public void sayHello() throws MalformedURLException, RemoteException {
		try {
			Service service = new Service();

			Call call = (Call) service.createCall();
			// 设置地址
			call.setTargetEndpointAddress(new java.net.URL(
					"http://127.0.0.1:9090/webservice/services/helloWorldService?wsdl)"));
			// 设置要执行的方法
			call.setOperationName(new QName("http://com", "sayHello"));
			// 设置要传入参数,如果没有要传入的参数，则不要写这个
			call.addParameter("msg", org.apache.axis.Constants.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);
			// 设置返回的类型
			call.setReturnType(org.apache.axis.Constants.XSD_STRING);

			String name = "花非花雾非雾";
			// 执行，调用webservice
			String result = (String) call.invoke(new Object[] { name });
			System.out.println("返回的结果是：" + result);

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	public void Callwebservice() {
		// asmx
		// private String url =
		// "http://127.0.0.1:8080/imageCenter_hrzy/services/ImageService.asmx";
		String url = "http://127.0.0.1:9090/webservice/services/helloWorldService?wsdl)";
		// 参数
		String paramsXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<params>" + "<serverbody>" + "<services>" + "<service>"
				+ "<barcode>OA201612230001</barcode>" + "</service>"
				+ "</services>" + "</serverbody>" + "</params>";
		// 要调用的方法名
		String op = "queryOaBarcodeService";

		try {
			Call call = (Call) new Service().createCall(); // 创建Call实例，也是必须的！
			System.out.println("url  :    " + url);
			System.out
					.println("-------------------------------------------------------");
			System.out.println("接口方法名称  :    " + op);
			System.out
					.println("-------------------------------------------------------");
			call.setTargetEndpointAddress(new URL(url)); // 为Call设置服务的位置
			call.setOperationName(op);
			call.addParameter(op, org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN); // stirng[] 输入参数设置
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING); // 设置返回类型
			System.out.println("入 参  :    " + paramsXML);
			Object xml = (Object) call.invoke(new Object[] { paramsXML }); // 输入参数paramsXML
			String stringXML = xml.toString();
			System.out
					.println("-------------------------------------------------------");
			System.out.println("出 参  :    " + xml.toString());
			Document doc = DocumentHelper.parseText(stringXML);
			Element root = doc.getRootElement();

			System.out
					.println("-------------------------------------------------------");
			String barcode = root.element("items").element("item")
					.elementTextTrim("barcode");
			System.out.println("barcode       :    " + barcode);

			System.out
					.println("-------------------------------------------------------");
			String name = root.element("items").element("item")
					.elementTextTrim("name");
			System.out.println("name          :    " + name);

			System.out
					.println("-------------------------------------------------------");
			String applicant = root.element("items").element("item")
					.elementTextTrim("applicant");
			System.out.println("applicant     :    " + applicant);

			System.out
					.println("-------------------------------------------------------");
			String applytime = root.element("items").element("item")
					.elementTextTrim("applytime");
			System.out.println("applytime     :    " + applytime);

			System.out
					.println("-------------------------------------------------------");
			String applybranch = root.element("items").element("item")
					.elementTextTrim("applybranch");
			System.out.println("applybranch   :    " + applybranch);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws MalformedURLException,
			RemoteException {
		ClientTest sayHelloo = new ClientTest();
		//sayHelloo.sayHello();
		sayHelloo.Callwebservice();
	}
}
