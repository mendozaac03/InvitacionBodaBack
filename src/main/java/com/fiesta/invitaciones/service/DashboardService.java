package com.fiesta.invitaciones.service;

import com.fiesta.invitaciones.dto.DashboardResponse;
import com.fiesta.invitaciones.dto.InvitationStatus;
import com.fiesta.invitaciones.mongo.Guest;
import com.fiesta.invitaciones.mongo.Rsvp;
import com.fiesta.invitaciones.repository.GuestRepository;
import com.fiesta.invitaciones.repository.RsvpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final GuestRepository guestRepository;

    private final RsvpRepository rsvpRepository;

    public DashboardResponse getDashboard() {
        int confirmedFamilies = 0;

        int confirmedGuests = 0;

        int totalInvitedGuests = 0;
        List<Guest> guests =
                guestRepository.findAll();

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalFamilies(
                guests.size());


        List<InvitationStatus> details =
                new ArrayList<>();

        for(Guest guest : guests){
            totalInvitedGuests +=
                    guest.getAllowedGuests();


            InvitationStatus item =
                    new InvitationStatus();

            item.setFamilyName(
                    guest.getFamilyName());

            item.setAllowedGuests(
                    guest.getAllowedGuests());

            Rsvp rsvp =
                    rsvpRepository
                            .findByCode(
                                    guest.getCode())
                            .orElse(null);

            if(rsvp != null){
                if(rsvp.getGuestsAttending()>0){
                    confirmedFamilies++;

                    confirmedGuests +=
                            rsvp.getGuestsAttending();

                    item.setConfirmed(true);

                    item.setGuestsAttending(
                            rsvp.getGuestsAttending());

                    item.setComment(
                            rsvp.getComment());
                }else{

                }
            }else{

                item.setConfirmed(false);

                item.setGuestsAttending(0);

                item.setComment("");

            }

            details.add(item);

        }

        response.setConfirmedFamilies(
                confirmedFamilies);

        response.setPendingFamilies(
                guests.size() - confirmedFamilies);

        response.setConfirmedGuests(
                confirmedGuests);
        response.setTotalInvitedGuests(
                totalInvitedGuests);

        double percentage = 0;

        if(totalInvitedGuests > 0){

            percentage =
                    (confirmedGuests * 100.0)
                            / totalInvitedGuests;
        }

        response.setConfirmationPercentage(
                Math.round(percentage * 100.0) / 100.0);
        response.setInvitations(
                details);

        return response;

    }

}