package com.codingbox.sprip.reservation.plane;

import javax.validation.GroupSequence;

import com.codingbox.sprip.reservation.plane.TicketFormValidationGroup.NumAndSeatGroup;
import com.codingbox.sprip.reservation.plane.TicketFormValidationGroup.SpotAndDateGroup;
 
@GroupSequence({NumAndSeatGroup.class, SpotAndDateGroup.class })

public interface TicketFormValidationSequence {

}