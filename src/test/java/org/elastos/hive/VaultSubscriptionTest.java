package org.elastos.hive;

import org.elastos.hive.config.TestData;
import org.elastos.hive.subscription.PricingPlan;
import org.elastos.hive.subscription.VaultInfo;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VaultSubscriptionTest {
	public static final String PRICING_PLAN_NAME = "Rookie";

	private static VaultSubscription subscription;

	@BeforeAll public static void setup() {
		Assertions.assertDoesNotThrow(()->subscription = TestData.getInstance().newVaultSubscription());
	}

	@Test @Order(1) void testGetPricingPlanList() {
		Assertions.assertDoesNotThrow(()->{
			List<PricingPlan> plans = subscription.getPricingPlanList().get();
			Assertions.assertNotNull(plans);
			Assertions.assertFalse(plans.isEmpty());
		});
	}

	@Test @Order(2) void testGetPricingPlan() {
		Assertions.assertDoesNotThrow(()-> {
			PricingPlan plan = subscription.getPricingPlan(PRICING_PLAN_NAME).get();
			Assertions.assertNotNull(plan);
			Assertions.assertEquals(plan.getName(), PRICING_PLAN_NAME);
		});
	}

	@Disabled
	@Test @Order(3) void testSubscribe() {
		Assertions.assertDoesNotThrow(()->subscription.subscribe().get());
	}

	@Test @Order(4) void testCheckSubscription() {
		Assertions.assertDoesNotThrow(()-> {
			VaultInfo info = subscription.checkSubscription().get();
			Assertions.assertNotNull(info);
			Assertions.assertNotNull(info.getPricingPlan());
			Assertions.assertNotNull(info.getServiceDid());
			Assertions.assertTrue(info.getStorageQuota() > 0);
			Assertions.assertTrue(info.getCreated() > 0);
			Assertions.assertTrue(info.getUpdated() > 0);
		});
	}

	@Disabled
	@Test @Order(5) void testGetAppStats() {
		Assertions.assertDoesNotThrow(()->Assertions.assertNotNull(subscription.getAppStats().get()));
	}

	@Disabled
	@Test @Order(6) void testUnsubscribe() {
		Assertions.assertDoesNotThrow(()->subscription.unsubscribe().get());
	}

	@Disabled
	@Test @Order(7) void testGetFileHashProcess() {
		//prepare for access vault service
		Assertions.assertDoesNotThrow(() -> {
			subscription.subscribe().get();
		});
		//function usage
		new FilesServiceTest().testHash();
		//release for disable vault service accessing
		Assertions.assertDoesNotThrow(() -> {
			subscription.unsubscribe().get();
		});
	}
}
