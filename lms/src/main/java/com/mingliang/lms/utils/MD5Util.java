/**
 ****************************************************************
 * Title: wmb.MD5Uti
 * Description: 加密解密算法
 **************************************************************** 
 * 2018年8月14日 下午4:42:12  MD5加密算法
 */

package com.mingliang.lms.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


public class MD5Util implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年8月14日 下午4:42:12
	 * @Title: authenticatePassword
	 * @Description: 验证输入的密码是否正确
	 * @param actualPwd 真正的密码（加密后的真密码）
	 * @param inputPwd  
	 * @throws Exception 
	 * @return boolean  返回类型
	 */
	public static boolean authenticatePassword(String actualPwd, String inputPwd) throws Exception {
		return actualPwd.equals(encodeByMD5(Constant.MD5_PWD_BYTE_LEN,inputPwd));
	}
	
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年8月14日 下午4:47:32
	 * @Title: encodeByMD5
	 * @Description: MD5算法加密 -->不可配KEY(系统所用的加密方式)
	 * @param type
	 * @param sourcePwd
	 * @return String  返回类型 
	 * @throws Exception
	 */
	public static String encodeByMD5(int type, String sourcePwd) {
		String mdcode = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourcePwd.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;// 2/8
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			if (type == 16) {
				mdcode = buf.toString().substring(8, 24);
			} else if (type == 32) {
				mdcode = buf.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return mdcode;
	}

	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年8月14日 下午4:50:12
	 * @Title: encodeByMD5
	 * @Description: MD5算法加密 -->可配KEY
	 * @param sourcePwd 未加密的密码
	 * @throws Exception  参数
	 * @return String  返回类型 (加密的密文)
	 * @throws Exception
	 */
	public static String encodeByMD5(String sourcePwd) throws Exception {
		if (sourcePwd != null && !sourcePwd.trim().equals("")) {		
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(sourcePwd.getBytes());
			String resultString = byteArrayToHexString(results);
			return resultString.toUpperCase();			
		}
		return null;
	}

	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年8月14日 下午4:54:45
	 * @Title: byteArrayToHexString
	 * @Description: 转换字节数组为16进制字串
	 * @param bt 字节数组
	 * @throws Exception  参数
	 * @return String  返回类型(十六进制字串)
	 */
	public static String byteArrayToHexString(byte[] bt) throws Exception {
		StringBuffer resultSb = new StringBuffer();
		String MD5Key[] = getMD5PwdKey();
		for (int i = 0; i < bt.length; i++) {
			resultSb.append(byteToHexString(bt[i],MD5Key));
		}
		return resultSb.toString();
	}

	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年8月14日 下午4:53:29
	 * @Title: byteToHexString
	 * @Description: 将一个字节转化成16进制形式的字符串
	 * @param @param bt 字节数组
	 * @param @param hexDigits
	 * @param @return  参数
	 * @return String  返回类型(十六进制字串)
	 * @throws
	 */
	public static String byteToHexString(byte bt,String[] hexDigits) {
		int n = bt;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	
	/**
	 * 
	 * @author Michael.Ma
	 * @date 2018年8月14日 下午4:52:26
	 * @Title: getMD5PwdKey
	 * @Description: 获取MD5 KEY
	 * @throws Exception  参数
	 * @return String[]  返回类型
	 */
	public static String[] getMD5PwdKey() throws Exception {
		Properties directoryProp = new Properties();
		Resource res = new ClassPathResource("wmb.properties");
		directoryProp.load(res.getInputStream());
		String key = (String)directoryProp.get("MD5.PASSWORD.KEY");
		return key.split("%");
	}

//	public static void main(String[] args) throws Exception {
//		String password = MD5Util.encodeByMD5("abcd");
//		System.out.println("用MD5摘要后的字符串：" + password);
//		String inputString = "8888";
//		System.out.println("密码匹配？" + MD5Util.authenticatePassword(password, inputString));
//		inputString = "abcd";
//		System.out.println("密码匹配？"+ MD5Util.authenticatePassword(password, inputString));
		
//		System.out.println(encodeByMD5(32,"123456"));
//	}
	

}
