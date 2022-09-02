package site.metacoding.red.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;

@RequiredArgsConstructor
@RestController
public class UsersController {

	private final UsersDao usersDao;
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		return new RespDto<>(1, "한명조회완료", usersDao.findById(id));
	}
	
	@GetMapping("/users")
	public RespDto<?> findAll(){		
		return new RespDto<>(1, "전체조회완료", usersDao.findAll());
	}
	
	@DeleteMapping("/users/{id}")
	public RespDto<?> delete(@PathVariable Integer id) {
		usersDao.delete(id);
		return new RespDto<>(1, "회원삭제완료", null);
	}
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return new RespDto<>(1, "회원가입완료", null);
	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id, UpdateDto updateDto) {
		Users usersPS = usersDao.findById(id);
		usersPS.전체수정(updateDto);
		usersDao.update(usersPS);		
		return new RespDto<>(1, "회원수정완료", null);
	}
	
	@PutMapping("/users/{id}/password")
	public RespDto<?> updateP(@PathVariable Integer id, String password) {
		Users usersPS = usersDao.findById(id);
		usersPS.패스워드수정(password);
		return new RespDto<>(1, "패스워드수정완료", null);
	}
}
