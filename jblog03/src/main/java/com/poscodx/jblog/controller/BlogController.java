package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String index(@PathVariable("id") String blogId,
						@PathVariable Optional<Long> categoryNo,
						@PathVariable Optional<Long> postNo,
						Model model) {
		
		/* 없는 블로그 계정일 때 처리 해줘야될듯, 페이지 처리? 인터셉터로 하나? exception? */
		
		// 블로그 기본 정보
		BlogVo blogVo = blogService.getBlog(blogId);
		
		// 모든 카테고리 데이터
		List<CategoryVo> categoryList = blogService.getCategoriesById(blogId);

		List<PostVo> postList = null;
		
		// 카테고리번호
		if(categoryNo.isPresent()) {
			postList = blogService.getPostsByCategory(categoryNo.get());
		}
		else {
			// categoryNo 없을 때 default 카테고리 지정 후 postList 가져옴
			if(categoryList.size() > 0) {
				categoryNo = Optional.of(categoryList.get(0).getNo());
				postList = blogService.getPostsByCategory(categoryList.get(0).getNo());
			}
		}
		
		// 포스트 번호
		if(postNo.isPresent()) {
			PostVo post = blogService.getPostByNo(postNo.get());
			model.addAttribute("post", post);
		}
		else {
			// PostNo 없을 때 default Post 지정
			if (postList != null && postList.size() > 0) {
				PostVo post = blogService.getPostByNo(postList.get(0).getNo());
				model.addAttribute("post", post);
			}
		}
		model.addAttribute("categoryNo", categoryNo.get());
		model.addAttribute("postList", postList);
		model.addAttribute("blog", blogVo);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/main";
	}
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String blogId, Model model) {  // 기본 컨트롤러 매핑에 blogId 있으므로 얘 꼭 있어야함
		
		/* 블로그 주인 아닐떄 인터셉터 처리, admin 다 빼야겠다 */
		
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blog", blogVo);
		
		return "blog/admin-basic";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String blogId, Model model) {
		
		/* 블로그 주인 아닐떄 인터셉터 처리 */
		
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blog", blogVo);
		
		List<CategoryVo> category = blogService.getCategoriesById(blogId);
		model.addAttribute("category", category);
		
		return "blog/admin-category";
	}
	
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id") String blogId, CategoryVo categoryVo) {
		
		/* 블로그 주인 아닐떄 인터셉터 처리 */
		
		categoryVo.setBlogId(blogId);
		blogService.addCategory(categoryVo);
		
		return "redirect:/"+blogId+"/admin/category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String blogId, Model model) {
		
		/* 블로그 주인 아닐떄 인터셉터 처리 */
		
		List<CategoryVo> category = blogService.getCategoriesById(blogId);
		model.addAttribute("category", category);
		
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blog", blogVo);
		
		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String blogId, PostVo postVo) {
		
		/* 블로그 주인 아닐떄 인터셉터 처리 */
		
		blogService.addPost(postVo);
		return "redirect:/"+blogId;
	}
	
	@RequestMapping("/admin/delete/{no}")
	public String adminDeleteCategory (@PathVariable("id") String blogId, @PathVariable Long no) {
		
		/* 블로그 주인 아닐떄 인터셉터 처리 */
		
		blogService.removeCategory(no);
		return "redirect:/"+blogId+"/admin/category";
	}
	
	@RequestMapping(value="/admin/basic/update", method=RequestMethod.POST)
	public String adminBlogUpdate(@PathVariable("id") String blogId, 
								@RequestParam("file") MultipartFile file,
								BlogVo blogVo) {
		String url = fileUploadService.restore(file);
		blogVo.setImage(url);
		blogVo.setBlogId(blogId);
		blogService.updateBlog(blogVo);
		
		return "redirect:/"+blogId+"/admin/basic";  
	}
	
}
