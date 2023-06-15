package com.codingbox.sprip.reservation.travelspot;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TravelSpotRepository {
	
	@Autowired
	private final EntityManager em;
	
	public TravelSpot findSpotById(Long spotId) {
		return em.find(TravelSpot.class, spotId);
	}
	public List<TravelSpot> findAll() {
		return em.createQuery("select t from TravelSpot t", TravelSpot.class)
				.getResultList();
	}
	
	public TravelSpot findSpot(String city) {
		return em.createQuery("select t from TravelSpot t where city = :city", TravelSpot.class)
				.setParameter("city", city)
				.getSingleResult();
	}
	
	public TravelSpot findContinent(String city) {
		return em.createQuery("select t from TravelSpot t where t.city = :city", TravelSpot.class)
					.setParameter("city", city)
					.getSingleResult();
		
	}
	
	public List<TravelSpot> findpage(int page) {
		return em.createQuery("select t from TravelSpot t order by t.id", TravelSpot.class)
				.setFirstResult((page-1)*5)
				.setMaxResults(5)
				.getResultList();
	}
	
	public List<TravelSpot> findContinentpage(int page, Continent continent) {
		return em.createQuery("select t from TravelSpot t where t.continent = :continent order by t.id", TravelSpot.class)
				.setParameter("continent", continent)
				.setFirstResult((page-1)*5)
				.setMaxResults(5)
				.getResultList();
	}
	
	public List<TravelSpot> findClimatepage(int page, Climate climate) {
		return em.createQuery("select t from TravelSpot t where t.climate = :climate order by t.id", TravelSpot.class)
				.setParameter("climate", climate)
				.setFirstResult((page-1)*5)
				.setMaxResults(5)
				.getResultList();
	}
	
	public List<TravelSpot> findLocal(int page) {
		return em.createQuery("select t from TravelSpot t where t.nation = '한국'", TravelSpot.class)
				.setFirstResult((page-1)*5)
				.setMaxResults(5)
				.getResultList();
	}
	
	public List<TravelSpot> findAbroad(int page) {
		return em.createQuery("select t from TravelSpot t where t.nation != '한국'", TravelSpot.class)
				.setFirstResult((page-1)*5)
				.setMaxResults(5)
				.getResultList();
	}
	
	public Long spotCount() {
		return (Long)em.createQuery("select count(t) from TravelSpot t")
					.getSingleResult();
	}
	public Long spotCount_Continent(Continent continent) {
		return (Long)em.createQuery("select count(t) from TravelSpot t where t.continent = :continent")
					.setParameter("continent", continent)
					.getSingleResult();
	}
	
	public Long spotCount_Climate(Climate climate) {
		return (Long)em.createQuery("select count(t) from TravelSpot t where t.climate = :climate")
					.setParameter("climate", climate)
					.getSingleResult();
	}
	
	public Long spotCount_Local() {
		return (Long)em.createQuery("select count(t) from TravelSpot t where t.nation = '한국'")
					.getSingleResult();
	}
	
	public Long spotCount_Abroad() {
		return (Long)em.createQuery("select count(t) from TravelSpot t where t.nation != '한국'")
					.getSingleResult();
	}
}
