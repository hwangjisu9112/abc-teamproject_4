package Project_UI.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Project_UI.model.Item;
import Project_UI.model.Wish;
import Project_UI.security.UserInfo;
import Project_UI.service.ItemService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ItemController {
	private final ItemService itemService;
	
	// 상품 등록 이동
	@GetMapping("/writeForm")
	public String writeForm(Model model) {
		model.addAttribute("write", new Item());
		return "/writeForm";
	}
	
	// 상품 등록
	@PostMapping("/writeForm")
	public String write(@Validated @ModelAttribute("write") Item item, @AuthenticationPrincipal UserInfo userInfo) {
		item.setSeller_id(userInfo.getUsername());
		itemService.insertItem(item);
		return "redirect:/";
	}
	
	// 상품 상세
	@GetMapping("/read/{item_id}")
	public String read(@AuthenticationPrincipal UserInfo userInfo, @PathVariable long item_id, Model model) {
		Item findItem = itemService.findItem(item_id);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("read", findItem);
		return "/read";
	}
	
	// 상품 검색 결과
	@GetMapping("/result")
	public String search(Model model, @RequestParam (defaultValue = "") String item_category, @RequestParam (defaultValue = "")String searchWord) {
		List<Item> findSearchItem = itemService.findSearchItem(item_category, searchWord);
		model.addAttribute("searchItem", findSearchItem);
		model.addAttribute("searchWord", searchWord);
		return "/result";
	}
	
	// 상품 수정 이동
	@GetMapping("/update/{item_id}")
	public String updateForm(@PathVariable long item_id, Model model) {
		Item findItem = itemService.findItem(item_id);
		model.addAttribute("update", findItem);
		return "/update";
	}
	
	// 상품 수정
	@PostMapping("/update")
	public String update(Item item) {
		Item findItem = itemService.findItem(item.getItem_id());
		findItem.setItem_category(item.getItem_category());
		findItem.setItem_name(item.getItem_name());
		findItem.setItem_status(item.getItem_status());
		findItem.setItem_price(item.getItem_price());
		findItem.setItem_des(item.getItem_des());
		itemService.updateItem(findItem);
		return "redirect:/";
	}
	
	// 상품 삭제
	@GetMapping("/delete/{item_id}")
	public String delete(@PathVariable long item_id) {
		itemService.deleteItem(item_id);
		return "redirect:/";
	}
	
	// 상품 구매
	@GetMapping("/purchase/{item_id}")
	public String purchase(Item item, @AuthenticationPrincipal UserInfo userInfo) {
		Item findItem = itemService.findItem(item.getItem_id());
		findItem.setBuyer_id(userInfo.getUsername());
		itemService.purchase(findItem);
		return "redirect:/read/{item_id}";
	}
	
	// 상품 구매 취소
	@GetMapping("/purchase_cancel/{item_id}")
	public String purchase_cancel(Item item) {
		Item findItem = itemService.findItem(item.getItem_id());
		itemService.purchase_cancel(findItem);
		return "redirect:/read/{item_id}";
	}
	
	// 관심 상품 등록
	@GetMapping("/wish")
	public String wish(Model model, Wish wish, Item item, @AuthenticationPrincipal UserInfo userInfo) {
		model.addAttribute("wish", new Wish());
		wish.setBuyer_id(userInfo.getUsername());
		wish.setItem_id(item.getItem_id());
		return "redirect:/read/{item_id}";
	}
	
	// 리뷰 작성 이동
	@GetMapping("/review/{item_id}")
	public String reviewForm(Model model, @PathVariable long item_id) {
		Item findItem = itemService.findItem(item_id);
		model.addAttribute("review", findItem);
		return "/review";
	}
	
	// 리뷰 작성
	@PostMapping("/review")
	public String review(Item item) {
		Item findItem = itemService.findItem(item.getItem_id());
		findItem.setReview(item.getReview());
		findItem.setRating(item.getRating());
		itemService.review(findItem);
		return "redirect:/";
	}
}
