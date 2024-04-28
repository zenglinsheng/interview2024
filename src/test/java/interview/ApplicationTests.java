package interview;

import interview.applicationEvent03.TestEventPublish;
import interview.beanLifeCycle02.Teacher;
import interview.iocFacade01.Factory;
import interview.iocFacade01.SupportType;
import interview.iocFacade01.pay.PayFacade;
import interview.iocFacade01.search.SearchFacade;
import interview.redisDistributeLock.RedisLockWithSetNx;
import interview.zkDistributeLock.ZkLock;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class ApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);
	@Autowired
	private Teacher teacher;

	@Autowired
	private TestEventPublish testEventPublish;

	@Autowired
	private RedisLockWithSetNx redisLockWithSetNx;
	
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

	@Test
	public void testRedisLockWithSetNx() {
		String lock = redisLockWithSetNx.getLock();
		if (lock != null) {
			System.out.println("获取锁成功");
		} else {
			System.out.println("获取锁失败");
		}
	}

	@Test
	public void testZkDistributeLock() throws IOException {
		ZkLock zkLock = new ZkLock();
		boolean lock = zkLock.getLock("order");
		if (lock) {
			System.out.println("获取锁成功");
		} else {
			System.out.println("获取锁失败");
		}
	}

	@Test
	public void testCuratorLock() throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
		client.start();
		InterProcessMutex lock = new InterProcessMutex(client, "/order");
		try {
			if (lock.acquire(10, TimeUnit.SECONDS)) {
				try {
					log.error("我获取了锁！");
				} finally {
					lock.release();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.close();

	}

}
