package com.xue.concurrency.threadlocal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mingway on Date:2019-03-26 17:02.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

	@PostMapping("/test")
	public Long getId() {
		return RequestHolder.getId();
	}
}
