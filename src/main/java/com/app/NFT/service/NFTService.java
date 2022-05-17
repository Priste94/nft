package com.app.NFT.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.NFT.dto.NFTDTO;
import com.app.NFT.entities.NFT;
import com.app.NFT.entities.User;
import com.app.NFT.repository.NFTRepository;



@Service
@Transactional
public class NFTService implements INFTService{
	
	@Autowired
	private NFTRepository nFTRepository;
	
	@Autowired
	private UserService userService;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public List<NFT> SelAll() {
		return nFTRepository.findAll();
	}
	

	public List<NFTDTO> SelAllDTO() {
		return nFTRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
                .collect(Collectors.toList());
	}
	
    private NFTDTO convertEntityToDto(NFT nft){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        NFTDTO NftDTO = new NFTDTO();
        NftDTO = modelMapper.map(nft, NFTDTO.class);
        return NftDTO;
    }

	@Override
	public NFT SelByIdNFT(int IdNFT) {
		return nFTRepository.findByIdn(IdNFT);
	}

	@Override
	public void InsNFT(NFT nft) {
		Date today = new Date();
		String oggi = today.toString();
	    
	    nft.setDateOfInput(today);;
		nFTRepository.save(nft);
		
	}

	@Override
	public void DelNFT(NFT nft) {
		nFTRepository.delete(nft);		
	}

	public List<NFTDTO> SelAllOnSale() {
		return nFTRepository.findAllOnSale()
				.stream()
				.map(this::convertEntityToDto)
                .collect(Collectors.toList());
	}
	

	public List<NFT> SelNFTByIdUser(String ownedBy) {
		User u = userService.SelByIdUser(Integer.parseInt(ownedBy));
		return nFTRepository.findByOwnedBy(u);
	}

	public List<NFT> SelNFTByName(String name) {
		return nFTRepository.getNFTByName(name);
	}

	public List<NFT> SelAllPreferites(String idUser) {
		return nFTRepository.findAllFavorites(idUser);
	}
	
	public void addFav(String idu, String idn) {
		
		User u = userService.SelByIdUser(Integer.parseInt(idu));
		
		NFT n = SelByIdNFT(Integer.parseInt(idn));

		u.getFavorites().add(n);

		
	}

	public void deleteFav(String idu, String idn) {
		User u = userService.SelByIdUser(Integer.parseInt(idu));
		
		NFT n = SelByIdNFT(Integer.parseInt(idn));

		u.getFavorites().remove(n);		
	}
}
