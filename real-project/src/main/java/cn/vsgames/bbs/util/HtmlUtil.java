package cn.vsgames.bbs.util;

import java.io.IOException;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.core.io.ClassPathResource;

public class HtmlUtil {
	
	public static String formatHtml(String input) throws PolicyException, ScanException, IOException {
        ClassPathResource classPathResource = new ClassPathResource("antisamy/antisamy-vsgames.xml");
		Policy policy = Policy.getInstance(classPathResource.getInputStream());
		AntiSamy as = new AntiSamy();
		CleanResults cr = as.scan(input, policy, AntiSamy.SAX);
		return cr.getCleanHTML();
	}
}