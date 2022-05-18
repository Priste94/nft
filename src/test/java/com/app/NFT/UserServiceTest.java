package com.app.NFT;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.NFT.entities.User;
import com.app.NFT.repository.UserRepository;
import com.app.NFT.service.UserService;



@RunWith(SpringRunner.class)
//@WebMvcTest(NFTController.class)
//@ContextConfiguration(classes = Nft1Application.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testUniqueUserName() {

		String username = "admina";
		boolean result = userService.isUniqueUserName(userRepository.findByUserName(username));
		assertFalse(result);
		
		username = "adminq";
		result = userService.isUniqueUserName(userRepository.findByUserName(username));
		assertFalse(!result);

	}
}
