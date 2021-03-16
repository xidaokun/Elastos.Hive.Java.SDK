package org.elastos.hive.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentApi {
	@GET(BaseApi.API_VERSION + "/payment/vault_package_info")
	Call<ResponseBody> getPackageInfo();

	@GET(BaseApi.API_VERSION + "/payment/vault_pricing_plan")
	Call<ResponseBody> getPricingPlan(@Query("name") String name);

	@POST(BaseApi.API_VERSION + "/payment/create_vault_package_order")
	Call<ResponseBody> createOrder(@Body RequestBody body);

	@POST(BaseApi.API_VERSION + "/payment/pay_vault_package_order")
	Call<ResponseBody> payOrder(@Body RequestBody body);

	@GET(BaseApi.API_VERSION + "/payment/vault_package_order")
	Call<ResponseBody> getOrderInfo(@Query("order_id") String orderId);

	@GET(BaseApi.API_VERSION + "/payment/vault_package_order_list")
	Call<ResponseBody> getOrderList();

	@GET(BaseApi.API_VERSION + "/service/vault")
	Call<ResponseBody> getServiceInfo();

	@GET(BaseApi.API_VERSION + "/payment/version")
	Call<ResponseBody> getPaymentVersion();
}
