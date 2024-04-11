package interview;

import interview.applicationEvent03.TestEventPublish;
import interview.beanLifeCycle02.Teacher;
import interview.iocFacade01.Factory;
import interview.iocFacade01.SupportType;
import interview.iocFacade01.pay.PayFacade;
import interview.iocFacade01.search.SearchFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class ApplicationTests {

	@Autowired
	private Teacher teacher;

	@Autowired
	private TestEventPublish testEventPublish;
	
	@Test
	public void testIocFacade01() {
		SupportType supportType = new SupportType();
		supportType.setBusinessType("pay");
		supportType.setTemplateCode("ali");
		PayFacade ali = Factory.get(supportType);
		ali.pay();

		SupportType supportType2 = new SupportType();
		supportType2.setBusinessType("search");
		supportType2.setTemplateCode("weixin");
		SearchFacade weixin = Factory.get(supportType2);
		weixin.search();
	}

	@Test
	public void testBeanLifeCycle02() {
		teacher.printStudentName();
	}

	@Test
	public void testApplicationEvent03() {
		testEventPublish.publish();
	}

}
