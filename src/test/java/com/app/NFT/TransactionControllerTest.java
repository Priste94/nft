package com.app.NFT;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.NFT.entities.Transaction;
import com.app.NFT.entities.User;
import com.app.NFT.service.TransactionService;
import com.app.NFT.service.UserService;

@RunWith(SpringRunner.class)
//@WebMvcTest(NFTController.class)
//@ContextConfiguration(classes = Nft1Application.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	private String jsonData = "{\r\n"
			+ "        \"idt\": \"\",\r\n"
			+ "        \"date\": \"\",\r\n"
			+ "        \"price\": \"\",\r\n"
			+ "        \r\n"
			+ "        \"nft\": {\r\n"
			+ "            \"idn\" : \"9\"\r\n"
			+ "        },\r\n"
			+ "        \"buyer\": {\r\n"
			+ "            \"idu\" : \"2\"           \r\n"
			+ "        },\r\n"
			+ "        \"seller\": {\r\n"
			+ "            \"idu\" : \"1\"         \r\n"
			+ "        }\r\n"
			+ "    }";
	
	
	@Test
	public void B_testGetById() throws Exception {
		Transaction transaction = transactionService.SelByIdTransaction(2);
	
		mockMvc.perform(MockMvcRequestBuilders.get("/transaction/" + transaction.getIdt())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				
				.andExpect(jsonPath("$.idt").exists())
				.andExpect(jsonPath("$.idt").value(transaction.getIdt()))
				
				.andExpect(jsonPath("$.date").exists())
				.andExpect(jsonPath("$.date").value("2022-05-03T12:56:52.904+00:00"))
				
				.andExpect(jsonPath("$.price").exists()) 
				.andExpect(jsonPath("$.price").value(transaction.getPrice()))
				
				
				.andExpect(jsonPath("$.buyer.idu").exists())
				.andExpect(jsonPath("$.buyer.idu").value(transaction.getBuyer().getIdu()))
				
				
				.andExpect(jsonPath("$.seller").doesNotExist())
				
		
				.andExpect(jsonPath("$.nft.idn").exists())
				.andExpect(jsonPath("$.nft.idn").value(transaction.getNft().getIdn()));
			
//				.andReturn();
	}

	//COMMENTATO PER EVITARE OGNI VOLTA DI AGGIUNGERE UNA TRANSAZIONE
//	@Test
//	public void C_testCreateTransaction() throws Exception {
//	
//			mockMvc.perform(MockMvcRequestBuilders.post("/transaction/insert")
//					.contentType(MediaType.APPLICATION_JSON)
//					.content(jsonData)
//					.accept(MediaType.APPLICATION_JSON))
//					.andExpect(status().isCreated())
//					.andDo(print());
//		}

	
	@Test
	public void D_testGetTransactionsByIdUser() throws Exception {

		Transaction transaction = transactionService.SelByIdTransaction(7);
		User user = transaction.getBuyer();
	
		mockMvc.perform(MockMvcRequestBuilders.get("/transaction/" + 2 +"/transactions")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				
				.andExpect(jsonPath("$.[0].idt").exists())
				.andExpect(jsonPath("$.[0].idt").value(transaction.getIdt()))
				
				.andExpect(jsonPath("$.[0].date").exists())
				.andExpect(jsonPath("$.[0].date").value("2022-05-16T08:57:52.740+00:00"))
				
				.andExpect(jsonPath("$.[0].price").exists())
				.andExpect(jsonPath("$.[0].price").value(transaction.getPrice()))
				
				
				.andExpect(jsonPath("$.[0].buyer.idu").exists())
				.andExpect(jsonPath("$.[0].buyer.idu").value(transaction.getBuyer().getIdu()))
				
				
				.andExpect(jsonPath("$.[0].seller").exists())
				.andExpect(jsonPath("$.[0].seller.idu").value(transaction.getSeller().getIdu()))
		
				.andExpect(jsonPath("$.[0].nft.idn").exists())
				.andExpect(jsonPath("$.[0].nft.idn").value(transaction.getNft().getIdn()));
			
//				.andReturn();
	}
	
	@Test
	public void E_testGetSalesByIdUser() throws Exception {
		
		Transaction transaction = transactionService.SelByIdTransaction(7);
		User user = transaction.getBuyer();
	
		mockMvc.perform(MockMvcRequestBuilders.get("/transaction/" + 2 +"/acquisitions")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				
				.andExpect(jsonPath("$.[0].idt").exists())
				.andExpect(jsonPath("$.[0].idt").value(transaction.getIdt()))
				
				.andExpect(jsonPath("$.[0].date").exists())
				.andExpect(jsonPath("$.[0].date").value("2022-05-16T08:57:52.740+00:00"))
				
				.andExpect(jsonPath("$.[0].price").exists())
				.andExpect(jsonPath("$.[0].price").value(transaction.getPrice()))
				
				
				.andExpect(jsonPath("$.[0].buyer.idu").exists())
				.andExpect(jsonPath("$.[0].buyer.idu").value(transaction.getBuyer().getIdu()))
				
				
				.andExpect(jsonPath("$.[0].seller").exists())
				.andExpect(jsonPath("$.[0].seller.idu").value(transaction.getSeller().getIdu()))
		
				.andExpect(jsonPath("$.[0].nft.idn").exists())
				.andExpect(jsonPath("$.[0].nft.idn").value(transaction.getNft().getIdn()));
			
//				.andReturn();
		
		
	
	}
	
	
	
	
	@Test
	public void E_testGetAcquistiByIdUser() throws Exception {
		
		Transaction transaction = transactionService.SelByIdTransaction(7);
		User user = transaction.getBuyer();
	
		mockMvc.perform(MockMvcRequestBuilders.get("/transaction/" + 1 +"/sales")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				
				.andExpect(jsonPath("$.[0].idt").exists())
				.andExpect(jsonPath("$.[0].idt").value(transaction.getIdt()))
				
				.andExpect(jsonPath("$.[0].date").exists())
				.andExpect(jsonPath("$.[0].date").value("2022-05-16T08:57:52.740+00:00"))
				
				.andExpect(jsonPath("$.[0].price").exists())
				.andExpect(jsonPath("$.[0].price").value(transaction.getPrice()))
				
				
				.andExpect(jsonPath("$.[0].buyer.idu").exists())
				.andExpect(jsonPath("$.[0].buyer.idu").value(transaction.getBuyer().getIdu()))
				
				
				.andExpect(jsonPath("$.[0].seller").exists())
				.andExpect(jsonPath("$.[0].seller.idu").value(transaction.getSeller().getIdu()))
		
				.andExpect(jsonPath("$.[0].nft.idn").exists())
				.andExpect(jsonPath("$.[0].nft.idn").value(transaction.getNft().getIdn()));
			
//				.andReturn();
		
	}
}
