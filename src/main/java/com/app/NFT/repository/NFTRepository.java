package com.app.NFT.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.NFT.entities.NFT;
import com.app.NFT.entities.User;

public interface NFTRepository extends JpaRepository<NFT, Long> {
	
	public NFT findByIdn(int id);

	@Query(value="SELECT DISTINCT n.* FROM Nft n WHERE n.status = 'ON_SALE'", nativeQuery=true)
	public List<NFT> findAllOnSale();
	

	
//    @Query(value="SELECT DISTINCT n.* FROM Nft n WHERE n.owned_by = ?1", nativeQuery=true)
//	public List<NFT> getNFTByUserId(String idUser);
    
    public List<NFT> findByOwnedBy(User ownedBy); //trova tutti gli nft posseduti da un utente

    @Query(value="SELECT DISTINCT n.* FROM Nft n WHERE n.name LIKE ?1%", nativeQuery=true)
	public List<NFT> getNFTByName(String name);
    
    @Query(value="SELECT n.* FROM user_favorites  uf  JOIN nft n WHERE uf.idu = ?1 AND n.idn = uf.idn", nativeQuery=true)
	public List<NFT> findAllFavorites(String idUser);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Nft", nativeQuery = true)
	void delAllNFT();
}
