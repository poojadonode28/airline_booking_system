package com.booking.airlinebookingsystem.controllers;

import com.booking.airlinebookingsystem.dto.BookingDTO;
import com.booking.airlinebookingsystem.dto.UserDTO;
import com.booking.airlinebookingsystem.entities.Flights;
import com.booking.airlinebookingsystem.entities.Bookings;
import com.booking.airlinebookingsystem.entities.User;
import com.booking.airlinebookingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Void> SaveUser(@RequestBody UserDTO userDTO){
        int response=userService.saveData(userDTO);
        if(response==0) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("/searchFlight/{src}/{dest}")
    public ResponseEntity<List<Flights>> GetFlights(@PathVariable("src") String src, @PathVariable("dest") String dest){
        List<Flights> list= userService.getFlights(src,dest);
        if(list.size()==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/booking")
    public ResponseEntity<Void> SaveUser(@RequestBody BookingDTO bookingDto){
       // System.out.println(bookings.getFlight().getId());
        int response=userService.saveBooking(bookingDto);
        System.out.println(response);
        if(response==1) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



}
