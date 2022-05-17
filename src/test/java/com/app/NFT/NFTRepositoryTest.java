package com.app.NFT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.text.ParseException;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.NFT.Status;
import com.app.NFT.entities.User;
import com.app.NFT.repository.NFTRepository;
import com.app.NFT.service.NFTService;
import com.app.NFT.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Nft1Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NFTRepositoryTest {
	
//	private Date dateOfInput = new Date();
//	private int idn;
//	private String author = "TestAuthor";
//	private String name = "TestName";
//	private double price = 89.5;
//	private NFT.Status status = Status.ON_SALE;
//	private String url = "./nft/01.png";
//	private User ownedBy = null;
//	
//	private static boolean isInitialized = false;
//	
//	
	
//	@Before
//	public void setup() 
//			throws ParseException
//	{
//		if (!isInitialized)
//		{
//			nFTRepository.delAllNFT();
//			
//			;
//			idn = (int) Math.random()*100;
//			
//			NFT nft = new NFT(idn,url,price,status,name,author,dateOfInput,null,ownedBy,null);
//			nFTRepository.save(nft);
//			
//			Set<User> users = new HashSet<>();
//			
//			User u = new User(-1,"username","password", "name", 99999, )
//			//La promo sarà valida l'intero anno corrente
//			Date Inizio = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(Anno) + "-01-01");  
//			Date Fine = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(Anno) + "-12-31");
//			
//			
//			
//			promo = promoRepository.findByAnnoAndCodice(Anno, Codice);
//			
//			//riga 1 promozione standard
//			dettPromo.add(new DettPromo(-1,1,"049477701","",Inizio,Fine,"1.10","No",promo,new TipoPromo("1","",null)));
//			
//			//riga 2 promozione fidelity
//			dettPromo.add(new DettPromo(-1,2,"004590201","",Inizio,Fine,"1.99","Si",promo,new TipoPromo("1","",null)));
//		
//			//riga 3 promozione fidelity Only You
//			dettPromo.add(new DettPromo(-1,3,"008071001","67000076",Inizio,Fine,"2.19","Si",promo,new TipoPromo("1","",null)));
//			 
//			Anno = Anno - 1; //assicuriamoci che la promo sia scaduta
//			Inizio = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(Anno) + "-01-01");  
//			Fine = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(Anno) + "-12-31");
//			
//			//riga 4 promozione standard scaduta
//			dettPromo.add(new DettPromo(-1,4,"002001601","",Inizio,Fine,"2.19","No",promo,new TipoPromo("1","",null)));
//			
//			promo.setDettPromo(dettPromo);
//			promoRepository.save(promo);
//			
//			isInitialized = true;
//		}
//		else
//		{
//			IdPromo = promoRepository.findByAnnoAndCodice(Anno, Codice).getIdPromo();
//		}
//	}
	
	
	
	
	@Autowired
	NFTRepository nFTRepository;
	
	@Autowired
	UserService userService;
	
	@Test
	public void TestFindByIdu() {
		
		int id = 3;
		assertThat(nFTRepository.findByIdn(id))
		.extracting(NFT::getAuthor)
		.isEqualTo("gino");

	}
	
	@Test
	public void TestFindAllOnSale() {
		
		List<NFT> nfts = nFTRepository.findAllOnSale();
		
		for(int i=0; i<nfts.size(); ++i) {
			
			assertThat(nfts)
			.element(i)
			.extracting(NFT::getStatus)
			.toString()
			.equals("ON_SALE");
			
		}

		
	}
	
	@Test
	public void TestFindByOwnedBy() {
		
		User u = userService.SelByIdUser(1);
		
		List<NFT> nfts = nFTRepository.findByOwnedBy(u);
		
		for(int i=0; i<nfts.size(); ++i) {
			
			assertThat(nfts.get(i)
					.getOwnedBy()
					.getIdu())
			.isEqualTo(1);
	
		}
	
	}
	
	
	@Test
	public void TestGetNFTByName() {

		List<NFT> nfts = nFTRepository.getNFTByName("no");
		
		for(int i=0; i<nfts.size(); ++i) {		
		assertThat(nfts.get(i).getName()).isEqualTo("nome");

		}
	}
	
	
	@Test
	public void TestFindAllFavorites() {
		
		List<NFT> nfts = nFTRepository.findAllFavorites("1");
		
		assertEquals(2, nfts.size());

		
		
	}
	

}
