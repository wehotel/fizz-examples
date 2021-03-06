/*
 *  Copyright (C) 2021 the original author or authors.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package we.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

/**
 * Example
 * 
 * @author Francis Dong
 *
 */
@RestController
public class UserController {

	/**
	 * User list by page
	 * 
	 * @param exchange
	 * @return
	 */
	@GetMapping(value = "/user/list")
	public Object findUserList(@RequestParam("page") Integer page, ServerWebExchange exchange) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("message", "success");
		List<Map<String, Object>> list = new ArrayList<>();
		result.put("data", list);

		if (page == null || page > 5 || page < 1) {
			return result;
		}

		int pagesize = 5;
		int offset = (page - 1) * pagesize;

		for (int i = offset; i < offset + pagesize; i++) {
			int n = i + 1;
			Map<String, Object> user = new HashMap<>();
			user.put("userId", n);
			user.put("userName", "user-" + n);
			user.put("age", 20 + n % 5);
			list.add(user);
		}

		return result;
	}

	/**
	 * User detail
	 * 
	 * @param exchange
	 * @return
	 */
	@GetMapping(value = "/user/detail")
	public Object getUserDetail(@RequestParam("userId") Integer userId, ServerWebExchange exchange) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("message", "success");

		if (userId == null || userId > 25 || userId < 1) {
			return null;
		}

		Map<String, Object> user = new HashMap<>();
		user.put("userId", userId);
		user.put("userName", "user-" + userId);
		user.put("age", 20 + userId % 5);
		user.put("email", "user-" + userId + "@fizzgate.com");
		user.put("birthday", "2001-10-" + userId);
		if (userId < 10) {
			user.put("phone", "1380013800" + userId);
		} else {
			user.put("phone", "138001380" + userId);
		}
		result.put("data", user);

		return result;
	}

}
