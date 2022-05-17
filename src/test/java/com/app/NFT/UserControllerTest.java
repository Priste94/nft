package com.app.NFT;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private UserService userService;
	
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	private String jsonData = "{\r\n"
			+ "        \"idu\": \"\",\r\n"
			+ "        \"name\": \"AdminchiumoTesta\",\r\n"
			+ "        \"password\": \"adminchiumoTesta\",\r\n"
			+ "        \"wallet\": \"200000000\",\r\n"
			+ "        \"userName\" : \"adminchiumoTesta\"\r\n"
			+ "}";

//	@Test
//	public void A_testInsertUser() throws Exception
//	{
//		mockMvc.perform(MockMvcRequestBuilders.post("/user/insert")
//			.contentType(MediaType.APPLICATION_JSON)
//			.content(jsonData)
//			.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isCreated())
//			.andDo(print());
//	}
	
	@Test
	public void B_testInsertUserByUserName() throws Exception {
		User user = userService.SelByUserName("adminchiumoTest");
	
		mockMvc.perform(MockMvcRequestBuilders.get("/username/" + user.getUserName())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				
				.andExpect(jsonPath("$.idu").exists())
				.andExpect(jsonPath("$.idu").value(user.getIdu()))
				
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.name").value(user.getName()))
				
//				.andExpect(jsonPath("$.password").exists()) //X @JsonIgnore da errore il test
//				.andExpect(jsonPath("$.password").value(user.getPassword()))
				
				
				.andExpect(jsonPath("$.wallet").exists())
				.andExpect(jsonPath("$.wallet").value(user.getWallet()))
				
				
				.andExpect(jsonPath("$.userName").exists())
				.andExpect(jsonPath("$.userName").value(user.getUserName()));
				
			
//				.andReturn();
	}
}

