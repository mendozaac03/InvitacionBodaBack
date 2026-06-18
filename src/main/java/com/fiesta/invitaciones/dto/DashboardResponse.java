package com.fiesta.invitaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardResponse {
    private int totalFamilies;
    private int confirmedFamilies;
    private int pendingFamilies;
    private int confirmedGuests;
    private List<InvitationStatus> invitations;
    private int totalInvitedGuests;

    private double confirmationPercentage;

}
