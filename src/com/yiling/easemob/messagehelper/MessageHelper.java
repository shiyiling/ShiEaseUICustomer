package com.yiling.easemob.messagehelper;

import org.json.JSONObject;


public class MessageHelper {

	public static JSONObject getMessageExtFromPicture(int index) {
		switch (index) {
		case 3:
			OrderMessageEntity entity1 = new OrderMessageEntity(1, "test_order1", "订单号：7890", "￥128",
					"2015早春新款高腰复古牛仔裙", "https://www.baidu.com/img/bdlogo.png", "http://www.baidu.com");
			return entity1.getJSONObject();
		case 4:
			OrderMessageEntity entity2 = new OrderMessageEntity(2, "test_order2", "订单号：7890", "￥518", "露肩名媛范套装",
					"https://www.baidu.com/img/bdlogo.png", "http://www.baidu.com");
			return entity2.getJSONObject();
		case 1:
			TrackMessageEntity entity3 = new TrackMessageEntity(3, "我正在看", "￥235", "假两件衬衣+V领毛衣上衣",
					"https://www.baidu.com/img/bdlogo.png", "http://www.baidu.com");
			return entity3.getJSONObject();
		case 2:
			TrackMessageEntity entity4 = new TrackMessageEntity(4, "我正在看", "￥162", "插肩棒球衫外套",
					"https://www.baidu.com/img/bdlogo.png", "http://www.baidu.com");
			return entity4.getJSONObject();
		}
		return null;
	}
	
	

}

