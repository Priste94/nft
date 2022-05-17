package com.app.NFT.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.NFT.dto.TransactionDTO;
import com.app.NFT.entities.NFT;
import com.app.NFT.entities.NFT.Status;
import com.app.NFT.entities.Transaction;
import com.app.NFT.entities.User;
import com.app.NFT.repository.TransactionRepository;



@Service
@Transactional
public class TransactionService implements ITransactionService{
	
	@Autowired
	private TransactionRepository transazioneRepository;
	
	@Autowired
	private NFTService nFTService;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private ModelMapper modelMapper;
	

	@Override
	public List<Transaction> SelAll() {
		
		return transazioneRepository.findAll();
	}
	
	public List<TransactionDTO> SelAllDTO() {
		
		return transazioneRepository.findAll()
				.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
	}
	
    private TransactionDTO convertEntityToDto(Transaction transaction){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDTO;
    }

	@Override
	public Transaction SelByIdTransaction(int IdTransazione) {

		return transazioneRepository.findByIdt(IdTransazione);
	}

	@Override
	public ResponseEntity<Transaction> InsTransaction(Transaction transazione) {
		
		
		NFT nft = nFTService.SelByIdNFT(transazione.getNft().getIdn());
		User buyer = userService.SelByIdUser(transazione.getBuyer().getIdu());
		User seller = userService.SelByIdUser(transazione.getSeller().getIdu());
		if (nft.getStatus()==Status.ON_SALE) {
			if ((nft.getOwnedBy() == null) || (nft.getOwnedBy() != null  && transazione.getBuyer().getIdu()!=nft.getOwnedBy().getIdu()))
					if (transazione.getIdt() == 0)
						if(buyer.getWallet()>=transazione.getPrice())
							if (nft.getOwnedBy()!=null) { //con questa logico ho asserito che se nella richiesta si mette un venditore che non ha l'nft verrà inteso come null.. potrebbe aver senso avere una bad request
								if(seller.getIdu()==nft.getOwnedBy().getIdu())
									{
										Date today = new Date();
										String oggi = today.toString();
						    
							//		    logger.info("***** Creiamo una NFT con id " + GUID + " *****");
									    
									    transazione.setDate(today);
									    
										nft.setStatus(Status.SOLD);
										nft.setOwnedBy(buyer);
										
										transazione.setPrice(nft.getPrice());
										
										buyer.decreaseWallet(nft.getPrice());
										
										if (transazione.getSeller()!=null)
											seller.increasesWallet(nft.getPrice());
										//NFT nft = new NFT(transazione.getNft().getIdn(), transazione.getNft().getUrl(), true, transazione.getNft().getNome(),  transazione.getNft().getAutore(),  transazione.getNft().getDataInserimento(),  transazione.getNft().getTransazioni());
										//transazione.getNft().setStato(true);
										nFTService.InsNFT(nft);
										transazioneRepository.save(transazione);
						
						
						
									return new ResponseEntity<Transaction>(new HttpHeaders(), HttpStatus.CREATED);
									}
							}
										else {
											Date today = new Date();
											String oggi = today.toString();
							    
								//		    logger.info("***** Creiamo una NFT con id " + GUID + " *****");
										    
										    transazione.setDate(today);
										    
											nft.setStatus(Status.SOLD);
											nft.setOwnedBy(buyer);
											
											transazione.setPrice(nft.getPrice());
											
											buyer.decreaseWallet(nft.getPrice());
											
											//NFT nft = new NFT(transazione.getNft().getIdn(), transazione.getNft().getUrl(), true, transazione.getNft().getNome(),  transazione.getNft().getAutore(),  transazione.getNft().getDataInserimento(),  transazione.getNft().getTransazioni());
											//transazione.getNft().setStato(true);
											nFTService.InsNFT(nft);
											transazioneRepository.save(transazione);
											transazione.setSeller(null);
											return new ResponseEntity<Transaction>(new HttpHeaders(), HttpStatus.CREATED);
										}
									}
				{
	//			 logger.warn("Impossibile modificare con il metodo POST ");
				 
				 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}

	}
				
				
				
	public List<Transaction>  SelTransazioniByIdUser(int idUser) {
		
		return transazioneRepository.getTransactionsByUserId(idUser);
	}
	
	public List<Transaction> SelAcquistiByIdUser(int idUser) {
		return transazioneRepository.getAcquisitionsByUserId(idUser);
	}

	public List<Transaction> SelVenditeByIdUser(int idUser) {
		return transazioneRepository.getSalesByUserId(idUser);
	}

}
