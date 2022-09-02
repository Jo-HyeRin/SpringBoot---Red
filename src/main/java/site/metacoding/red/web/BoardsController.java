package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.RespDto;

@RestController
@RequiredArgsConstructor
public class BoardsController {
	private final BoardsDao boardsDao;
	
	@GetMapping("/boards/{id}")
	public RespDto<?> findById(@PathVariable Integer id) {
		return new RespDto<>(1, "하나조회", boardsDao.findById(id));
	}
	
	@GetMapping("/boards")
	public RespDto<?> findAll(Boards boards) {
		return new RespDto<>(1, "전체조회", boardsDao.findAll());
	}
	
	@PostMapping("/boards")
	public RespDto<?> insert(WriteDto writeDto) {
		boardsDao.insert(writeDto);
		return new RespDto<>(1, "글쓰기", null);
	}
	
	@PutMapping("/boards/{id}")
	public RespDto<?> updateB(@PathVariable Integer id, UpdateDto updateDto) {
		Boards boards = boardsDao.findById(id);
		boards.updateB(updateDto);
		boardsDao.update(boards);		
		return new RespDto<>(1, "전체 업데이트", null);
	}
	
	@PutMapping("/boards/{id}/title")
	public RespDto<?> updateT(@PathVariable Integer id, String title) {
		Boards boards = boardsDao.findById(id);
		boards.updateT(title);
		boardsDao.update(boards);
		return new RespDto<>(1, "title 업데이트", null);
	}
	
	@DeleteMapping("/boards/{id}")
	public RespDto<?> delete(@PathVariable Integer id) {
		boardsDao.delete(id);
		return new RespDto<>(1, "삭제", null);
	}
		
}
