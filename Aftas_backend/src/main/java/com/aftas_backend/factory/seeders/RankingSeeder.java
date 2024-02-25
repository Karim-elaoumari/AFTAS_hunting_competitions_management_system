package com.aftas_backend.factory.seeders;

import com.aftas_backend.models.entities.Competition;
import com.aftas_backend.models.entities.Member;
import com.aftas_backend.models.entities.Ranking;
import com.aftas_backend.repositories.CompetitionRepository;
import com.aftas_backend.repositories.MemberRepository;
import com.aftas_backend.repositories.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class RankingSeeder {
    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    public void seed() {
        Competition todayCompetition = competitionRepository.findAllByDateEquals(LocalDate.now(), PageRequest.of(0, 1)).getContent().get(0);

        List<Member> member = memberRepository.findAll();
        for (Member value : member) {
            rankingRepository.save(Ranking.builder()
                    .competition(todayCompetition)
                    .member(value)
                    .rank(0)
                    .score((double) 0)
                    .build());
        }
    }
}
