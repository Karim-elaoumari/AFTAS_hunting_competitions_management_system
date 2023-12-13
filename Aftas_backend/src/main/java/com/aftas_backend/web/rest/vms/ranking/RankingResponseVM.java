package com.aftas_backend.web.rest.vms.ranking;

import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.web.rest.vms.competition.CompetitionResponseRankVM;
import com.aftas_backend.web.rest.vms.member.MemberResponseVM;

public record RankingResponseVM(
        MemberResponseVM member,
        CompetitionResponseRankVM competition,
        Integer rank,
        Double points
) {
    public static RankingResponseVM fromRanking(Ranking ranking) {
        return new RankingResponseVM(
                MemberResponseVM.fromMember(ranking.getMember()),
                CompetitionResponseRankVM.fromCompetition(ranking.getCompetition()),
                ranking.getRank(),
                ranking.getScore()
        );
    }
}
