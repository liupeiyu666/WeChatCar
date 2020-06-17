package com.ne.weixincar.onlearn.runtimeData;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.List;
/**
 由于频繁的访问数据库会造成性能消耗，所以这里进行临时数据的存储
 **/
import java.util.Map;

import com.ne.weixincar.onlearn.pojo.CarUser;
import com.ne.weixincar.onlearn.service.CarUserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;


public class UserManager {
	@Autowired
	private CarUserService carUserService ;

	private static UserManager _instance;

	/**
	 * 单例化
	 * @return
	 */
	public static UserManager get_instance() {
		if (_instance == null) {
			_instance = new UserManager();
		}
		return _instance;
	}

	private UserManager() {

	}

	Map map = new HashMap(8);

	/*
	 * 初始化加载所有的数据，之后运行时直接从这里获取，不需要每次请求都去数据库申请*/
	public void Init() {
		//初始化时加载所有数据
		List<CarUser> carUser = carUserService.getAllUser();

		for (int i = 0; i < carUser.size(); i++) {
			System.out.println("------------------:" + carUser.get(i).getWxOpenId());
			if (!map.containsKey(carUser.get(i).getWxOpenId())) {
				map.put(carUser.get(i).getWxOpenId(), carUser.get(i));
			}
		}
	}

	//添加用户
	public boolean AddUser(CarUser carUser) {
		if (!map.containsKey(carUser.getWxOpenId())) {
			map.put(carUser.getWxOpenId(), carUser.getWxOpenId());

			if(carUser.getPhone() == null || "".equals(carUser.getPhone())){
				carUser.setPhone("13346574567");
			}
			if(carUser.getPersonalNote() == null || "".equals(carUser.getPersonalNote())){
				carUser.setPersonalNote("x");
			}
			//数据库数据
			boolean isSuccess = true ;
			carUserService.addUser(carUser);
			return  isSuccess;

		}else{
			return false;
		}
	}

	//通过ID创建用户
	public void AddUserById(String p_opendId) {
		CarUser t_User = GetUser(p_opendId);
		if (t_User == null) {
			CarUser t_tempUser = new CarUser();
			t_tempUser.setWxOpenId( p_opendId ) ;
			AddUser(t_tempUser);
			System.out.println("添加用户成功---------：" + p_opendId);
		}
	}

	//获取用户
	public CarUser GetUser(String p_opendId) {
		if (map.containsKey(p_opendId)) {
			return (CarUser) map.get(p_opendId);
		}
		return null;
	}

	//更新用户信息
	public void UpdateUser(CarUser carUser) {
		if (map.containsKey(carUser.getWxOpenId())) {
			map.put(carUser.getWxOpenId(), carUser);

		}

	}

	public boolean CheckVip(String p_openId) {
		CarUser t_CarUser = GetUser(p_openId);
		if (t_CarUser != null) {
			if (t_CarUser.getVipLevel() >= 0) {
				return true;
			}
		}
		return false;
	}
}
