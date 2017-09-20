package newb.c.test;


import newb.c.controller.MockController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class MockTest {
	
	@Test
	public void testMock() throws Exception {

		MockController mockController =new MockController();
		MockMvc mockMvc = standaloneSetup(mockController).build();

		mockMvc.perform(get("/mock/mockTest")).andExpect(view().name("mock"));
	}
}
