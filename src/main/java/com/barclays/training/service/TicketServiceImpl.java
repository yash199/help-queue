package com.barclays.training.service;

import com.barclays.training.model.Ticket;
import com.barclays.training.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public void saveTicket(Ticket ticket) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		ticket.setTimestamp(timestamp);
		this.ticketRepository.save(ticket);
	}

	@Override
	public Ticket getTicketById(long id) {
		Optional<Ticket> optional = ticketRepository.findById(id);
		Ticket ticket = null;
		if (optional.isPresent()) {
			ticket = optional.get();
		} else {
			throw new RuntimeException(" Ticket not found for id :: " + id);
		}
		return ticket;
	}

	@Override
	public void deleteTicketById(long id) {
		this.ticketRepository.deleteById(id);
	}

	@Override
	public Page<Ticket> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ticketRepository.findAll(pageable);
	}
}
