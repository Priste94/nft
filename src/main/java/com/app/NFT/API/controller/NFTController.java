package com.app.NFT.API.controller;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.NFT.dto.NFTDTO;
import com.app.NFT.entities.NFT;
import com.app.NFT.entities.User;
import com.app.NFT.repository.NFTRepository;
import com.app.NFT.repository.UserRepository;
import com.app.NFT.service.NFTService;
import com.app.NFT.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;




@RestController
public class NFTController {
	
	@Autowired
	   private NFTService nFTService;
	
	@Autowired
	   private UserService userService;
	

		public NFTController() {

		}
		
		@GetMapping(value = "/nft", produces = "application/json") 
		public Iterable<NFTDTO> SelAll() {
			
			return nFTService.SelAllDTO();
		}
		
		@GetMapping(value = "/nft/onSale", produces = "application/json") 
		public List<NFTDTO> getAllOnSale() {
			
			return nFTService.SelAllOnSale();
		}
		
		
		@GetMapping(value = "/nft/{idNFT}", produces = "application/json")
		public NFT getById(@PathVariable("idNFT") int IdNFT) {
			return  nFTService.SelByIdNFT(IdNFT);
		}
		
		
		@PostMapping(value = "/nft")
		public ResponseEntity<NFT> createNFT(@RequestBody NFT nft)
		{
			if (nft.getIdn()>0)
			{
				UUID uuid = UUID.randomUUID();
			    String GUID = uuid.toString();
			    
//			    logger.info("***** Creiamo una NFT con id " + GUID + " *****");
			    
			    nft.setIdn(Integer.parseInt(GUID));
			}
			else
			{
//				 logger.warn("Impossibile modificare con il metodo POST ");
				 
				 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				 
				
			}
			
			nFTService.InsNFT(nft);
			
			return new ResponseEntity<NFT>(new HttpHeaders(), HttpStatus.CREATED);
		}
		
		
		@PutMapping(value = "/nft/modification") //per modificare ad esempio il prezzo di un nft: forse ha più senso creare un metodo specifico
		public ResponseEntity<NFT> updateNFT(@RequestBody NFT NFT)
		{
//			 logger.info("***** Modifichiamo la NFT con id " + NFT.getIdNFT() + " *****");
			 
			 if (NFT.getIdn() > 0)
			 {
				 nFTService.InsNFT(NFT);
				 
				 return new ResponseEntity<NFT>(new HttpHeaders(), HttpStatus.CREATED);
			 }
			 else
			 {
//				 logger.warn("Impossibile modificare una NFTzione priva di id! ");
				 
				 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			 }
		}
		
		@DeleteMapping(value = "/nft/{idNFT}")
		public ResponseEntity<?> deleteNFT(@PathVariable("idNFT") int IdNFT) 
		{
//			logger.info("Eliminiamo la NFT con id " + IdNFT);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseNode = mapper.createObjectNode();
			
			NFT NFT = nFTService.SelByIdNFT(IdNFT);
			
			if (NFT == null)
			{
//				logger.warn("Impossibile trovare la NFT con id " + IdNFT);
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			nFTService.DelNFT(NFT);
			
			responseNode.put("code", HttpStatus.OK.toString());
			responseNode.put("message", "Eliminazione NFT " + IdNFT + " Eseguita Con Successo!");
			
			return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
		}
		
		@PutMapping(value = "nft/changeState/{idNFT}")
		public ResponseEntity<?> cambiaStatoNFT(@PathVariable("idNFT") int IdNFT)
		{
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseNode = mapper.createObjectNode();
			
			NFT nft = nFTService.SelByIdNFT(IdNFT);
			
			if (nft == null)
			{
//				logger.warn("Impossibile trovare la NFT con id " + IdNFT);
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			nft.changeStatus();
			nFTService.InsNFT(nft);
			
			responseNode.put("code", HttpStatus.OK.toString());
			responseNode.put("message", "modifica stato NFT " + IdNFT + " Eseguita Con Successo!");
			
			return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
		}
		

		
		@GetMapping(value = "/nft/{idUser}/nfts", produces = "application/json")
		public List<NFT> getNFTById(@PathVariable("idUser") String IdUser) {
			return  nFTService.SelNFTByIdUser(IdUser);
		}
		
		@GetMapping(value = "/nft/search/{name}/nfts", produces = "application/json")
		public List<NFT> getNFTByName(@PathVariable("name") String name) {
			return  nFTService.SelNFTByName(name);
		}
		
		@GetMapping(value = "/nft/favorites/{idUser}", produces = "application/json") 
		public List<NFT> getAllPreferites(@PathVariable("idUser") String idUser) {
			
			return nFTService.SelAllPreferites(idUser);
		}
		
		
		@PostMapping(value = "/nft/{idUser}/addFavorite/{idNFT}")
		public ResponseEntity<?> addFavorites(@PathVariable("idUser")String idUser,@PathVariable("idNFT")String idNFT) //
		{
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseNode = mapper.createObjectNode();
				
			NFT nft = nFTService.SelByIdNFT(Integer.parseInt(idUser));
			User user = userService.SelByIdUser(Integer.parseInt(idUser));

			//System.out.println(nft);
			
			if (nft == null || user == null)
			{
//				logger.warn("Impossibile trovare la NFT con id " + IdNFT);
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			nFTService.addFav(idUser, idNFT);
			
			responseNode.put("code", HttpStatus.OK.toString());
			responseNode.put("message", "Aggiunta a preferiti NFT " + idNFT + " utente " + idUser + " Eseguita Con Successo!");
			
			return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
			
		}
		
		@DeleteMapping(value = "/nft/{idUser}/deleteFavorite/{idNFT}")
		public ResponseEntity<?> deleteFavorites(@PathVariable("idUser")String idUser,@PathVariable("idNFT")String idNFT)
		{
				
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode responseNode = mapper.createObjectNode();
			
			NFT nft = nFTService.SelByIdNFT(Integer.parseInt(idUser));
			User user = userService.SelByIdUser(Integer.parseInt(idUser));
			
			if (nft == null || user == null)
			{
//				logger.warn("Impossibile trovare la NFT con id " + IdNFT);
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
				nFTService.deleteFav(idUser, idNFT);
				
				responseNode.put("code", HttpStatus.OK.toString());
				responseNode.put("message", "Eliminazione da preferiti NFT " + idNFT + " di utente " + idUser + " Eseguita Con Successo!");
				
				return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
		}
	
}
