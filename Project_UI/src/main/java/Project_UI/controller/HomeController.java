package Project_UI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Project_UI.model.Item;
import Project_UI.model.Member;
import Project_UI.security.UserInfo;
import Project_UI.service.ItemService;
import Project_UI.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ItemService itemService;
	
	@GetMapping("/")
    public String home(Model model, String item_category) {
		List<Item> list = itemService.findAllItem(item_category);
		model.addAttribute("list", list);
        return "main";
    }
}
