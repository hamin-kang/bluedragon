package com.bdboard.bluedragon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String root() {
		return "redirect:/board/list";
	}
}
