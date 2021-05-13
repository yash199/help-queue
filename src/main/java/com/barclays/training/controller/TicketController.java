package com.barclays.training.controller;

import com.barclays.training.model.Ticket;
import com.barclays.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "timestamp", "asc", model);
	}
	
	@GetMapping("/showNewTicketForm")
	public String showNewTicketForm(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "new_ticket";
	}
	
	@PostMapping("/saveTicket")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticket.setStatus("NEW");
		ticketService.saveTicket(ticket);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "update_ticket";
	}

	@GetMapping("/doneTicket/{id}")
	public String doneTicket(@PathVariable ( value = "id") long id) {
		Ticket ticket = ticketService.getTicketById(id);
		ticket.setStatus("DONE");
		ticketService.saveTicket(ticket);
		return "redirect:/";
	}
	
	@GetMapping("/deleteTicket/{id}")
	public String deleteTicket(@PathVariable (value = "id") long id) {
		this.ticketService.deleteTicketById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Ticket> page = ticketService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Ticket> listTickets = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		List<Ticket> newTickets = listTickets.stream()
				.filter(ticket -> ticket.getStatus().equals("NEW"))
				.collect(Collectors.toList());
		model.addAttribute("newTickets", newTickets);
		//return "index";
		List<Ticket> resolvedTickets = listTickets.stream()
				.filter(ticket -> ticket.getStatus().equals("DONE"))
				.collect(Collectors.toList());
		model.addAttribute("resolvedTickets", resolvedTickets);
		return "index";
	}
}
