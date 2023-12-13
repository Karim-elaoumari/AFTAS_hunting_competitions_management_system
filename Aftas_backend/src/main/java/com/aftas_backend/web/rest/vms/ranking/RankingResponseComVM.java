package com.aftas_backend.web.rest.vms.ranking;

import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.web.rest.vms.competition.CompetitionResponseVM;
import com.aftas_backend.web.rest.vms.member.MemberResponseVM;

public record RankingResponseComVM(

        MemberResponseVM member,
        Integer rank,
        Double points
) {
    public static RankingResponseComVM fromRanking(Ranking ranking) {
        return new RankingResponseComVM(
                MemberResponseVM.fromMember(ranking.getMember()),
                ranking.getRank(),
                ranking.getScore()
        );
    }
}