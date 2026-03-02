package avicit.cmjt.task.rest;

import avicit.cmjt.cmjtcontract.dto.CmjtContractDTO;
import avicit.cmjt.cmjtcontract.service.CmjtContractService;
import avicit.cmjt.task.service.CmSyncService;
import avicit.cust.domain.customer.entity.Cust;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

/**
 * @金航数码科技有限责任公司
 * @作者：xiazx
 * @邮箱：xiazx@avic-d.com
 * @创建时间： 2025-04-11 11:31
 * @类说明：合同主表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "cmjtContract", value = "合同主表")
@RequestMapping("/api/task")
public class TaskRest {

	private static final Logger logger = LoggerFactory.getLogger(TaskRest.class);



	/**
	 * 请求集团接口获取集团合同数据
	 */
	@PostMapping("/cust/v1")
	public void postCmjt() {
//		cmjtContractService.postCmjtContract();
		Cust c = new Cust();
		c.setCode("wrightwerwerw");
		c.setName("wrightwerwerw");
		updateFmpCust(c);
	}
	private  void updateFmpCust(Cust cust){
		try{
			String url = "http://129.1.119.137:10001/api/v1/common/updateCust";
			URI uri = new URI(url);
			HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");//AEF56B8AE355963D6D75CD8CE4CB950F
			connection.setDoOutput(true);
			connection.setRequestProperty("Sign", "AEF56B8AE355963D6D75CD8CE4CB950F");
			connection.setRequestProperty("Basic", "avicit2015");
			JSONObject j=new JSONObject();
			j.put("code",cust.getCode());
			j.put("name",cust.getName());
			try (OutputStream os = connection.getOutputStream()) {
				byte[] input = j.toJSONString().getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			// 获取响应状态码
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 读取响应内容
				try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
					StringBuilder response = new StringBuilder();
					String responseLine;
					while ((responseLine = br.readLine()) != null) {
						response.append(responseLine.trim());
					}

					System.out.println("updateFmpCust: " + response.toString());

				}
			} else {
				System.err.println("updateFmpCustfail:"  + responseCode);
			}
			// 关闭连接
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
