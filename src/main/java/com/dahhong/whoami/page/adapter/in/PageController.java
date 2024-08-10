package com.dahhong.whoami.page.adapter.in;

import com.dahhong.whoami.global.response.ApiResponse;
import com.dahhong.whoami.page.domain.entity.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/page")
public class PageController {

	@GetMapping("/{id}")
	public ResponseEntity<?> getPage(@PathVariable Long id) {
		/**
		 * TODO: 로직
		 */
		return ResponseEntity.ok(ApiResponse.success(/* TODO: data! */));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllPages() {
		/**
		 * TODO: 로직
		 */
		return ResponseEntity.ok(ApiResponse.success(/* TODO: data! */));
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPage(@RequestBody Page page) {
		/**
		 * TODO: 로직
		 */
		return ResponseEntity.ok(ApiResponse.success(/* data? */));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePage(@PathVariable Long id, @RequestBody Page pageDetails) {
		/**
		 * TODO : 로직
		 */
		return ResponseEntity.ok(ApiResponse.success());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePage(@PathVariable Long id) {
		/**
		 * TODO : 로직
		 */
		return ResponseEntity.ok(ApiResponse.success());
	}
}
