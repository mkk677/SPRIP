package com.codingbox.sprip.reservation.plane;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.sprip.reservation.ReserveStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketRepository {
	
	@Autowired
	private final EntityManager em;
	
	public void reserve(Ticket ticket) {
		em.persist(ticket);
	}
	
	public List<Ticket> findAllTicket(String memberId) {
		return em.createQuery("select t from Ticket t where t.reservation.member.userid = :memberid", Ticket.class)
				.setParameter("memberid", memberId)
				.getResultList();
	}
	
	public void cancel(Long ticketId) {
		Ticket ticket = em.find(Ticket.class, ticketId);
		ticket.getReservation().setStatus(ReserveStatus.CANCELED);
	}
}
