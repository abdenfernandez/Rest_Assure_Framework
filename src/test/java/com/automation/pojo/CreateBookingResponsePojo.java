package com.automation.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBookingResponsePojo {

    int bookingid;
    CreateBookingRequestPojo booking;
}
