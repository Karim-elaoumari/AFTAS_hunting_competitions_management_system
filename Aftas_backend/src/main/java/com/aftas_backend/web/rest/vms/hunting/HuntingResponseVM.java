package com.aftas_backend.web.rest.vms.hunting;

import com.aftas_backend.models.entities.Hunting;
import com.aftas_backend.web.rest.vms.competition.CompetitionResponseVM;
import com.aftas_backend.web.rest.vms.fish.FishResponseVM;
import com.aftas_backend.web.rest.vms.member.MemberResponseVM;

public record HuntingResponseVM(
        String id,
        Integer number_of_fish,
        FishResponseVM fish,
        MemberResponseVM member
) {
    public static HuntingResponseVM fromHunting(Hunting hunting) {
        return new HuntingResponseVM(
                hunting.getId().toString(),
                hunting.getNumberOfFish(),
                FishResponseVM.fromFish(hunting.getFish()),
                MemberResponseVM.fromMember(hunting.getMember())
        );
    }
}
