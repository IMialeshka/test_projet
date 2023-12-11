package by.mialeshka.effectiveMobile;

import by.mialeshka.effectiveMobile.dto.TaskDto;
import by.mialeshka.effectiveMobile.references.Status;
import by.mialeshka.effectiveMobile.service.TaskService;
import by.mialeshka.effectiveMobile.service.impl.UserTaskDetailsService;
import by.mialeshka.effectiveMobile.utill.exception.TaskException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TaskManagementSystemApplicationTests {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TaskService taskService;


	@Test
	public void test1() throws TaskException {
		try (MockedStatic< UserTaskDetailsService> utilities =  Mockito.mockStatic(UserTaskDetailsService.class)) {
			utilities.when(UserTaskDetailsService::currentUser).thenReturn("ina@mail.ru");
			Pageable pageable = PageRequest.of(0, 2);
			TaskDto taskDto = taskService.getAllAuthorTasks(4, pageable).get(0);
			taskDto.setStatus(Status.COMPLETED);
			taskService.uppStatusTask(taskDto);
			Assert.assertEquals(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "task", "status='COMPLETED'"), 1);

		}

	}

	@Test
	public void test2() {
		Pageable pageable = PageRequest.of(0, 2);
		Assert.assertEquals(taskService.getAllAuthorTasks(4, pageable).size(), 2);
	}

}
