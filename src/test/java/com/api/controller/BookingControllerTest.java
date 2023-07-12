package com.api.controller;

import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.api.entites.Order;
import com.api.entites.Screen;
import com.api.entites.SeatNo;
import com.api.entites.Theatre;
import com.api.entites.Ticket;
import com.api.entites.User;
import com.api.repository.OrderRepository;
import com.api.repository.ScreenRepository;
import com.api.repository.SeatNoRepository;
import com.api.repository.TicketRepository;
import com.api.repository.UserRepository;
import com.api.services.impl.BookingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private BookingController bookingController;

    @MockBean
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    
    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
		this.mockMvc=MockMvcBuilders.standaloneSetup( bookingController).build();
    }

    @Test
    public void bookingTicketTest() throws Exception {
        long ticketId = 1;

        Theatre theatre = new Theatre();
        theatre.setTheatreId(ticketId);
        theatre.setPincode(600412L);
        theatre.setTheatreCity("city");
        theatre.setTheatreName("Theatre");

        Screen screen = new Screen();
        screen.setScreenId(ticketId);
        screen.setMovietitle("Title");
        screen.setTheatre(theatre);

        User user = new User();
        user.setEmail("Namajram");
        user.setName("Namaj");

        SeatNo seat1 = new SeatNo();
        seat1.setSeatNo("A1");
        seat1.setSeatStatus("Unavailable");
        seat1.setScreen(screen);
        seat1.setUser(user);

        SeatNo seat2 = new SeatNo();
        seat2.setSeatNoId(ticketId);
        seat2.setSeatNo("A2");
        seat2.setSeatStatus("Unavailable");
        seat2.setScreen(screen);
        seat2.setUser(user);

        String ticketStatus = "Booked";

        Order order = new Order();
        order.setOrderId(ticketId);
        order.setPaymentStatus("Completed");
        order.setTheatre(theatre);
        order.setUser(user);

        Ticket ticket =new Ticket();
        		ticket.setSeat(Arrays.asList(seat1, seat2));
        ticket.setTicketId(ticketId);
        ticket.setOrder(order);
        ticket.setTicketStatus(ticketStatus);
               

        ticket.setTicketStatus("Booking");
        String writeValueAsString = objectWriter.writeValueAsString(ticket);

        Mockito.when(bookingServiceImpl.bookingTicket(Mockito.anyLong(), Mockito.anyLong(), ArgumentMatchers.any())).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/1/1/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ticketStatus", is("Booking")));
    }
}
//	@PostMapping("/cancel/{ticketId}")
//	public ResponseEntity<?> cancelBooking(@PathVariable long ticketId){
//		try {
//			  Order cancelBooking = bookingServiceImpl.cancelBooking(ticketId);
//	        if (cancelBooking != null) {
//	            return ResponseEntity.ok(cancelBooking);
//	        }
//	        return ResponseEntity.notFound().build();
//	  } catch (Exception e) {
//				return ResponseEntity.internalServerError().body(e.getMessage());
//			}
//	}


