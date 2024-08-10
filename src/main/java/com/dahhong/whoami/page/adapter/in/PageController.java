package com.dahhong.whoami.page.adapter.in;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.page.adapter.in.dto.PageRequestDto;
import com.dahhong.whoami.page.adapter.in.dto.CreatePageResponseDto;
import com.dahhong.whoami.page.adapter.in.swagger.PageControllerSwagger;
import com.dahhong.whoami.page.application.port.in.CreatePageUseCase;
import com.dahhong.whoami.page.application.port.in.DeletePageUseCase;
import com.dahhong.whoami.page.application.port.in.GetPageUseCase;
import com.dahhong.whoami.page.application.port.in.UpdatePageUseCase;
import com.dahhong.whoami.page.application.service.DeletePageService;
import com.dahhong.whoami.page.domain.entity.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/page")
public class PageController implements PageControllerSwagger {

	private final CreatePageUseCase createPageUseCase;

	private final DeletePageUseCase deletePageUseCase;

	private final UpdatePageUseCase updatePageUseCase;

	private final GetPageUseCase getPageUseCase;
	private final DeletePageService deletePageService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getPage(@PathVariable Long id) {
		Page page = getPageUseCase.getPage(id);
		return ResponseEntity.ok(ApiResponse.success(page));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllPages() {
		List<Page> allPages = getPageUseCase.getAllPages();
		return ResponseEntity.ok(ApiResponse.success(allPages));
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPage(@RequestBody PageRequestDto pageRequest) {
		Page createdPage = createPageUseCase.createPage(pageRequest.getUserId(), pageRequest.getTitle());
		return ResponseEntity.ok(ApiResponse.success(new CreatePageResponseDto("성공적으로 페이지를 생성했습니다", createdPage.getId())));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePage(@PathVariable Long id, @RequestBody PageRequestDto pageRequest) {
		updatePageUseCase.updatePage(id, pageRequest);
		return ResponseEntity.ok(ApiResponse.success());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePage(@PathVariable Long id) {
		deletePageService.deletePage(id);
		return ResponseEntity.ok(ApiResponse.success());
	}
}
