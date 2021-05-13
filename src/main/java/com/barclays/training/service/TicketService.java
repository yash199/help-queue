package com.barclays.training.service;

import com.barclays.training.model.Ticket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketService {
	List<Ticket> getAllTickets();
	void saveTicket(Ticket ticket);
	Ticket getTicketById(long id);
	void deleteTicketById(long id);
	Page<Ticket> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
